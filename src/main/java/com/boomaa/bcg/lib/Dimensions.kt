package com.boomaa.bcg.lib;

import com.boomaa.bcg.util.MathUtils

class Dimensions(widthRatio: Int, heightRatio: Int) {
    private val wRatio: Int
    private val hRatio: Int
    var width = widthRatio
    var height = heightRatio

    init {
        val gcd: Int = MathUtils.gcd(widthRatio, heightRatio)
        wRatio = widthRatio / gcd
        hRatio = heightRatio / gcd
    }

    fun scaleToMaxAxis(input: Int, axis: Axis): Dimensions {
        when (axis) {
            Axis.HEIGHT -> {
                height = input
                width = input * wRatio / hRatio
            }
            Axis.WIDTH -> {
                height = input * hRatio / wRatio
                width = input
            }
        }
        return this
    }

    enum class Axis {
        WIDTH, HEIGHT
    }
}
