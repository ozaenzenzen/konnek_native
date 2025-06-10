package com.example.appsample1.support

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KonnekService {
    val gson = Gson()

    fun getConfig(
//        call: MethodCall,
        clientIdValue: String,
        onSuccess: (data: String) -> Unit?,
        onFailed: (errorMessage: String) -> Unit?,
    ) {
        try {
            println("[FlutterEngineHelper][getConfig] called")
//            val map: Map<*, *> = call.arguments as Map<*, *>
//            val clientId: String? = map.get("clientId") as String?
//            val platform: String? = map.get("platform") as String?
            val clientId: String = clientIdValue
            val platform: String = "android"

            if (clientId == null && platform == null) {
                onFailed.invoke("empty params")
                return
            }

            // KonnekNative.clientId = clientId ?: ""

            CoroutineScope(Dispatchers.IO).launch {
                val apiService = ApiConfig.provideApiService()
                val response = apiService.getConfig(
                    clientId ?: "",
                    platform = platform ?: "",
                )
//                println("[getConfig] response: $response")
                if (response.isSuccessful) {

                    val data = response.body()
                    val json: String = gson.toJson(data)
                    println("[getConfig] json: $json")
                    withContext(Dispatchers.Main) { // to the main thread for UI update
                        // KonnekNative.callbackConfig.invoke(output["data"] as Map<*, *>)
                        onSuccess.invoke(json)
                    }
//                    onSuccess.invoke(json)
                } else {
                    onFailed.invoke(response.message())
                }
            }
        } catch (e: Exception) {
            onFailed.invoke(e.toString())
            return
        }
    }
}