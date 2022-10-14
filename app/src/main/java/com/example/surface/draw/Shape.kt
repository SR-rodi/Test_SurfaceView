package com.example.surface.draw

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.surface.board.Cell
import com.example.surface.board.GameBoard

enum class Model{
    LINE_HOR,LINE_VER,
    SQUARE(),
    CORNER_LEFT,CORNER_RIGHT,
    CROSS
}


class Shape {

    var isMove = false
    private var cellSize = 0f
    var footerRect = RectF()

    private val shapeRect = RectF()

    var x: Float = 0f
    var y: Float = 0f

    var defaultX = 0f
    var defaultY = 0f


    val paint = Paint().apply {
        color = Color.BLUE
        alpha = 100
    }

    fun isBoard(x: Float, y: Float): Boolean =
        x > this.x && x < this.x + (cellSize * 2) && y > this.y && y < this.y + (cellSize * 2)


    fun draw(canvas: Canvas?) {
        shapeRect.left = x
        shapeRect.top = y
        shapeRect.right = shapeRect.left + cellSize
        shapeRect.bottom = shapeRect.top + cellSize
        canvas?.drawRect(shapeRect, paint)
    }

    fun setDefaultPosition(){
        this.x = defaultX
        this.y = defaultY
    }

    fun setSize(board: GameBoard) {
        cellSize = board.cellSize
        x = board.footerRect.left
        defaultX = board.footerRect.left
        y = board.footerRect.top
        defaultY = board.footerRect.top
    }
}