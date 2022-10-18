package com.example.surface.gameview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.surface.R
import com.example.surface.draw.BitmapConverter
import com.example.surface.gameWorld.element.GameWorld
import com.example.surface.gameWorld.element.RenderGame
import com.example.surface.gameWorld.element.Shape

class GameView(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
) : SurfaceView(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?, defStileAttrs: Int) : this(
        context,
        attrs,
        defStileAttrs,
        0
    )

    var isStartRender = true

    private val renderGame by lazy { GameWorld() }
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
        if (isStartRender) {
            isStartRender = false
            renderGame.setSize(width.toFloat(), height.toFloat())
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
        if (fullTile != null && emptyTile != null && footerTile != null)
            renderGame.draw(canvas, fullTile, emptyTile, footerTile)
    }

    var isMove: Boolean = false
    var moveShape: Shape? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                moveShape = renderGame.getShape(event.x, event.y)
                moveShape?.setPosition(event.x,event.y)
                if (moveShape != null) {
                    isMove = true
                }
            }
            MotionEvent.ACTION_UP -> {
                moveShape?.sendDefaultPosition()
                val cellPosition = moveShape?.let {
                    renderGame.getPosition(event.x, event.y, it)
                }
                if (cellPosition != null && moveShape != null)
                    if (renderGame.setCell(cellPosition, moveShape!!)){
                        moveShape!!.sendDefaultPosition()
                        renderGame.setNewShape()
                    }

                moveShape = null
                isMove = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (isMove)
                    moveShape?.setPosition(event.x, event.y)
            }
        }
        return true
    }
}