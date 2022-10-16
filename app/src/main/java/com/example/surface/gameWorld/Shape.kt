package com.example.surface.gameWorld

import android.graphics.RectF
import kotlin.random.Random

class Shape() {
    val shapeRect = RectF()
    var shapeX = 0f
    var shapeY = 0f
    val model = random(Random.nextInt())
    var modelArray = setShape(model)
    var shapeRow = 0
    var shapeColumns = 0
    var defX = 0f
    var defY = 0f


    private fun random(number: Number) = when (number) {
        1 -> Model.LINE_HOR
        2 -> Model.CORNER_LEFT
        3 -> Model.SQUARE
        4 -> Model.CORNER_RIGHT
        5 -> Model.LINE_VER
        else -> Model.CROSS
    }

    private fun setShape(model: Model): Array<Array<Cell>> {

        return when (model) {
            Model.LINE_HOR -> {
                shapeRow = 1
                shapeColumns = 4
                modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                modelArray
            }

            Model.CORNER_LEFT -> {
                shapeRow = 2
                shapeColumns = 3
                modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                this.modelArray[0][1] = Cell.EMPTY
                this.modelArray[0][2] = Cell.EMPTY
                modelArray
            }
            Model.SQUARE -> {
                shapeColumns = 2
                modelArray = Array(shapeColumns) { Array(shapeColumns) { Cell.FULL } }
                modelArray
            }
            Model.CORNER_RIGHT -> {
                shapeRow = 2
                shapeColumns = 3
                modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                this.modelArray[0][1] = Cell.EMPTY
                this.modelArray[0][0] = Cell.EMPTY
                modelArray
            }
            Model.CROSS -> {
                shapeRow = 2
                shapeColumns = 3
                modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                this.modelArray[0][2] = Cell.EMPTY
                this.modelArray[0][0] = Cell.EMPTY
                modelArray
            }
            Model.LINE_VER -> {
                shapeRow = 4
                shapeColumns = 1
                modelArray = Array(shapeRow) { Array(shapeColumns) { Cell.FULL } }
                modelArray
            }
        }
    }

    fun setPosition(x: Float, y: Float) {
        shapeX = x
        shapeY = y
    }

    fun getDefaultPosition(defX: Float, defY: Float) {
        this.defX = defX
        this.defY = defY
    }

    fun setDefaultPosition(){
        shapeX=defX
        shapeY=defY
    }
}