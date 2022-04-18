package com.wllpwr.handedness



object Timer {
    var beginTime = System.nanoTime()
    var elapsedTime = System.nanoTime()

    fun startTimer(){
        beginTime = System.nanoTime()
    }

    fun endTimer(){
        elapsedTime = System.nanoTime() - beginTime
    }

    fun getTime(): Long {
        return elapsedTime
    }
}