package com.example.surface.gameview


import android.view.SurfaceHolder


class GameHolderCallback(
    private val block: () -> Unit
) : SurfaceHolder.Callback {

    private val gameThread = GameThread()

    override fun surfaceCreated(p0: SurfaceHolder) {
        gameThread.startDraw {block()}
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        gameThread.stop()
    }
}