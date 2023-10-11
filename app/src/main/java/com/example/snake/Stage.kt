package com.example.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.google.android.material.color.MaterialColors

class Stage() {

    fun onDraw(canvas: Canvas, sizeOfRect: Float, maxX: Int, maxY: Int) {
        val mPaint = Paint()
        mPaint.color = Color.BLACK
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                canvas.drawRect(
                    x * sizeOfRect, y * sizeOfRect,
                    (x + 1) * sizeOfRect, (y + 1) * sizeOfRect, mPaint
                )
            }
        }
    }
}