package com.example.appsample1

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.appsample1.support.DataGetConfig
import com.example.appsample1.support.EnvironmentConfig
import com.example.appsample1.support.Flavor
import com.example.appsample1.support.KonnekService
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

object FlutterEngineHelper {
    private var flutterEngine: FlutterEngine? = null
    private const val ENGINE_ID = "sag_main_engine"
    private const val CHANNEL_ID = "konnek_native"
    private lateinit var channel: MethodChannel

    private fun registerLifecycle(context: Context) {
        val application = context.applicationContext as Application
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityStarted(activity: Activity) {
                // Start engine here if appropriate
                Log.d("MyLifecycleManager", "Activity started: ${activity.localClassName}")
            }

            override fun onActivityStopped(activity: Activity) {
                // Stop engine here
                disposeEngine()
                Log.d("MyLifecycleManager", "Activity stopped: ${activity.localClassName}")
            }

            override fun onActivityDestroyed(activity: Activity) {
                disposeEngine()
                Log.d("MyLifecycleManager", "Activity destroyed: ${activity.localClassName}")
            }

            // Required empty implementations
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        })
    }

    private fun registerLifecycleV2(lifecycle: Lifecycle) {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w("MyLibraryEngine", "Cannot start engine: lifecycle already destroyed")
            return
        }

        // Start the engine
        Log.d("MyLibraryEngine", "Engine started")

        // Observe lifecycle to stop when needed
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStop(owner: LifecycleOwner) {
                disposeEngine()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                disposeEngine()
                lifecycle.removeObserver(this)
            }
        })
    }

    fun disposeEngine() {
//        Log.d("disposeEngine", "Flutter Engine disposed")
//        if (flutterEngine != null) {
//            flutterEngine?.destroy()
//            flutterEngine = null
//        }
    }

    fun ensureEngine(context: Context) {
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(context.applicationContext).apply {
                navigationChannel.setInitialRoute("/")
                dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
                )

                FlutterEngineCache.getInstance().put(ENGINE_ID, this)
            }
//            registerLifecycle(context)
            callConfigViaNative()
            // println("[FlutterEngineHelper][ensureEngine]")
        }
    }

    var initConfigData: String = ""

    private fun callConfigViaNative() {
        // println("[FlutterEngineHelper][callConfigViaNative]")
        KonnekService().getConfig(
            KonnekNative.clientId,
            onSuccess = { value: String ->
                initConfigData = value
                val output: Map<*, *> = jsonStringToMap(value)
                KonnekNative.triggerFloatingUIChanges.invoke(output["data"] as Map<*, *>)
            },
            onFailed = { errorMessage: String ->
                // println("[FlutterEngineHelper][callConfigViaNative][onFailed] errorMessage: $errorMessage")
            },
        )
    }

    private fun jsonStringToMap(jsonString: String): Map<*, *> {
        val jsonObject = JSONObject(jsonString)
        val map = mutableMapOf<String, Any>()
        val keys = jsonObject.keys()

        while (keys.hasNext()) {
            val key = keys.next()
            val value = jsonObject.get(key)
            when (value) {
                is JSONObject -> map[key] = jsonStringToMap(value.toString())
//                 is JSONArray -> map[key] = jsonArrayToList(value)
                else -> map[key] = value
            }
        }
        return map.toMap()
    }

    private fun callConfig(engineInput: FlutterEngine) {
        println("[FlutterEngineHelper][callConfig]")
        // val engine = FlutterEngineCache.getInstance().get(ENGINE_ID)
        val engine = engineInput
        if (engine != null) {
            channel = MethodChannel(
                engine.dartExecutor.binaryMessenger,
                CHANNEL_ID,
            )
            val arguments = hashMapOf<String, String>()
            arguments["clientId"] = KonnekNative.clientId
            arguments["clientSecret"] = KonnekNative.clientSecret
            arguments["flavor"] = KonnekNative.flavor
            // println("[FlutterEngineHelper][callConfig] arguments: $arguments")
            val sendData: String = Gson().toJson(arguments)
            // println("[FlutterEngineHelper][callConfig] sendData: $sendData")
            channel.invokeMethod("clientConfigChannel", sendData)
            if (initConfigData != "") {
                channel.invokeMethod("fetchConfigData", initConfigData)
            }

            channel.setMethodCallHandler { call, result ->
//                println("[FlutterEngineHelper][onMethodCall]: 1 $call")
//                println("[FlutterEngineHelper][onMethodCall]: 2 $result")
                if (call.method == "configData") {
//                    println("[FlutterEngineHelper][onMethodCall]: configData called")
//                    println("[FlutterEngineHelper][onMethodCall]: call ${call.arguments}")
                    val map: Map<*, *> = call.arguments as Map<*, *>
                    // println("[FlutterEngineHelper][onMethodCall]: call map $map")

                    result.success("success")
                } else if (call.method == "disposeEngine") {
//                    println("[FlutterEngineHelper][disposeEngine]")
                    // disposeEngine()
                    result.success("success dispose engine")
                } else {
                    result.notImplemented()
                }
            }
        }
    }

    fun launchFlutter(context: Context) {
        val engine = FlutterEngineCache.getInstance().get(ENGINE_ID)
        if (engine != null) {
            callConfig(engine)

            val intent = FlutterActivity
                .withCachedEngine(ENGINE_ID)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(context)
            context.startActivity(intent)
        }
    }

    fun envInit(flavor: String) {
        when (flavor) {
            "development" -> {
                EnvironmentConfig.flavor = Flavor.DEVELOPMENT
            }

            "staging" -> {
                EnvironmentConfig.flavor = Flavor.STAGING
            }

            "production" -> {
                EnvironmentConfig.flavor = Flavor.PRODUCTION
            }

            else -> {
                EnvironmentConfig.flavor = Flavor.STAGING
            }
        }
    }
}
