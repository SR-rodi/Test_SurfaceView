package com.example.surface.gameWorld.engine

import com.example.surface.gameWorld.Cell
import com.example.surface.gameWorld.element.GameBoard
import com.example.surface.gameWorld.element.Shape

class GetPosition(override val gameBoard: GameBoard) : Engine {

    override fun start(x: Float?, y: Float?, shape: Shape?): Pair<Int, Int>? {
        var position: Pair<Int, Int>? = null
        gameBoard.cellArray.forEachIndexed { row: Int, cells: Array<Cell> ->
            val cellHeight = gameBoard.cellSize!! * row + gameBoard.paddingTop
            cells.forEachIndexed { col: Int, cell ->
                val newX = gameBoard.paddingHorizontal + (gameBoard.cellSize!! * col)
                if (x != null && y != null)
                    if (x > newX && x < newX + gameBoard.cellSize!! && y > cellHeight && y < cellHeight + gameBoard.cellSize!!)
                        position = Pair(row, col)
            }
            if (position != null) return@forEachIndexed
        }
        return position
    }
}