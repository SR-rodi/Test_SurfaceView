package com.example.surface.gameview

import kotlinx.coroutines.*

class GameThread {

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)

    fun startDraw(block: () -> Unit) {
        scope.launch {
            while (true) {
                block()
                fps(System.nanoTime())
            }
        }
    }

    fun stop(){
        job.cancel()
    }

    //плавная картинка
  private suspend fun fps(startTime: Long) {
       val sleepTime = (System.nanoTime() - startTime) - FRAME_TIME
       if (sleepTime > 0) delay(sleepTime)
   }

    companion object {
        private const val MILLION = 1000000
        private const val FPS: Long = 25
        const val FRAME_TIME: Long = 1000 / FPS * MILLION
    }
}