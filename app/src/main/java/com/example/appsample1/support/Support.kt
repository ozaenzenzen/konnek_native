package com.example.appsample1.support

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageButton

fun String.base64ToBitmap(): Bitmap? {
    return try {
        // Remove data URI prefix if present (e.g., "data:image/png;base64,...")
        val pureBase64 = this.substringAfterLast(",")

        // Decode Base64 string to byte array
        val decodedBytes = Base64.decode(pureBase64, Base64.DEFAULT)

        // Convert byte array to Bitmap
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        e.printStackTrace()
        null // Return null if decoding fails
    }
}

class Support {
//
}