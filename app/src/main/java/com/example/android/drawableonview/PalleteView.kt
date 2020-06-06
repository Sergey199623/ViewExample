package com.example.android.drawableonview

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PalleteView (context: Context?, attrs: AttributeSet?) : View {

    val pallete = mutableListOf<PalleteColor> ()
    var radius: Int = 0
    val paint = Paint()

    var selectedColor : Int = 0

    private var colorListener : OnColorChangedListener? = null

    fun setOnChangeColorListener(listener: OnColorChangedListener) {
        colorListener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heighMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heighMeasureSpec)

        measuredWidth
        val radius = measuredHeight / 2
        pallete.add(PalleteColor(Color.GREEN, radius.toFloat(), radius.toFloat()))
        pallete.add(PalleteColor(Color.RED, 3 * radius.toFloat(), radius.toFloat()))
        pallete.add(PalleteColor(Color.YELLOW, 5 * radius.toFloat(), radius.toFloat()))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        pallete.forEach {
            paint.color = it.color
            canvas?.drawCircle(it.x, it.y, radius.toFloat(), paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?) : Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }

            MotionEvent.ACTION_UP -> {
                pallete.forEach {
                    val diffX = event.x - it.x
                    val diffY = event.y - it.y
                    val distance = Math.sqrt((diffX * diffX + diffY * diffY).toDouble())
                    if (distance <= radius) {
                        selectedColor = it.color
                        colorListener?.colorChanged(selectedColor)
                        return@forEach
                    }
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    class PalleteColor(
            val color: Int,
            val x: Float,
            val y: Float
    )

    interface OnColorChangedListener {
        fun colorChanged(color: Int)
    }

}