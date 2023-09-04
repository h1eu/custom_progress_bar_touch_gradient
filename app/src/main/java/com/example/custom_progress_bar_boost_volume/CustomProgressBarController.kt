package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CustomProgressBarController(context: Context,attr: AttributeSet): View(context,attr) {
    private var startX: Float? = null
    private var offSetX: Float? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val drawableProgressPoint = ContextCompat.getDrawable(context, R.drawable.ic_progress_bar)
            ?.let{ drawableToBitmap(it) }

        if (drawableProgressPoint != null){
            canvas?.drawBitmap(
                drawableProgressPoint,
                0f,0f,
                null
            )
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val temporaryX = event?.x
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                startX = temporaryX
                offSetX = 0f
                Log.e("motion", "onTouchEvent: cham xuong", )
            }
            MotionEvent.ACTION_MOVE -> {
                val dX = temporaryX?.minus(startX!!)
                dX?.let { offsetLeftAndRight(it.toInt()) }

                if (dX != null) {
                    offSetX = offSetX?.plus(dX)
                }


                startX = temporaryX
                Log.e("motion", "onTouchEvent: sau khi di chuyen", )
            }
        }


        return true
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