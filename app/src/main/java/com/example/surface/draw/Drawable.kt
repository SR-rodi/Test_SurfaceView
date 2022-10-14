package com.example.surface.draw

import android.graphics.*
import com.example.surface.board.Cell
import com.example.surface.board.GameBoard

class Drawable(private val cellArray: Array<Array<Cell>>) {

    private var cellSize = 0f
    private var padding = 0f
    private val cellRect = RectF()
    private var columns = 0
    private var row = 0 // потом сделаю прямоугольную форму
    private val paint = Paint()
    private var height = 0f
    private var width = 0f
    private var footerRect = RectF()


    fun setSize(board: GameBoard) {
        this.padding = board.padding / 2
        this.columns = board.columns
        this.row = board.columns
        this.cellSize = board.cellSize
        this.height = board.height
        this.width = board.width
        this.footerRect = board.footerRect
    }

    private fun drawEmpty(canvas: Canvas?, emptyTile: Bitmap, col: Int, cellHeight: Float) {
        cellRect.left = cellSize * col + padding
        cellRect.top = cellHeight
        cellRect.right = cellRect.left + cellSize
        cellRect.bottom = cellRect.top + cellSize
        canvas?.drawBitmap(emptyTile, null, cellRect, paint)
    }

    private fun drawFull(canvas: Canvas?, fullTile: Bitmap, col: Int, cellHeight: Float) {
        val topPadding = padding / 5
        val bottomPadding = topPadding * 2
        cellRect.left = cellSize * col + padding + topPadding
        cellRect.top = cellHeight + topPadding
        cellRect.right = cellRect.left + cellSize - bottomPadding
        cellRect.bottom = cellRect.top + cellSize - bottomPadding
        canvas?.drawBitmap(fullTile, null, cellRect, paint)
    }

    fun renderBoard(canvas: Canvas?, emptyTile: Bitmap, fullTile: Bitmap) {
        for (row in 0 until this.row) {
            val cellHeight = cellSize * row + padding
            for (col in 0 until this.columns) {
                drawEmpty(canvas, emptyTile, col, cellHeight)
                if (cellArray[row][col] == Cell.FULL)
                    drawFull(canvas, fullTile, col, cellHeight)
            }
        }
    }

    fun backgroundColor(canvas: Canvas?, backgroundColor: Int) {
        canvas?.drawColor(backgroundColor)
    }

    fun footer(canvas: Canvas?, footer: Bitmap?) {
        if (footer != null)
            canvas?.drawBitmap(footer, null, footerRect, paint)
    }

}