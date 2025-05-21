package com.example.appsample1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.appsample1.component.MovableFloatingActionButton
import com.example.appsample1.component.MovableFloatingActionButton2
import com.example.appsample1.component.MovableFloatingActionButton4
import com.example.appsample1.support.base64ToBitmap
import androidx.core.graphics.toColorInt
import com.example.appsample1.FlutterEngineHelper.envInit
import com.konneknative.R

object KonnekNative {
    internal var clientId: String = ""
    internal var clientSecret: String = ""
    internal var flavor: String = ""

    @JvmStatic
    fun initializeSDK2(
        context: Context,
        id: String,
        secret: String,
        flavorData: String,
    ) {
        clientId = id
        clientSecret = secret
        flavor = flavorData
        envInit(flavor)
        FlutterEngineHelper.ensureEngine(context.applicationContext)
    }

    fun getFloatingButton(context: Context): MovableFloatingActionButton {
        val btn = MovableFloatingActionButton(context).apply {
            // Customize the FAB if needed
            // setImageResource(android.R.drawable.ic_input_add) // Example icon
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

    lateinit var triggerFloatingUIChanges: (Map<*, *>) -> Unit

    fun getFloatingButton2(context: Context): ImageButton {
        var bgColor = Color.WHITE
        var textColor = Color.BLACK
        var textButton = ""
        var iconButton = ""

        var btn = MovableFloatingActionButton2(context)

        //        var btn = MovableFloatingActionButton2(context).apply {
        btn.apply {
            setImageResource(R.drawable.ic_konnek)
            scaleType = ImageView.ScaleType.FIT_CENTER // or CENTER_CROP, CENTER_INSIDE
            setBackgroundColor(bgColor)
            setPadding(
                20, 20, 20, 20
            )
            layoutParams = CoordinatorLayout.LayoutParams(
                200.toPx(context), // width in pixels
                50.toPx(context),  // height in pixels
            ).apply {
                gravity = Gravity.BOTTOM or Gravity.END
            }
            elevation = 16f
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 10.toPx(context).toFloat()
                setColor(bgColor) // background color
            }
            setOnClickListener {
                FlutterEngineHelper.launchFlutter(context)
            }
        }

        triggerFloatingUIChanges = { datas ->
            if (datas != null) {
                println("[callbackConfig]: datas $datas")
                bgColor = Color.parseColor(datas.get("button_color") as String?)
                textColor = Color.parseColor(datas.get("text_button_color") as String?)
                textButton = datas.get("text_button_color") as String? ?: ""
                iconButton = datas.get("ios_icon") as String? ?: ""
                val bitmap = iconButton.base64ToBitmap()

                btn.apply {
                    setBackgroundColor(bgColor)
                    bitmap?.let { output ->
                        setImageBitmap(output)
                    } ?: run {
                        setImageResource(R.drawable.ic_konnek)
                    }
                    background = GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        cornerRadius = 10.toPx(context).toFloat()
                        setColor(bgColor) // background color
                    }

                }
            }
        }

        return btn
    }

    fun disposeEngine() {
        FlutterEngineHelper.disposeEngine()
    }

    @JvmStatic
    fun getFloatingButton3(context: Context): FrameLayout {
//        var bgColor = Color.WHITE
        var bgColor = "#ffffff"
        var textColor = "#000000"
        var textButton = ""
        var iconButton = ""

//        var btn = MovableFloatingActionButton3(context)
        var btn = MovableFloatingActionButton4(context)

//        var btn = MovableFloatingActionButton2(context).apply {
        btn.apply {
//            setImageResource(com.example.appsample1.R.drawable.ic_konnek)
//            scaleType = ImageView.ScaleType.FIT_CENTER // or CENTER_CROP, CENTER_INSIDE
            setBackgroundColor(bgColor.toColorInt())
            setPadding(
                20, 20, 20, 20
            )
            layoutParams = CoordinatorLayout.LayoutParams(
//                LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT
                180.toPx(context), // width in pixels
                70.toPx(context),  // height in pixels
//                LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.BOTTOM or Gravity.END
            }
            elevation = 16f
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 10.toPx(context).toFloat()
                setColor((bgColor).toColorInt()) // background color
            }
            setOnClickListener {
                FlutterEngineHelper.launchFlutter(context)
            }
        }

        triggerFloatingUIChanges = { datas ->
            if (datas != null) {
                // bgColor = Color.parseColor(datas.get("button_color") as String?)
                bgColor = datas["button_color"] as String? ?: ""
                // textColor = Color.parseColor(datas.get("text_button_color") as String?)
                textColor = datas["text_button_color"] as String? ?: ""
                textButton = datas["text_button"] as String? ?: ""
                iconButton = datas["ios_icon"] as String? ?: ""
                val bitmap = iconButton.base64ToBitmap()

                btn.apply {
//                    setBackgroundColor(bgColor)
                    setButtonTextColor(textColor)
                    setButtonBackgroundColor(bgColor)
                    setButtonText(textButton)
                    setBackgroundImage(null)
                    bitmap?.let { output ->
                        setButtonIcon2(output)
                    } ?: run {
                        // setButtonIcon(R.drawable.ic_konnek)
                    }
                    background = GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        cornerRadius = 10.toPx(context).toFloat()
                        setColor(bgColor.toColorInt()) // background color
                    }
                }
            }
        }

        // Jika pakai constraint layout
        if (btn.layoutParams is ConstraintLayout.LayoutParams) {
            val layoutParams = btn.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        }

        return btn
    }
}

fun Int.toPx(context: Context): Int =
    (this * context.resources.displayMetrics.density).toInt()

