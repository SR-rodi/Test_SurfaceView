package com.example.surface.gameWorld

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF

interface Element {
    val rectangle:RectF
    fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?=null)
}