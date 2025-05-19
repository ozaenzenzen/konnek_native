package com.example.appsample1.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.konneknative.R
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MovableFloatingActionButton3(context: Context) : FrameLayout(context), View.OnTouchListener {

    private var downRawX = 0f
    private var downRawY = 0f
    private var dX = 0f
    private var dY = 0f

    private val imageView = AppCompatImageView(context)
    private val textView = AppCompatTextView(context)

    init {
        // Setup layout params for this floating button container
        layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        // ImageView setup
        imageView.setImageResource(R.drawable.ic_konnek) // Replace with your icon
        imageView.layoutParams = LayoutParams(120, 120).apply {
            gravity = Gravity.CENTER
        }

        // TextView setup
        textView.text = "Open"
        textView.setTextColor(Color.WHITE)
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
//        textView.setTypeface(Typeface.DEFAULT_BOLD)
        textView.setPadding(4, 4, 4, 4)
        textView.gravity = Gravity.CENTER
        textView.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }

//        // Optional background for button container
//        setBackgroundResource(R.drawable.fab_background) // Your background drawable

        // Add views to this container
        addView(imageView)
        addView(textView)

        setOnTouchListener(this)
    }

    fun setButtonText(text: String) {
        textView.text = text
    }

    fun setButtonTextColor(colorHex: String) {
        textView.setTextColor(Color.parseColor(colorHex))
    }

    fun setButtonBackgroundColor(colorHex: String) {
        setBackgroundColor(Color.parseColor(colorHex))
    }

    fun setButtonIcon(drawableRes: Int) {
        imageView.setImageResource(drawableRes)
    }

    fun setButtonIcon2(drawableRes: Bitmap) {
        imageView.setImageBitmap(drawableRes)
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
}
