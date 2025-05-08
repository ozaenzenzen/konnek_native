package com.example.appsample1

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView

class FloatingButtonView(context: Context) : FrameLayout(context) {
    private var dX = 0f
    private var dY = 0f

    init {
        val button = Button(context).apply {
            text = "Open Flutter"
            setOnClickListener {
                FlutterEngineHelper.launchFlutter(context)
            }
        }

        addView(button)

        setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    view.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                }
            }
            true
        }
    }
}

//class FloatingButtonView(context: Context) : FrameLayout(context) {
//
//    private var layoutParams: WindowManager.LayoutParams? = null
//    private var windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//
//    init {
//        val button = ImageView(context).apply {
//            setImageResource(android.R.drawable.ic_dialog_info) // Customize this
//            layoutParams = LayoutParams(150, 150)
//            setBackgroundColor(Color.LTGRAY)
//        }
//
//        addView(button)
//
//        button.setOnClickListener {
////            FlutterEngineHolder.invokeFlutter("onFloatingButtonClicked", null)
//            FlutterEngineHelper.launchFlutter(context)
//        }
//
//        // Enable dragging
//        setOnTouchListener { view, event ->
//            layoutParams?.let {
//                when (event.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        initialTouchX = event.rawX
//                        initialTouchY = event.rawY
//                        initialX = it.x
//                        initialY = it.y
//                        true
//                    }
//
//                    MotionEvent.ACTION_MOVE -> {
//                        val deltaX = (event.rawX - initialTouchX).toInt()
//                        val deltaY = (event.rawY - initialTouchY).toInt()
//                        it.x = initialX + deltaX
//                        it.y = initialY + deltaY
//                        windowManager.updateViewLayout(this, it)
//                        true
//                    }
//
//                    else -> false
//                }
//            } ?: false
//        }
//    }
//
//    private var initialX = 0
//    private var initialY = 0
//    private var initialTouchX = 0f
//    private var initialTouchY = 0f
//
//    fun show() {
//        if (layoutParams == null) {
//            layoutParams = WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//                else
//                    WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                PixelFormat.TRANSLUCENT
//            ).apply {
//                gravity = Gravity.TOP or Gravity.START
//                x = 200
//                y = 400
//            }
//
//            windowManager.addView(this, layoutParams)
//        }
//    }
//
//    fun hide() {
//        layoutParams?.let {
//            windowManager.removeView(this)
//            layoutParams = null
//        }
//    }
//}
