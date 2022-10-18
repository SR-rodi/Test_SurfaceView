package com.example.surface.gameWorld.engine

import android.graphics.Canvas
import com.example.surface.gameWorld.Cell
import com.example.surface.gameWorld.element.Footer
import com.example.surface.gameWorld.element.GameBoard
import com.example.surface.gameWorld.element.Shape

class DeleteLineOrRow(
    override val gameBoard: GameBoard,
):Engine {

   override fun start(x: Float?, y: Float?, shape: Shape?) {

        for (row in 0..gameBoard.cellArray.lastIndex) {
            var counterHorizontal = 0
            var counterVertical = 0
            for (col in 0..gameBoard.cellArray[0].lastIndex) {
                if (gameBoard.cellArray[row][col] == Cell.FULL) counterHorizontal++
                if (gameBoard.cellArray[col][row] == Cell.FULL) counterVertical++
            }
            val isDelHorizontal = counterHorizontal == gameBoard.cellArray.size
            val isDelVertical = counterVertical == gameBoard.cellArray[0].size
            if (isDelHorizontal || isDelVertical)
                for (col in 0..gameBoard.cellArray[0].lastIndex) {
                    if (isDelHorizontal) gameBoard.cellArray[row][col] = Cell.EMPTY
                    if (isDelVertical) gameBoard.cellArray[col][row] = Cell.EMPTY
                }
        }
    }
}
