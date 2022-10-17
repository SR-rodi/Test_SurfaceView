package com.example.surface.gameWorld

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import com.example.surface.NUMBER_OF_CELL
import com.example.surface.PADDING_HORIZONTAL_iN_GAME
import com.example.surface.SIZE_GAME_BOARD
import com.example.surface.SIZE_GAME_COUNT

class GameBoard(
    private val sizeBoard: Int = NUMBER_OF_CELL,
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

    fun getPosition(x: Float, y: Float, shape: Shape): Pair<Int, Int>? {
        var position: Pair<Int, Int>? = null
        cellArray.forEachIndexed { row: Int, cells: Array<Cell> ->
            val cellHeight = cellSize!! * row + paddingTop
            cells.forEachIndexed { col: Int, cell ->
                val newX = paddingHorizontal + (cellSize!! * col)
                if (x > newX && x < newX + cellSize!! && y > cellHeight && y < cellHeight + cellSize!!)
                    position = Pair(row, col)
            }
            if (position != null) return@forEachIndexed
        }
        return position
    }

    private fun isInBoard(position: Pair<Int, Int>, shape: Shape) =
        (shape.shapeRow + position.first <= sizeBoard && shape.shapeColumns + position.second <= sizeBoard)

    private fun checkShapeInBoard(position: Pair<Int, Int>, shape: Shape): Boolean {
        val isNext = cellArray[position.first][position.second + shape.shiftPosition] == Cell.EMPTY
        var counterShape = 0
        var counterBoard = 0
        if (isInBoard(position, shape) && isNext) {
            shape.modelArray.forEachIndexed { row, cells ->
                cells.forEachIndexed { col, cell ->
                    if (cell != Cell.EMPTY) counterShape++
                    if (cell != Cell.EMPTY && cellArray[position.first + row][position.second + col] == Cell.EMPTY)
                        counterBoard++
                }
            }
        }
        return counterShape == counterBoard && counterBoard != 0
    }

    fun setCell(position: Pair<Int, Int>, shape: Shape): Boolean {
        val check = checkShapeInBoard(position, shape)
        if (check) {
            shape.modelArray.forEachIndexed { row, cells ->
                cells.forEachIndexed { col, cell ->
                    if (cell != Cell.EMPTY)
                        cellArray[position.first + row][position.second + col] = cell
                }
            }
            deleteLine()
        }
        return check
    }

    private fun deleteLine() {
        for (row in 0..cellArray.lastIndex) {
            var counterhorizontal = 0
            var counterVertical = 0
            for (col in 0..cellArray[0].lastIndex) {
                if (cellArray[row][col] == Cell.FULL) counterhorizontal++
                if (cellArray[col][row] == Cell.FULL) counterVertical++
            }
            val isDelHorizontal = counterhorizontal == cellArray.size
            Log.d("Kart","счетчик $counterVertical размерность ${cellArray[0].size}")
            val isDelVertical = counterVertical == cellArray[0].size
            Log.e("Kart","$isDelVertical")
            if (isDelHorizontal || isDelVertical)
                for (col in 0..cellArray[0].lastIndex) {
                    if (isDelHorizontal) cellArray[row][col] = Cell.EMPTY
                    if (isDelVertical) cellArray[col][row] = Cell.EMPTY
                }
        }
    }

}