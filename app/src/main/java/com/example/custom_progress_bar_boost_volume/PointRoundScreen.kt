package com.example.custom_progress_bar_boost_volume

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class PointRoundScreen(context: Context, attr: AttributeSet) : View(context, attr) {
    private val rectH = 30
    private val rectW = 30
    private var xP = 0f
    private var yP = 0f
    private val pWidth = 50f
    private var animatorLeft = ValueAnimator()
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paintRect = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#42f5b9")
        }
        val rectTop = Rect(0,0,width,rectH)
        val rectLeft = Rect(0,0,rectW,height)
        val rectRight = Rect(width - rectW,0,width,height)
        val rectBot = Rect(0,height - rectH,width,height)
        canvas?.drawRect(rectTop,paintRect)
        canvas?.drawRect(rectLeft,paintRect)
        canvas?.drawRect(rectRight,paintRect)
        canvas?.drawRect(rectBot,paintRect)


        val paintPoint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#f3ff05")
            strokeWidth = pWidth
        }
        canvas?.drawPoint(xP + pWidth / 2,yP + pWidth/ 2 ,paintPoint)
    }

    fun startTransition(){
        animatorLeft = ValueAnimator.ofFloat(yP,height.toFloat() - pWidth)
        animatorLeft.duration = 10000
        animatorLeft.addUpdateListener {
            yP =  animatorLeft.animatedValue as Float
            invalidate()
        }
        animatorLeft.start()
    }
}