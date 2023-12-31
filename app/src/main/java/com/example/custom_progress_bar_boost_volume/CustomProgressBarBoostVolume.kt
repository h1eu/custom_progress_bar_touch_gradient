package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class CustomProgressBarBoostVolume(context: Context, attr: AttributeSet) : View(context, attr) {
    private var widthGradient = 0f

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
            widthGradient + 5f,
            height.toFloat(),
            4f,
            4f,
            painGradient
        )


    }

    fun drawGradient(widthGradient: Float){
        this.widthGradient = widthGradient
        invalidate()
    }



    private fun dpToPx(dp: Float): Float {
        val displayMetric = context.resources.displayMetrics.densityDpi
        return dp * (displayMetric / 160)
    }
}