package com.example.surface.gameWorld

import android.graphics.*
import android.util.Log
import com.example.surface.*

class Footer(private val amountShape: Int = AMOUNT_SHAPE) : GameWorld() {

    val listShape = List(amountShape) { Shape() }

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
            shape.setPosition(rectangle.left + shapeSizeForX * position, rectangle.top)
            shape.getDefaultPosition(rectangle.left + shapeSizeForX * position, rectangle.top)
        }
    }

    val paint = Paint().apply {
        color = Color.BLUE
        alpha = 50
    }
    val paint1 = Paint().apply {
        color = Color.RED
    }

    override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?) {
        canvas?.drawBitmap(oneTile, null, rectangle, Paint())

        listShape.forEachIndexed { position, shape ->
            val cellSize = shapeSizeForX / (shape.shapeColumns + 2)
            shape.modelArray.forEachIndexed { row, cells ->
                cells.forEachIndexed { col, cell ->
                    shape.shapeRect.left = shape.shapeX + (cellSize * col)
                    shape.shapeRect.top = shape.shapeY + (cellSize * row)
                    shape.shapeRect.right = shape.shapeRect.left + cellSize
                    shape.shapeRect.bottom = shape.shapeRect.top + cellSize
                    if (cell == Cell.FULL) canvas?.drawRect(shape.shapeRect, paint1)
                    else canvas?.drawRect(shape.shapeRect, paint)
                }
            }
        }
    }

    fun isShapeNumber(x: Float, y: Float): Shape? {
        val isVerticalCoordinateShape = y > rectangle.top && y < rectangle.bottom
        var shape :Shape?=null
        for (position in 0 until amountShape)
            if (x > rectangle.left + shapeSizeForX * position
                && x < shapeSizeForX * (position + 1) && isVerticalCoordinateShape
            ) {
                shape = listShape[position]
                Log.d("Kart", "$shape")
                break
            }
        return shape
    }
}