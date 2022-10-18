package com.example.surface.gameWorld.element

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import com.example.surface.gameWorld.engine.GameEngine

abstract class RenderGame(private val sizeBoard: Int, private val amountShape: Int) {

    protected val gameBoard by lazy { GameBoard(sizeBoard) }
    protected val footer by lazy { Footer() }

   protected val listShape = Array(amountShape) { Shape() }
    protected var shapeSizeForX = 0f

    fun draw(canvas: Canvas?, fullTile: Bitmap, emptyTile: Bitmap, footerTile: Bitmap) {
        gameBoard.draw(canvas, emptyTile, fullTile)
        footer.draw(canvas, footerTile)
        listShape.forEach { shape -> shape.draw(canvas, fullTile) }
    }

    fun setSize(newWidth: Float, newHeight: Float) {
        gameBoard.setSize(newWidth, newHeight)
        footer.setSize(newWidth, newHeight)
        shapeSizeForX = newWidth / amountShape
        listShape.forEachIndexed { position, shape ->
            shape.setSize(shapeSizeForX, footer.rectangle, position)
        }
    }
}