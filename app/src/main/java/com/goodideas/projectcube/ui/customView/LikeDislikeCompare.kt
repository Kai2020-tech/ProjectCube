package com.goodideas.projectcube.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import timber.log.Timber

class LikeDislikeCompare @JvmOverloads constructor(
    context: Context,attrs:AttributeSet? = null
    ) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()

    var _like = 3
    var _dislike = 2
    var like
        get() =_like
        set(value) {
            _like = value
            invalidate()
        }
    var dislike
        get() =_dislike
        set(value) {
            _dislike = value
            invalidate()
        }
    fun setLikeAndDislike(l:Int =0, d:Int = 0){
        like = l
        dislike = d
    }

    var w = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val total = like + dislike
        paint.strokeWidth = 20f

        try {
            val firstPart = (w/total)*like
            Timber.d(w.toString())
            paint.color = Color.GREEN
            canvas?.drawLine(0f,0f,firstPart.toFloat(),0f,paint)

            paint.color = Color.RED
            canvas?.drawLine(firstPart.toFloat(),0f,w.toFloat(),0f,paint)
        } catch (e:Exception){
            paint.color = Color.GRAY
            canvas?.drawLine(0f,0f,w.toFloat(),0f,paint)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        w = MeasureSpec.getSize(widthMeasureSpec)
    }
}