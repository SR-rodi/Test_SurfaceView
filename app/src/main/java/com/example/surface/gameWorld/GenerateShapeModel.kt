package com.example.surface.gameWorld

import android.graphics.RectF
import kotlin.random.Random
import kotlin.random.nextInt

class GenerateShapeModel {

    private var shapeRow = 0
    private var shapeColumns = 0
    var shift = 0

    fun setModel(number: Number) = when (number) {
        1 -> Model.LINE_HOR
        2 -> Model.CORNER_LEFT
        3 -> Model.SQUARE
        4 -> Model.CORNER_RIGHT
        5 -> Model.LINE_VER
        else -> Model.CROSS
    }

    fun setShape(model: Model): Array<Array<Cell>> {

        return when (model) {
            Model.LINE_HOR -> {
                shapeRow = 1
                shapeColumns = 4
                Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
            }

            Model.CORNER_LEFT -> {
                shapeRow = 2
                shapeColumns = 3
                val modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                modelArray[0][1] = Cell.EMPTY
                modelArray[0][2] = Cell.EMPTY
                modelArray
            }
            Model.SQUARE -> {
                shapeColumns = 2
                shapeRow = 2
                Array(shapeColumns) { Array(shapeColumns) { Cell.FULL } }
            }
            Model.CORNER_RIGHT -> {
                shift = 2
                shapeRow = 2
                shapeColumns = 3
                val modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                modelArray[0][1] = Cell.EMPTY
                modelArray[0][0] = Cell.EMPTY
                modelArray
            }
            Model.CROSS -> {
                shift = 1
                shapeRow = 2
                shapeColumns = 3
                val modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                modelArray[0][2] = Cell.EMPTY
                modelArray[0][0] = Cell.EMPTY
                modelArray
            }
            Model.LINE_VER -> {
                shapeRow = 4
                shapeColumns = 1
                Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
            }
        }
    }
}