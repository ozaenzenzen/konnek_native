package com.example.appsample1.component

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MovableFloatingActionButton2(context: Context) : androidx.appcompat.widget.AppCompatImageButton(context), View.OnTouchListener {

    private var downRawX = 0f
    private var downRawY = 0f
    private var dX = 0f
    private var dY = 0f

    init {
        layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        // text = "Open"
        setPadding(20, 20, 20, 20)

        setOnTouchListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val layoutParams = view.layoutParams as MarginLayoutParams
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                downRawX = motionEvent.rawX
                downRawY = motionEvent.rawY
                dX = view.x - downRawX
                dY = view.y - downRawY
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val parent = view.parent as? View ?: return false
                val parentWidth = parent.width
                val parentHeight = parent.height
                val viewWidth = view.width
                val viewHeight = view.height

                var newX = motionEvent.rawX + dX
                var newY = motionEvent.rawY + dY

                newX = newX.coerceIn(
                    layoutParams.leftMargin.toFloat(),
                    (parentWidth - viewWidth - layoutParams.rightMargin).toFloat()
                )
                newY = newY.coerceIn(
                    layoutParams.topMargin.toFloat(),
                    (parentHeight - viewHeight - layoutParams.bottomMargin).toFloat()
                )

                view.animate().x(newX).y(newY).setDuration(0).start()
                return true
            }

            MotionEvent.ACTION_UP -> {
                val upDX = motionEvent.rawX - downRawX
                val upDY = motionEvent.rawY - downRawY
                return if (abs(upDX) < CLICK_DRAG_TOLERANCE && abs(upDY) < CLICK_DRAG_TOLERANCE) {
                    performClick()
                } else true
            }
        }
        return false
    }

    companion object {
        private const val CLICK_DRAG_TOLERANCE = 10f
    }

    enum class ImagePosition {
        LEFT, RIGHT, TOP, BOTTOM
    }
}