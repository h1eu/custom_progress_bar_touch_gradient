package com.example.custom_progress_bar_boost_volume

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class PointRoundScreen(context: Context, attr: AttributeSet) : View(context, attr) {
    private val rectH = 30
    private val rectW = 30
    private var xP = 0f
    private var yP = 0f
    private val pWidth = 50f
    private val animatorDuration = 3000L

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paintRect = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#42f5b9")
        }
        val rectTop = Rect(0, 0, width, rectH)
        val rectLeft = Rect(0, 0, rectW, height)
        val rectRight = Rect(width - rectW, 0, width, height)
        val rectBot = Rect(0, height - rectH, width, height)
        canvas?.drawRect(rectTop, paintRect)
        canvas?.drawRect(rectLeft, paintRect)
        canvas?.drawRect(rectRight, paintRect)
        canvas?.drawRect(rectBot, paintRect)


        val paintPoint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#f3ff05")
            strokeWidth = pWidth
        }
        canvas?.drawPoint(xP + pWidth / 2, yP + pWidth / 2, paintPoint)
    }

    fun startTransition() {
        val animatorLeftToBot = ValueAnimator.ofFloat(yP, height.toFloat() - pWidth)
        val animatorLeftBotToRight = ValueAnimator.ofFloat(xP, width.toFloat() - pWidth)
        val animatorRightBotToTop = ValueAnimator.ofFloat(height.toFloat() - pWidth, 0f )
        val animatorRightTopToLeft = ValueAnimator.ofFloat(width.toFloat() - pWidth, 0f )
        animatorLeftBotToRight.duration = animatorDuration
        animatorLeftToBot.duration = animatorDuration
        animatorRightBotToTop.duration = animatorDuration
        animatorRightTopToLeft.duration = animatorDuration
//        val listAnimator = listOf<ValueAnimator>(
//            animatorLeftToBot,
//            animatorLeftBotToRight,
//            animatorRightBotToTop,
//            animatorTopRightToLeft
//        )
//        for (i in listAnimator.indices){
//            listAnimator[i].duration = animatorDuration
//            if (i % 2 == 0){
//                listAnimator[i].addUpdateListener {
//                    yP = animatorLeftToBot.animatedValue as Float
//                    invalidate()
//                }
//            } else {
//                listAnimator[i].addUpdateListener {
//                    xP = animatorLeftToBot.animatedValue as Float
//                    invalidate()
//                }
//            }
//            listAnimator[i].addListener(object : AnimatorListener {
//                override fun onAnimationStart(p0: Animator) {}
//
//                override fun onAnimationEnd(p0: Animator) {
//                    listAnimator[i + 1].start()
//                }
//
//                override fun onAnimationCancel(p0: Animator) {}
//
//                override fun onAnimationRepeat(p0: Animator) {}
//            })
//            if (i == 0){
//                listAnimator[i].start()
//            }
//        }

        animatorLeftToBot.addUpdateListener {
            yP = animatorLeftToBot.animatedValue as Float
            invalidate()
        }

        animatorLeftToBot.addListener(object : AnimatorListener{
            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) {
                animatorLeftBotToRight.addUpdateListener {
                    xP = animatorLeftBotToRight.animatedValue as Float
                    invalidate()
                }
                animatorLeftBotToRight.start()
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}

        })

        animatorLeftBotToRight.addListener(object : AnimatorListener{
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                animatorRightBotToTop.addUpdateListener {
                    yP = animatorRightBotToTop.animatedValue as Float
                    invalidate()
                }
                animatorRightBotToTop.start()
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}

        })

        animatorRightBotToTop.addListener(object : AnimatorListener{
            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) {
                animatorRightTopToLeft.addUpdateListener {
                    xP = animatorRightTopToLeft.animatedValue as Float
                    invalidate()
                }
                animatorRightTopToLeft.start()
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}

        })

        animatorRightTopToLeft.addListener(object : AnimatorListener{
            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) {
                animatorLeftToBot.addUpdateListener {
                    yP = animatorLeftToBot.animatedValue as Float
                    invalidate()
                }
                animatorLeftToBot.start()
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}

        })


        animatorLeftToBot.start()


    }
}