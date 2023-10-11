package com.example.snake

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Snake {

    private var body = mutableListOf(Pair(7, 7))
    private var direction = Direction.UP

    fun onDraw(canvas: Canvas, sizeOfRect: Float, food: Food, maxX: Int, maxY: Int): Boolean {

        val mPaint = Paint()
        mPaint.color = Color.GREEN

        var tmp = body[0]
        val head = next(body[0])

        if (checkCrash(head, maxX, maxY)) {
            for (i in 0 until body.size) {
                canvas.drawRect(
                    body[i].first * sizeOfRect, body[i].second * sizeOfRect,
                    (body[i].first + 1) * sizeOfRect, (body[i].second + 1) * sizeOfRect, mPaint
                )
            }
            return false
        }

        var new = false
        if (checkFood(head, food.position)) {
            new = true
            food.generate(body, maxX, maxY)
        }

        body[0] = head
        canvas.drawRect(
            body[0].first * sizeOfRect, body[0].second * sizeOfRect,
            (body[0].first + 1) * sizeOfRect, (body[0].second + 1) * sizeOfRect, mPaint
        )

        for (i in 1 until body.size) {
            val tmp1 = body[i]
            body[i] = tmp
            tmp = tmp1
            canvas.drawRect(
                body[i].first * sizeOfRect, body[i].second * sizeOfRect,
                (body[i].first + 1) * sizeOfRect, (body[i].second + 1) * sizeOfRect, mPaint
            )
        }
        if (new) {
            body.add(tmp)
            val index = body.size - 1
            canvas.drawRect(
                body[index].first * sizeOfRect, body[index].second * sizeOfRect,
                (body[index].first + 1) * sizeOfRect, (body[index].second + 1) * sizeOfRect, mPaint
            )
        }

        return true
    }

    fun left() {
        if (direction != Direction.RIGHT) {
            direction = Direction.LEFT
        }
    }

    fun right() {
        if (direction != Direction.LEFT) {
            direction = Direction.RIGHT
        }

    }

    fun up() {
        if (direction != Direction.DOWN) {
            direction = Direction.UP
        }
    }

    fun down() {
        if (direction != Direction.UP) {
            direction = Direction.DOWN
        }
    }

    private fun next(head: Pair<Int, Int>): Pair<Int, Int> {
        return when (direction) {
            Direction.RIGHT -> Pair(head.first + 1, head.second)
            Direction.LEFT -> Pair(head.first - 1, head.second)
            Direction.DOWN -> Pair(head.first, head.second + 1)
            Direction.UP -> Pair(head.first, head.second - 1)
        }
    }

    private fun checkCrash(head: Pair<Int, Int>, maxX: Int, maxY: Int): Boolean {
        if (head.first < 0 || head.first > maxX || head.second < 0 || head.second > maxY) return true
        if (body.contains(head)) return true
        return false
    }

    private fun checkFood(head: Pair<Int, Int>, foodPosition: Pair<Int, Int>): Boolean {
        if (head.first == foodPosition.first && head.second == foodPosition.second) {
            return true
        }
        return false
    }

}