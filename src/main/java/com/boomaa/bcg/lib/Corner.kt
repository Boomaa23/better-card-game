package com.boomaa.bcg.lib

class Corner(val x: BorderZone, val y: BorderZone) {
    companion object QuickUse {
        val CENTER = Corner(BorderZone.MIDDLE, BorderZone.MIDDLE)
    }
}