package com.example.surface.gameWorld.element

import android.graphics.*
import com.example.surface.gameWorld.Cell
import com.example.surface.gameWorld.Element
import com.example.surface.gameWorld.GenerateShapeModel
import kotlin.random.Random
import kotlin.random.nextInt


class Shape(): Element {

    private val generateShapeModel = GenerateShapeModel()

    val model = generateShapeModel.setModel(Random.nextInt(1..6))
    var shiftPosition = 0
    var modelArray = generateShapeModel.setShape(model)

init {
    shiftPosition = generateShapeModel.shift // магия, при объявлении не присваивается
    }

    private var cellSizeInDraw = 0f
    override val rectangle = RectF()

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
   private fun setDefaultPosition(defX: Float, defY: Float) {
        this.defX = defX
        this.defY = defY
    }

    fun sendDefaultPosition(cellSize: Float?=null) {
        cellSize?.let {cellSizeInDraw = it }
        shapeX = defX
        shapeY = defY
    }

    fun setSize(shapeSizeForX:Float,rectangle:RectF,position:Int){
        cellSizeInDraw = shapeSizeForX / (shapeColumns + 2)
        setPosition(rectangle.left + shapeSizeForX * position,
            rectangle.top,
            cellSizeInDraw)
        setDefaultPosition(rectangle.left + shapeSizeForX * position,
            rectangle.top)
    }

    override fun draw(canvas: Canvas?, oneTile: Bitmap, twoTile: Bitmap?) {
        modelArray.forEachIndexed { row, cells ->
            cells.forEachIndexed { col, cell ->
                rectangle.left = shapeX + (cellSizeInDraw * col)
                rectangle.top = shapeY + (cellSizeInDraw * row)
                rectangle.right = rectangle.left + cellSizeInDraw
                rectangle.bottom = rectangle.top + cellSizeInDraw
                if (cell == Cell.FULL) canvas?.drawBitmap(oneTile,null, rectangle, Paint())
            }
        }
    }
}