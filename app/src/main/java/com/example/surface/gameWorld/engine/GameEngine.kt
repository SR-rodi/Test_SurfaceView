package com.example.surface.gameWorld.engine

import com.example.surface.gameWorld.element.Footer
import com.example.surface.gameWorld.element.GameBoard
import com.example.surface.gameWorld.element.Shape

class GameEngine(
    gameBoard: GameBoard,
    private val footer: Footer,
) {

    private val getNewPosition = GetPosition(gameBoard)
    private val setNewCell = SetCell(gameBoard)
    private val setNewShape = SetNewShape(gameBoard)

    private var shapeSelectPosition: Int? = null
    private fun isShapeNumber(
        x: Float, y: Float, listShape: Array<Shape>, shapeSizeForX: Float,
    ): Int? {
        val isVerticalCoordinateShape = y > footer.rectangle.top && y < footer.rectangle.bottom
        listShape.forEachIndexed { position, _ ->
            if (x > footer.rectangle.left + shapeSizeForX * position
                && x < shapeSizeForX * (position + 1) && isVerticalCoordinateShape
            ) {
                shapeSelectPosition = position
                return@forEachIndexed
            }
        }
        return shapeSelectPosition
    }

    fun getShape(x: Float, y: Float, listShape: Array<Shape>, shapeSizeForX: Float): Shape? {
        shapeSelectPosition = isShapeNumber(x, y, listShape, shapeSizeForX)
        return if (shapeSelectPosition != null) listShape[shapeSelectPosition!!]
        else null
    }

    fun setNewShape() = setNewShape.start(shapeSelectPosition?.toFloat())

    fun getPosition(x: Float, y: Float, shape: Shape) = getNewPosition.start(x, y, shape)

    fun setCell(position: Pair<Int, Int>, shape: Shape) =
        setNewCell.start(position.first.toFloat(), position.second.toFloat(), shape)

}