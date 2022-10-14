package com.example.surface.draw

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapConverter() {

    fun getBitmap(resources:Resources,id:Int): Bitmap? = BitmapFactory.decodeResource(resources,id)
}