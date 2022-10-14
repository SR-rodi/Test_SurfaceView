package com.example.surface.gameview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.surface.draw.BitmapConverter
import com.example.surface.R
import com.example.surface.board.GameBoard
import com.example.surface.draw.Drawable
import com.example.surface.draw.Shape


class GameView(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
) : SurfaceView(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet?, defStileAttrs: Int) : this(
        context,
        attrs,
        defStileAttrs,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var boardWidth: Int? = null
    private var boardHeight: Int? = null
    private val board = GameBoard()
    private val drawable = Drawable(board.cellArray)
    private val shape = Shape()
    private val gameHolderCallback = GameHolderCallback({ newDraw() })

    init {
        holder.addCallback(gameHolderCallback)
    }

    private fun newDraw() {
        val canvas = holder.lockCanvas()
        draw(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

    private val emptyTile = BitmapConverter().getBitmap(resources, R.drawable.tile)
    private val fullTile = BitmapConverter().getBitmap(resources, R.drawable.ston)
    private val backgroundColor = context.getColor(R.color.green)
    private val footer = BitmapConverter().getBitmap(resources, R.drawable.footer)

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        setSize(width, height)
        drawable.backgroundColor(canvas, backgroundColor)
        emptyTile?.let { fullTile?.let { it1 -> drawable.renderBoard(canvas, emptyTile, it1) } }
        drawable.footer(canvas, footer)
        shape.draw(canvas)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val center = board.cellSize

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_UP -> {
                if (shape.isMove)
                    board.getPosition(shape.x, shape.y)

                shape.setDefaultPosition()
                shape.isMove = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (shape.isBoard(event.x, event.y)) {
                    shape.isMove = true
                    shape.x = event.x - center
                    shape.y = event.y - center
                }
            }
        }
        return true
    }

    private fun setSize(width: Int, height: Int) {
        if (boardWidth == null) {
            boardWidth = width
            boardHeight = height
            board.setSize(boardWidth!!, boardHeight!!)
            drawable.setSize(board)
            shape.footerRect = board.footerRect
            shape.setSize(board)
        }
    }
}