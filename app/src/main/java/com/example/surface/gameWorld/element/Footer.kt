package com.example.surface.gameWorld.element

import android.graphics.*
import com.example.surface.*
import com.example.surface.gameWorld.GameElement

class Footer(private val amountShape: Int = AMOUNT_SHAPE) : GameElement() {

    override fun setSize(newWidth: Float, newHeight: Float) {
        width = newWidth
        height = newHeight * SIZE_GAME_FOOTER
        paddingTop = newHeight * (SIZE_GAME_COUNT + SIZE_GAME_BOARD)
        paddingTop += paddingTop * 0.1f
        paddingHorizontal = width * PADDING_HORIZONTAL_iN_GAME

        rectangle.left = paddingHorizontal / 2
        rectangle.top = paddingTop
        rectangle.right = rectangle.left + width - paddingHorizontal
        rectangle.bottom = rectangle.top + height
    }

    override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?) {
        canvas?.drawBitmap(oneTile, null, rectangle, Paint())
    }

}