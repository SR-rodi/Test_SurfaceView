package com.example.surface.gameWorld.engine

import com.example.surface.gameWorld.Cell
import com.example.surface.gameWorld.element.Footer
import com.example.surface.gameWorld.element.GameBoard
import com.example.surface.gameWorld.element.Shape

class SetCell(
    override val gameBoard: GameBoard,
) : Engine {
    private fun isInBoard(position: Pair<Int?, Int?>, shape: Shape) =
        (shape.shapeRow + position.first!! <= gameBoard.sizeBoard && shape.shapeColumns + position.second!! <= gameBoard.sizeBoard)

    private fun checkShapeInBoard(position: Pair<Int?, Int?>, shape: Shape?): Boolean {

        var counterShape = 0
        var counterBoard = 0
        if (position.first != null && position.second != null && shape != null) {
            val isNext =
                gameBoard.cellArray[position.first!!][position.second!! + shape.shiftPosition] == Cell.EMPTY
            if (isInBoard(position, shape) && isNext) {
                shape.modelArray.forEachIndexed { row, cells ->
                    cells.forEachIndexed { col, cell ->
                        if (cell != Cell.EMPTY) counterShape++
                        if (cell != Cell.EMPTY && gameBoard.cellArray[position.first!! + row][position.second!! + col] == Cell.EMPTY)
                            counterBoard++
                    }
                }
            }
        }

        return counterShape == counterBoard && counterBoard != 0
    }

    override fun start(x: Float?, y: Float?, shape: Shape?): Boolean {
        val check = checkShapeInBoard(Pair(x?.toInt(), y?.toInt()), shape)
        if (check) {
            shape?.modelArray?.forEachIndexed { row, cells ->
                cells.forEachIndexed { col, cell ->
                    if (cell != Cell.EMPTY)
                        gameBoard.cellArray[x?.toInt()!! + row][y?.toInt()!! + col] = cell
                }
            }
            DeleteLineOrRow(gameBoard).start()
        }
        return check
    }
}