package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat

class CustomProgressBarBoostVolume(context: Context, attr: AttributeSet) : View(context, attr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paintRoundRect = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#D9D9D9")
        }
        canvas?.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            4f,
            4f,
            paintRoundRect
        )

        val startColor = Color.parseColor("#FF920D")
        val endColor = Color.parseColor("#ED3400")
        val painGradient = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                startColor,
                endColor,
                Shader.TileMode.CLAMP
            )
        }

        canvas?.drawRoundRect(
            0f,
            0f,
            0f,
            height.toFloat(),
            4f,
            4f,
            painGradient
        )



    }



    private fun dpToPx(dp: Float): Float {
        val displayMetric = context.resources.displayMetrics.densityDpi
        return dp * (displayMetric / 160)
    }
}