package com.example.surface.gameWorld

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.example.surface.PADDING_HORIZONTAL_iN_GAME
import com.example.surface.SIZE_GAME_BOARD
import com.example.surface.SIZE_GAME_COUNT


class GameBoard(
    private val sizeBoard: Int = 6
) : GameWorld() {

    val cellArray = Array(sizeBoard) { Array(sizeBoard) { Cell.EMPTY } }

    var cellSize: Float? = null

    override fun setSize(newWidth: Float, newHeight: Float) {
        width = newWidth
        height = newHeight * SIZE_GAME_BOARD
        paddingTop = newHeight * SIZE_GAME_COUNT
        if (height < width) {
            cellSize = height / sizeBoard
            paddingHorizontal = (width - (cellSize!! * sizeBoard)) / 2
        } else {
            paddingHorizontal = width * PADDING_HORIZONTAL_iN_GAME
            cellSize = (width - paddingHorizontal) / sizeBoard
        }
    }

    override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?) {
        if (cellSize != null) {
            cellArray.forEachIndexed { row: Int, cells: Array<Cell> ->
                val cellHeight = cellSize!! * row + paddingTop
                cells.forEachIndexed { col: Int, cell: Cell ->
                    rectangle.left = paddingHorizontal + (cellSize!! * col)
                    rectangle.top = cellHeight
                    rectangle.right = rectangle.left + cellSize!!
                    rectangle.bottom = rectangle.top + cellSize!!
                    if (cell == Cell.EMPTY) canvas?.drawBitmap(oneTile, null, rectangle, Paint())
                    else twoTile?.let { canvas?.drawBitmap(it, null, rectangle, Paint()) }
                }
            }
        }
    }
}
