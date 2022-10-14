package com.example.surface.board

import android.graphics.RectF
import android.icu.text.Transliterator.Position
import android.util.Log
import com.google.android.material.internal.ViewUtils.RelativePadding
import kotlin.math.min


class GameBoard(
    private val row: Int? = null,
    val columns: Int = 6
) {
    val listener = mutableSetOf<onShapeListener>()

    var width = 0f
    var height = 0f
    var padding = 0f
    var cellSize = 0f
    val footerRect = RectF()


    val cellArray = Array(columns) { Array(columns) { Cell.EMPTY } }

    fun getPosition(x: Float, y: Float): Pair<Int, Int>? {
        var position: Pair<Int, Int>? = null
        for (line in 0 until columns) {
            val y1 = cellSize * line + padding
            val y2 = cellSize + padding + cellSize * line
            for (col in 0 until columns) {
                val x1 = cellSize * col + padding
                val x2 = cellSize + padding + cellSize * col
                if (x > x1 && y > y1 && x < x2 && y < y2) {
                    position = Pair(line, col)
                    break
                }
            }
            if (position != null) break
        }
        if (position != null){
            if (checkPosition(position)) setCell(position)
        }
        return position
    }

    private fun checkPosition(position: Pair<Int, Int>): Boolean {
        var isEmpty = true
        if (position.first + 1 < columns && position.second + 1 < columns)
            for (line in position.first..position.first + 1){
                for (col in position.second..position.second + 1) {
                    if (cellArray[line][col] == Cell.FULL){
                        isEmpty = false
                        break
                    }
                }
                if (!isEmpty) break
            }
            return isEmpty
    }

    private fun setCell(position: Pair<Int, Int>) {
        if (position.first + 1 < columns && position.second + 1 < columns)
            for (line in position.first..position.first + 1)
                for (col in position.second..position.second + 1) {
                    cellArray[line][col] = Cell.FULL
                }
    }


    fun setSize(width: Int, height: Int) {
        this.padding = width * PADDING_PROPORTION
        this.width = width.toFloat() - padding
        this.height = height.toFloat() - padding

        createFooterRect(this.width, this.height, this.padding)
        val cellHeight = (this.height * PROPORTION) / columns
        val cellWidth = this.width / columns
        cellSize = min(cellWidth, cellHeight)
    }

    private fun createFooterRect(width: Float, height: Float, padding: Float) {
        footerRect.left = padding / 2
        footerRect.top = height * PROPORTION
        footerRect.right = width + (padding / 2)
        footerRect.bottom = height
    }

    companion object {
        const val PROPORTION = 0.7f
        const val PADDING_PROPORTION = 0.05f
    }
}
