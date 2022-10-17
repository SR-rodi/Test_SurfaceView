package com.example.surface.gameWorld

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF

abstract class GameWorld{
    protected var width = 0f
    protected var height = 0f
    protected var paddingTop = 0f
    protected var paddingHorizontal = 0f
    protected val rectangle = RectF()

   abstract fun setSize(newWidth: Float, newHeight: Float)
   abstract fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?=null)

}