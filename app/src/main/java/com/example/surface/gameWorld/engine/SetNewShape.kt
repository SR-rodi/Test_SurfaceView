package com.example.surface.gameWorld.engine

import com.example.surface.gameWorld.element.GameBoard
import com.example.surface.gameWorld.element.Shape

class SetNewShape(override val gameBoard: GameBoard) : Engine {

    override fun start(x: Float?, y: Float?, shape: Shape?) {

    /*    val newShapePositionX = gameBoard.listShape[x!!.toInt()].shapeX
        val  newShapePositionY = gameBoard.listShape[x.toInt()].shapeY
        val newShape = Shape()
        newShape.setDefaultPosition(newShapePositionX, newShapePositionY)
        newShape.newDefaultPosition(gameBoard.cellSize)
        gameBoard.listShape[x.toInt()] = newShape*/
    }
}