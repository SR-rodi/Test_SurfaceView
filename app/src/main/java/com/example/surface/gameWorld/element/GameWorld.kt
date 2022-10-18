package com.example.surface.gameWorld.element

import com.example.surface.gameWorld.engine.GameEngine

class GameWorld(sizeBoard: Int = 8, amountShape: Int = 3) : RenderGame(sizeBoard, amountShape) {

    private val gameEngine by lazy { GameEngine(gameBoard, footer) }

    fun getShape(x: Float, y: Float): Shape? = gameEngine.getShape(x, y, listShape, shapeSizeForX)

    fun getPosition(x: Float, y: Float, shape: Shape) =
        gameEngine.getPosition(x, y, shape)

    fun setCell(cellPosition: Pair<Int, Int>, shape: Shape) =
        gameEngine.setCell(cellPosition, shape)

    fun setNewShape() = gameEngine.setNewShape()


}