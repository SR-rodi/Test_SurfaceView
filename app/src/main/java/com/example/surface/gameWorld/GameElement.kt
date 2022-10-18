package com.example.surface.gameWorld

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF

abstract class GameElement:Element{
    protected var width = 0f
    protected var height = 0f
    var paddingTop = 0f
    var paddingHorizontal = 0f
    override val rectangle = RectF()

   abstract fun setSize(newWidth: Float, newHeight: Float)
   abstract override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?)

}