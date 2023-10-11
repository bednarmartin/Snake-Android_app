package com.example.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap

class Food(private val context: Context) {

    var eaten = false
    var position: Pair<Int, Int> = Pair(2,3)

    fun onDraw(canvas: Canvas, size: Float) {
        val mPaint = Paint()
        val bitmap = AppCompatResources.getDrawable(context, R.drawable.lunch_dining_48px)?.toBitmap(
            size.toInt(), size.toInt()
        )
        canvas.drawBitmap(bitmap!!,((position.first*size)), ((position.second*size)), mPaint)
    }

    fun generate(snake: MutableList<Pair<Int, Int>>, maxX:Int, maxY: Int) {
        val list = mutableListOf<Pair<Int, Int>>()
        for(y in 0..maxY) {
            for (x in 0..maxX) {
                if (!snake.contains(Pair(x, y))) {
                    list.add(Pair(x, y))
                }
            }
        }
        eaten = true
        position = list.random()
    }
}