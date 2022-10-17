package com.example.surface.gameWorld

import android.graphics.*
import android.util.Log
import kotlin.random.Random
import kotlin.random.nextInt


class Shape():GameWorld() {
    private val generateShapeModel = GenerateShapeModel()


    val model = generateShapeModel.setModel(Random.nextInt(1..6))
    var shiftPosition = 0
    var modelArray = generateShapeModel.setShape(model)

init {
    shiftPosition = generateShapeModel.shift // магия, при объявлении не присваивается
    }

    var cellSizeInFooter = 0f
    var cellSizeInDraw = 0f
    val shapeRect = RectF()

    var shapeX: Float = 0f
    var shapeY: Float = 0f

    var shapeRow = modelArray.size
    var shapeColumns = modelArray[0].size
    var defX = 0f
    var defY = 0f


    fun setPosition(x: Float, y: Float,cellSize:Float?=null) {
        cellSize?.let {cellSizeInDraw = it }
        shapeX = x
        shapeY = y
    }
    fun getDefaultPosition(defX: Float, defY: Float) {
        this.defX = defX
        this.defY = defY
    }

    fun setDefaultPosition(cellSize: Float?=null) {
        cellSize?.let {cellSizeInDraw = it }
        shapeX = defX
        shapeY = defY
    }

    override fun setSize(newWidth: Float, newHeight: Float) {
        cellSizeInFooter = newWidth
        cellSizeInDraw = cellSizeInFooter

    }

    override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?) {
        modelArray.forEachIndexed { row, cells ->
            cells.forEachIndexed { col, cell ->
                shapeRect.left = shapeX + (cellSizeInDraw * col)
                shapeRect.top = shapeY + (cellSizeInDraw * row)
                shapeRect.right = shapeRect.left + cellSizeInDraw
                shapeRect.bottom = shapeRect.top + cellSizeInDraw
                if (cell == Cell.FULL) canvas?.drawBitmap(oneTile,null,shapeRect, Paint())
            }
        }
    }
}