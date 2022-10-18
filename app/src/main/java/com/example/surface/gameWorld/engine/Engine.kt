package com.example.surface.gameWorld.engine

import android.graphics.Canvas
import com.example.surface.gameWorld.element.Footer
import com.example.surface.gameWorld.element.GameBoard
import com.example.surface.gameWorld.element.Shape

interface Engine {
    val gameBoard: GameBoard

    fun start(x: Float?=null, y: Float?=null, shape: Shape?=null):Any?

}

