package com.example.appsample1

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class KonnekNative {
    fun initializeSDK(
//        flutterEngine: FlutterEngine,
        clientId: String,
        clientSecret: String,
        flavor: String,
//        messenger: BinaryMessenger,
    ) {
        var flutterEngine1: FlutterEngine = MainActivity.flutterEngine
        println("[KonnekNative][initializeSDK] call here")
        val methodChannel = MethodChannel(
            flutterEngine1.dartExecutor.binaryMessenger,
            "konnek_native"
        )
        val arguments = hashMapOf<String, String>()
        arguments["clientId"] = clientId
        arguments["clientSecret"] = clientSecret
        arguments["flavor"] = flavor
        println("[KonnekNative][initializeSDK] arguments: $arguments")
        val sendData: String = Gson().toJson(arguments)
        println("[KonnekNative][initializeSDK] sendData: $sendData")
        methodChannel.invokeMethod("clientConfigChannel", sendData)
    }
}