package com.example.surface.board

import android.view.View
import com.example.surface.draw.Shape

typealias onShapeListener = (shape: Shape) -> Unit

fun one() {
    val columns = 12
    val cellSize = 10
    val padding = 10
    for (line in 0 until columns)
        for (col in 0 until columns) {

            val x1 = cellSize * col + padding
            val y1 = cellSize * line + padding

            val x2 = x1
            val y2 = (padding+cellSize)*line

            val x3 = (padding+cellSize)*col
            val y3 = y1

            val x4 = x3
            val y4 = y2
        }
}