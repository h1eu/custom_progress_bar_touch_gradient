package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CustomProgressBarController(context: Context, attr: AttributeSet) : View(context, attr) {
    private var leftController: Int? = null
    private var rightController: Int? = null
    var onTouchController: (Float) -> Unit = {}
    var onProgress: (Float) -> Unit = {}
    private var viewLeft = 0f
    private var viewTop = 0F
    private var viewStart = 0f
    private var viewDraw = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val drawableProgressPoint = ContextCompat.getDrawable(context, R.drawable.ic_progress_bar)
            ?.let { drawableToBitmap(it) }

        if (drawableProgressPoint != null) {
            leftController = this.left
            rightController = this.right + this.width - drawableProgressPoint.width
            canvas?.drawBitmap(
                drawableProgressPoint,
                viewLeft, viewTop,
                null
            )

            viewDraw = drawableProgressPoint.width.toFloat()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                if (event.x > viewStart && event.x < width){
                    onTouchController(event.x)
                    onProgress(calculatorOnProgress(event.x))
                    viewLeft = event.x
                    invalidate()
                }
                else{
                    if (event.x <= viewStart){
                        onTouchController(event.x)
                        onProgress(calculatorOnProgress(viewStart))
                        viewLeft = viewStart
                        invalidate()
                    }
                    if (event.x >= width){
                        onTouchController(event.x)
                        onProgress(calculatorOnProgress(width.toFloat()))
                        viewLeft = width - viewDraw
                        invalidate()
                    }

                }

            }
        }
        return true
    }

    private fun calculatorOnProgress(value: Float): Float{
        return value/width * 100
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap? {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var width = drawable.intrinsicWidth
        width = if (width > 0) width else 1
        var height = drawable.intrinsicHeight
        height = if (height > 0) height else 1
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}