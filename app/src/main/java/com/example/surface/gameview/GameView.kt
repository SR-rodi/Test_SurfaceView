package com.example.surface.gameview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.surface.draw.BitmapConverter
import com.example.surface.R
import com.example.surface.gameWorld.Footer
import com.example.surface.gameWorld.GameBoard
import com.example.surface.gameWorld.Shape

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

    var gameHeight: Float? = null
    var gameWidth: Float? = null

    private val board = GameBoard()
    private val footer = Footer()
    private val gameHolderCallback = GameHolderCallback { newDraw() }

    init {
        holder.addCallback(gameHolderCallback)
    }

    private fun newDraw() {
        val canvas = holder.lockCanvas()
        draw(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

    private fun setSize(width: Int, height: Int) {
        if (gameWidth == null) {
            gameHeight = height.toFloat()
            gameWidth = width.toFloat()
            board.setSize(gameWidth!!, gameHeight!!)
            footer.setSize(gameWidth!!, gameHeight!!)
        }
    }

    private val emptyTile = BitmapConverter().getBitmap(resources, R.drawable.tile)
    private val fullTile = BitmapConverter().getBitmap(resources, R.drawable.ston)
    private val backgroundColor = context.getColor(R.color.green)
    private val footerTile = BitmapConverter().getBitmap(resources, R.drawable.footer)

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        setSize(width, height)
        canvas?.drawColor(backgroundColor)
        emptyTile?.let { fullTile?.let { fullTile -> board.draw(canvas, emptyTile, fullTile) } }
        footerTile?.let { footerTile -> footer.draw(canvas, footerTile) }

    }

    var isMove: Boolean = false
    var moveShape: Shape? = null
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                moveShape = footer.isShapeNumber(event.x, event.y)
                if (moveShape != null) isMove = true
            }
            MotionEvent.ACTION_UP -> {
                moveShape?.setDefaultPosition()
                moveShape = null
                isMove = false
            }
            MotionEvent.ACTION_MOVE -> {
                moveShape?.setPosition(event.x, event.y)
            }
        }
    return true
}
}