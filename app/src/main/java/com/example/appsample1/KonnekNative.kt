package com.example.appsample1

import android.content.Context
import android.view.View
import androidx.annotation.NonNull
import com.google.gson.Gson
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

object KonnekNative {
    internal var clientId: String = ""
    internal var clientSecret: String = ""
    internal var flavor: String = ""

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


//    private lateinit var floatingButtonView: FloatingButtonView

    fun initializeSDK2(
        context: Context,
        id: String,
        secret: String,
        flavorData: String,
    ) {
        clientId = id
        clientSecret = secret
        flavor = flavorData
        FlutterEngineHelper.ensureEngine(context.applicationContext)
//        floatingButtonView = FloatingButtonView(context.applicationContext)
    }

    fun getFloatingButton(context: Context): View {
        return FloatingButtonView(context)
    }

//    fun getFloatingButton(): FloatingButtonView {
//        return floatingButtonView
//    }
}