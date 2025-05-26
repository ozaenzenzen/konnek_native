package com.example.appsample1.support

import android.util.Log

class AppLoggerCS {
    companion object {
        fun debugLog(
            value: String,
            isActive: Boolean = true
        ) {
            if (useLogger) {
                if (isActive) {
                    if (useFoundation) {
                        println(value) // Kotlin equivalent of debugPrint
                    } else {
                        Log.d("AppLoggerCS", value) // Android Log equivalent
                    }
                }
            }
        }

        fun debugLogSpecific(
            value: String,
            processName: String,
            functionName: String,
            isActive: Boolean = true
        ) {
            if (useLogger) {
                if (isActive) {
                    val message = "[$functionName][$processName]: $value"
                    if (useFoundation) {
                        println(message)
                    } else {
                        Log.d("AppLoggerCS", message)
                    }
                }
            }
        }

        /// WARNING!!! THIS SHOULD BE FALSE ON PRODUCTION
        var useFoundation: Boolean = false

        /// WARNING!!! THIS SHOULD BE FALSE ON PRODUCTION
        var useLogger: Boolean = false
    }
}