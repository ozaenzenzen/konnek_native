package com.example.appsample1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.appsample1.component.MovableFloatingActionButton
import com.example.appsample1.component.MovableFloatingActionButton2
import com.google.gson.Gson
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

object KonnekNative {
    internal var clientId: String = ""
    internal var clientSecret: String = ""
    internal var flavor: String = ""

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
    }

    fun getFloatingButton(context: Context): MovableFloatingActionButton {
        val btn = MovableFloatingActionButton(context).apply {
            // Customize the FAB if needed
//            setImageResource(android.R.drawable.ic_input_add) // Example icon
            setBackgroundColor(Color.WHITE)
            setImageResource(R.drawable.ic_konnek) // Example icon
            setPadding(16, 16, 16, 16)
            layoutParams = CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.BOTTOM or Gravity.END
                marginEnd = 32
                bottomMargin = 32
            }
            setOnClickListener {
                FlutterEngineHelper.launchFlutter(context)
            }
        }
        return btn
    }

    fun getFloatingButton2(context: Context): ImageButton {
        val btn = MovableFloatingActionButton2(context).apply {
            setImageResource(com.example.appsample1.R.drawable.ic_konnek)
            scaleType = ImageView.ScaleType.FIT_CENTER // or CENTER_CROP, CENTER_INSIDE
            setBackgroundColor(Color.WHITE)
            setPadding(
                20, 20, 20, 20
            )
            layoutParams = CoordinatorLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
                200.toPx(context), // width in pixels
                50.toPx(context),  // height in pixels
            ).apply {
                gravity = Gravity.BOTTOM or Gravity.END
//                marginEnd = 32
//                bottomMargin = 32
            }
            elevation = 16f
            setBackgroundColor(Color.WHITE)
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 10.toPx(context).toFloat()
                setColor(Color.WHITE) // background color
            }
            setOnClickListener {
                FlutterEngineHelper.launchFlutter(context)
            }
        }
        return btn
    }
}

fun Int.toPx(context: Context): Int =
    (this * context.resources.displayMetrics.density).toInt()

