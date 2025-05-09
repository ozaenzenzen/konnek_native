package com.example.appsample1

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

//class FloatingButtonView(context: Context) : FrameLayout(context) {
//    private var dX = 0f
//    private var dY = 0f
//
//    init {
//        val button = Button(context).apply {
//            text = "Open Flutter"
//            setOnClickListener {
//                FlutterEngineHelper.launchFlutter(context)
//            }
//        }
//
//        addView(button)
//
//        setOnTouchListener { view, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    dX = view.x - event.rawX
//                    dY = view.y - event.rawY
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    view.animate()
//                        .x(event.rawX + dX)
//                        .y(event.rawY + dY)
//                        .setDuration(0)
//                        .start()
//                }
//            }
//            true
//        }
//    }
//}

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

//class FloatingButtonView(private val context: Context) {
//
//    private var windowManager: WindowManager? = null
//    private var floatingButton: View? = null
//    private var layoutParams: WindowManager.LayoutParams? = null
//
//    fun show() {
//        if (floatingButton != null) return // Already showing
//
//        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//
//        layoutParams = WindowManager.LayoutParams(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//            else
//                WindowManager.LayoutParams.TYPE_PHONE,
//            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//            PixelFormat.TRANSLUCENT
//        ).apply {
//            gravity = Gravity.TOP or Gravity.START
//            x = 100
//            y = 300
//        }
//
//        // Create a Button dynamically
//        floatingButton = Button(context).apply {
//            text = "Click Me"  // Set text for the button
//            setBackgroundColor(Color.BLUE)  // Set a background color for visibility
//            setTextColor(Color.WHITE)  // Set text color
//            setPadding(20, 20, 20, 20)  // Add some padding to make it clickable
//        }
//
//        // Add touch listener to make it draggable
//        floatingButton?.setOnTouchListener(DraggableTouchListener(layoutParams!!, windowManager!!))
//
//        // Set a click listener to launch Flutter when tapped
//        floatingButton?.setOnClickListener {
//            // Launch Flutter engine or FlutterActivity here (via MethodChannel, etc.)
//        }
//
//        windowManager?.addView(floatingButton, layoutParams)
//    }
//
//    fun hide() {
//        if (floatingButton != null) {
//            windowManager?.removeView(floatingButton)
//            floatingButton = null
//        }
//    }
//}


//class FloatingButtonView(context: Context) : View(context) {
//
//    private var windowManager: WindowManager? = null
//    private var layoutParams: WindowManager.LayoutParams? = null
//
//    private var initialX = 0
//    private var initialY = 0
//    private var initialTouchX = 0f
//    private var initialTouchY = 0f
//
//    init {
//        // Initialize the WindowManager
//        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//
//        // Set the background color and size
//        setBackgroundColor(Color.BLUE)
//        setLayoutParams(LayoutParams(200, 200)) // Set the size of the floating button
//
//        // Set up a click listener to launch Flutter (you can add MethodChannel calls here)
//        setOnClickListener {
//            FlutterEngineHelper.launchFlutter(context)
//        }
//
//        // Set up the draggable touch listener
//        setOnTouchListener { view, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    initialX = layoutParams!!.x
//                    initialY = layoutParams!!.y
//                    initialTouchX = event.rawX
//                    initialTouchY = event.rawY
//                    true
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    // Calculate the new position based on the touch movement
//                    layoutParams!!.x = initialX + (event.rawX - initialTouchX).toInt()
//                    layoutParams!!.y = initialY + (event.rawY - initialTouchY).toInt()
//
//                    // Update the layout position
//                    windowManager?.updateViewLayout(this, layoutParams)
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//
//    // This function is called to show the button on screen
//    fun show() {
//        layoutParams = WindowManager.LayoutParams(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//            else
//                WindowManager.LayoutParams.TYPE_PHONE,
//            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//            PixelFormat.TRANSLUCENT
//        ).apply {
//            gravity = Gravity.TOP or Gravity.START
//            x = 100
//            y = 300
//        }
//
//        windowManager?.addView(this, layoutParams)
//    }
//
//    // This function is called to remove the button from screen
//    fun hide() {
//        if (windowManager != null) {
//            windowManager?.removeView(this)
//        }
//    }
//}

//class FloatingButtonView(context: Context) : FrameLayout(context) {
//
//    private var dX = 0f
//    private var dY = 0f
//
//    // Initialize the button with basic properties
//    private val button: Button
//
//    init {
//        button = Button(context).apply {
//            text = "Open Flutter"
//            setBackgroundColor(Color.BLUE)
//            setTextColor(Color.WHITE)
//            setOnClickListener {
//                FlutterEngineHelper.launchFlutter(context)
//            }
//            layoutParams = LayoutParams(
//                LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT
//            ).apply {
//                gravity = Gravity.CENTER
//            }
//        }
//
//        addView(button)
//
//        setOnTouchListener { view, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    dX = view.x - event.rawX
//                    dY = view.y - event.rawY
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    // Prevent the button from going out of screen bounds
//                    val newX = event.rawX + dX
//                    val newY = event.rawY + dY
//
//                    // Ensure the button stays within the screen bounds
//                    val maxX = (view.rootView.width - view.width).toFloat()
//                    val maxY = (view.rootView.height - view.height).toFloat()
//
//                    val clampedX = newX.coerceIn(0f, maxX)
//                    val clampedY = newY.coerceIn(0f, maxY)
//
//                    view.animate()
//                        .x(clampedX)
//                        .y(clampedY)
//                        .setDuration(0)
//                        .start()
//                }
//            }
//            true
//        }
//    }
//
//    // Method to update the button text
//    fun setButtonText(text: String) {
//        button.text = text
//    }
//
//    // Method to update the button's background color
//    fun setButtonBackgroundColor(color: Int) {
//        button.setBackgroundColor(color)
//    }
//
//    // Method to set custom size for the button
//    fun setButtonSize(width: Int, height: Int) {
//        button.layoutParams = LayoutParams(width, height)
//    }
//
//    // Method to remove the floating button from the screen
//    fun remove() {
//        (parent as? ViewGroup)?.removeView(this)
//    }
//}

class FloatingButtonView2(context: Context) : androidx.coordinatorlayout.widget.CoordinatorLayout(context) {

    private val draggableFab: FloatingActionButton
    private var dX: Float = 0f
    private var dY: Float = 0f
    private var lastAction: Int = 0
    private var onFabClickListener: OnClickListener? = null

    init {
        // Initialize the FloatingActionButton
        draggableFab = FloatingActionButton(context)
        draggableFab.setImageResource(android.R.drawable.ic_input_add) // Customize icon if needed
        draggableFab.setOnClickListener {
            FlutterEngineHelper.launchFlutter(context)
        }

        // Set layout parameters for the FAB within the CoordinatorLayout
        val fabLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//        fabLayoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen.fab_margin) // Ensure you have this dimension
//        fabLayoutParams.rightMargin = resources.getDimensionPixelOffset(R.dimen.fab_margin)    // Ensure you have this dimensio
        fabLayoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen.fab_margin) // Ensure you have this dimension
        fabLayoutParams.rightMargin = resources.getDimensionPixelOffset(R.dimen.fab_margin)    // Ensure you have this dimension
//        fabLayoutParams.gravity = androidx.coordinatorlayout.widget.Gravity.END or android.view.Gravity.BOTTOM
        fabLayoutParams.gravity = Gravity.BOTTOM
        draggableFab.layoutParams = fabLayoutParams

        // Add the FAB to this FloatingButtonView (which is a CoordinatorLayout)
        addView(draggableFab)

        setupDragListener()
    }

    private fun setupDragListener() {
        draggableFab.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY
                        lastAction = MotionEvent.ACTION_DOWN
                    }
                    MotionEvent.ACTION_MOVE -> {
                        view.y = event.rawY + dY
                        view.x = event.rawX + dX
                        lastAction = MotionEvent.ACTION_MOVE
                    }
                    MotionEvent.ACTION_UP -> {
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            performClick() // Programmatically perform the default click action
                        }
                    }
                    else -> return false
                }
                view.invalidate()
                return true // Consume the touch event
            }
        })
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onFabClickListener = l
        // Don't call super.setOnClickListener on the FAB, we handle clicks in onTouch
    }

    override fun performClick(): Boolean {
        onFabClickListener?.onClick(draggableFab) // Call the listener with the FAB as the view
        return draggableFab.performClick() // Trigger any accessibility actions on the FAB
    }

    // Method to customize the FAB's icon
    fun setFabIcon(resourceId: Int) {
        draggableFab.setImageResource(resourceId)
    }

    // Method to get the underlying FloatingActionButton if needed for more advanced customization
    fun getFab(): FloatingActionButton {
        return draggableFab
    }
}

class FloatingButtonView(context: Context) : CoordinatorLayout(context) {

    private val draggableFab: FloatingActionButton
    private var dX: Float = 0f
    private var dY: Float = 0f
    private var lastAction: Int = 0
    private var onFabClickListener: OnClickListener? = null

    init {
        // Initialize the FloatingActionButton
        draggableFab = FloatingActionButton(context)
        draggableFab.setImageResource(android.R.drawable.ic_input_add) // Customize icon if needed
        draggableFab.setOnClickListener {
            FlutterEngineHelper.launchFlutter(context)
        }

        // Set layout parameters for the FAB within the CoordinatorLayout
        val fabLayoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        val margin = resources.getDimensionPixelOffset(R.dimen.fab_margin) // Get margin value
        fabLayoutParams.setMargins(margin, margin, margin, margin)
        fabLayoutParams.gravity = Gravity.BOTTOM or Gravity.END // Use CoordinatorLayout's gravity

        draggableFab.layoutParams = fabLayoutParams

        // Add the FAB to this FloatingButtonView (which is a CoordinatorLayout)
        addView(draggableFab)

        setupDragListener()
    }

    private fun setupDragListener() {
        draggableFab.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY
                        lastAction = MotionEvent.ACTION_DOWN
                    }
                    MotionEvent.ACTION_MOVE -> {
                        view.y = event.rawY + dY
                        view.x = event.rawX + dX
                        lastAction = MotionEvent.ACTION_MOVE
                    }
                    MotionEvent.ACTION_UP -> {
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            performClick() // Programmatically perform the default click action
                        }
                    }
                    else -> return false
                }
                view.invalidate()
                return true // Consume the touch event
            }
        })
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onFabClickListener = l
        // Don't call super.setOnClickListener on the FAB, we handle clicks in onTouch
    }

    override fun performClick(): Boolean {
        onFabClickListener?.onClick(draggableFab) // Call the listener with the FAB as the view
        return draggableFab.performClick() // Trigger any accessibility actions on the FAB
    }

    // Method to customize the FAB's icon
    fun setFabIcon(resourceId: Int) {
        draggableFab.setImageResource(resourceId)
    }

    // Method to get the underlying FloatingActionButton if needed
    fun getFab(): FloatingActionButton {
        return draggableFab
    }
}