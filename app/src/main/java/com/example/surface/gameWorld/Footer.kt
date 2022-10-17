package com.example.surface.gameWorld

import android.graphics.*
import android.util.Log
import com.example.surface.*

class Footer(private val amountShape: Int = AMOUNT_SHAPE) : GameWorld() {

    private val listShape = Array(amountShape) { Shape() }

    var cellSize = 0f

    private var shapeSizeForX = 0f

    override fun setSize(newWidth: Float, newHeight: Float) {
        width = newWidth
        height = newHeight * SIZE_GAME_FOOTER
        paddingTop = newHeight * (SIZE_GAME_COUNT + SIZE_GAME_BOARD)
        paddingTop += paddingTop * 0.1f
        paddingHorizontal = width * PADDING_HORIZONTAL_iN_GAME

        shapeSizeForX = width / amountShape

        rectangle.left = paddingHorizontal / 2
        rectangle.top = paddingTop
        rectangle.right = rectangle.left + width - paddingHorizontal
        rectangle.bottom = rectangle.top + height
        listShape.forEachIndexed { position, shape ->
            cellSize = shapeSizeForX / (shape.shapeColumns + 2)
            shape.setPosition(rectangle.left + shapeSizeForX * position, rectangle.top,cellSize)
            shape.getDefaultPosition(rectangle.left + shapeSizeForX * position, rectangle.top)
        }
    }

    override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?) {
        canvas?.drawBitmap(oneTile, null, rectangle, Paint())

        listShape.forEach { shape ->
            twoTile?.let { shape.draw(canvas, it) }
        }
    }

    private var shapeSelectPosition = 0
    fun isShapeNumber(x: Float, y: Float): Shape? {
        val isVerticalCoordinateShape = y > rectangle.top && y < rectangle.bottom
        var selectShape: Shape? = null
        listShape.forEachIndexed { position, shape ->
            if (x > rectangle.left + shapeSizeForX * position
                && x < shapeSizeForX * (position + 1) && isVerticalCoordinateShape
            ) {
                selectShape = shape
                shapeSelectPosition = position
                return@forEachIndexed
            }
        }
        return selectShape
    }

    fun setNewShape() {
        val x = listShape[shapeSelectPosition].shapeX
        val y = listShape[shapeSelectPosition].shapeY
        val shape = Shape()
        shape.getDefaultPosition(x,y)
        shape.setDefaultPosition(cellSize)
        listShape[shapeSelectPosition] = shape
    }
}