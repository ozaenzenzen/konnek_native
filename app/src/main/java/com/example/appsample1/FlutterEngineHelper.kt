package com.example.appsample1

import android.content.Context
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

object FlutterEngineHelper {
    private var flutterEngine: FlutterEngine? = null
    private const val ENGINE_ID = "sag_main_engine"
    private const val CHANNEL_ID = "konnek_native"
    private lateinit var channel: MethodChannel

    fun ensureEngine(context: Context) {
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(context.applicationContext).apply {
                navigationChannel.setInitialRoute("/")
                dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
                )

                FlutterEngineCache.getInstance().put(ENGINE_ID, this)
            }
            // callConfig()
            println("[FlutterEngineHelper][ensureEngine]")
        }
    }

    private fun callConfig() {
        println("[FlutterEngineHelper][callConfig]")
        val engine = FlutterEngineCache.getInstance().get(ENGINE_ID)
        if (engine != null) {
            channel = MethodChannel(
                engine.dartExecutor.binaryMessenger,
                CHANNEL_ID,
            )
            val arguments = hashMapOf<String, String>()
            arguments["clientId"] = KonnekNative.clientId
            arguments["clientSecret"] = KonnekNative.clientSecret
            arguments["flavor"] = KonnekNative.flavor
            println("[FlutterEngineHelper][initializeSDK] arguments: $arguments")
            val sendData: String = Gson().toJson(arguments)
            println("[FlutterEngineHelper][initializeSDK] sendData: $sendData")
            channel.invokeMethod("clientConfigChannel", sendData)
            channel.invokeMethod("fetchConfigData", "")

            channel.setMethodCallHandler { call, result ->
                println("[FlutterEngineHelper][onMethodCall]: 1 $call")
                println("[FlutterEngineHelper][onMethodCall]: 2 $result")
                if (call.method == "configData") {
                    println("[FlutterEngineHelper][onMethodCall]: configData called")
                    println("[FlutterEngineHelper][onMethodCall]: call ${call.arguments}")
                    val map: Map<*, *> = call.arguments as Map<*, *>
                    println("[FlutterEngineHelper][onMethodCall]: call map $map")

                    KonnekNative.callbackConfig.invoke(map)

                    result.success("success")
                } else {
                    result.notImplemented()
                }
            }
        }
    }

    fun launchFlutter(context: Context) {
        val engine = FlutterEngineCache.getInstance().get(ENGINE_ID)
        if (engine != null) {
            channel = MethodChannel(
                engine.dartExecutor.binaryMessenger,
                CHANNEL_ID,
            )
            val arguments = hashMapOf<String, String>()
            arguments["clientId"] = KonnekNative.clientId
            arguments["clientSecret"] = KonnekNative.clientSecret
            arguments["flavor"] = KonnekNative.flavor
            println("[FlutterEngineHelper][initializeSDK] arguments: $arguments")
            val sendData: String = Gson().toJson(arguments)
            println("[FlutterEngineHelper][initializeSDK] sendData: $sendData")
            channel.invokeMethod("clientConfigChannel", sendData)
            channel.invokeMethod("fetchConfigData", "")


            channel.setMethodCallHandler { call, result ->
                println("[FlutterEngineHelper][onMethodCall]: 1 $call")
                println("[FlutterEngineHelper][onMethodCall]: 2 $result")
                if (call.method == "configData") {
                    println("[FlutterEngineHelper][onMethodCall]: configData called")
                    println("[FlutterEngineHelper][onMethodCall]: call ${call.arguments}")
                    val map: Map<*, *> = call.arguments as Map<*, *>
                    println("[FlutterEngineHelper][onMethodCall]: call map $map")

                    KonnekNative.callbackConfig.invoke(map)

                    result.success("success")
                } else {
                    result.notImplemented()
                }
            }

            val intent = FlutterActivity
                .withCachedEngine(ENGINE_ID)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(context)
            println("[FlutterEngineHelper][launchFlutter] intent defined")
            context.startActivity(intent)
            println("[FlutterEngineHelper][launchFlutter] start activity called")
        }
    }
}
