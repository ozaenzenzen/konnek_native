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

    fun ensureEngine(context: Context) {
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(context.applicationContext).apply {
                navigationChannel.setInitialRoute("/");
                dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
                )

                FlutterEngineCache.getInstance().put(ENGINE_ID, this)
            }
            println("[FlutterEngineHelper][ensureEngine]")
        }
    }

    fun launchFlutter(context: Context) {
        val engine = FlutterEngineCache.getInstance().get(ENGINE_ID)
        if (engine != null) {
            val channel = MethodChannel(
                engine.dartExecutor.binaryMessenger,
                "konnek_native",
            )
            val arguments = hashMapOf<String, String>()
            arguments["clientId"] = KonnekNative.clientId
            arguments["clientSecret"] = KonnekNative.clientSecret
            arguments["flavor"] = KonnekNative.flavor
            println("[KonnekNative][initializeSDK] arguments: $arguments")
            val sendData: String = Gson().toJson(arguments)
            println("[KonnekNative][initializeSDK] sendData: $sendData")
            channel.invokeMethod("clientConfigChannel", sendData)

            val intent = FlutterActivity
                .withCachedEngine(ENGINE_ID)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(context)
            context.startActivity(intent)
        }
    }
}
