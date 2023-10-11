package com.example.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View

class Playground(internal var context: Context, attrs: AttributeSet) : View(context, attrs),
    View.OnKeyListener {

    var score = 0
    private var pwidth = 0
    private var pheight = 0
    private var size = 15
    private var rectSize = 0f
    private var countHeight = 0
    internal var stage: Stage? = null
    internal var snake: Snake? = null
    internal var food: Food? = null


    init {
        setBackgroundColor(Color.BLACK)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pwidth = w
        pheight = h
        rectSize = w.coerceAtMost(h).toFloat() / size
        countHeight = (pheight / rectSize).toInt()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (stage == null) {
            stage = Stage()
        }
        if (snake == null) {
            snake = Snake()
        }
        if (food == null) {
            food = Food(context)
        }

        stage?.onDraw(canvas, rectSize, size - 1, countHeight - 1)
        if (food!!.eaten) {
            score++
            (context as MainActivity).setScore(score)
            (context as MainActivity).playEaten()
            food!!.eaten = false
        }
        food?.onDraw(canvas, rectSize)
        val gameOver = snake?.onDraw(canvas, rectSize, food!!, size - 1, countHeight - 1)
        if (!gameOver!!) {
            (context as MainActivity).setGameOver()
        }
    }


    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            else -> return false
        }
    }

    fun up() {
        snake?.up()
    }

    fun down() {
        snake?.down()
    }

    fun left() {
        snake?.left()
    }

    fun right() {
        snake?.right()
    }

}