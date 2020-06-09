package com.boomaa.bcg.lib

import java.awt.Font
import java.awt.Image
import java.awt.font.FontRenderContext

class IntPoint(var x: Int, var y: Int) {
    constructor (x: Double, y: Double) : this(x.toInt(), y.toInt())

    fun add(point: IntPoint): IntPoint {
        this.x += point.x
        this.y += point.y
        return this
    }

    fun add(x: Int, y: Int): IntPoint {
        this.x += x
        this.y += y
        return this
    }

    fun invert(): IntPoint {
        this.x = -this.x
        this.y = -this.y
        return this
    }

    companion object Creator {
        fun imageCoords(image: Image, corner: Corner): IntPoint {
            var x: Int = image.getWidth(null) / when (corner.x) {
                BorderZone.MIDDLE -> 2
                BorderZone.RIGHT -> 1
                else -> image.getWidth(null)
            }
            var y: Int = image.getHeight(null) / when (corner.y) {
                BorderZone.MIDDLE -> 2
                BorderZone.BOTTOM -> 1
                else -> image.getHeight(null)
            }
            if (x == 1 && image.getWidth(null) != 0) x = 0
            if (y == 1 && image.getHeight(null) != 0) y = 0
            return IntPoint(x, y)
        }

        fun stringCoords(text: String, font: Font, corner: Corner): IntPoint {
            val strw = font.getStringBounds(text, FontRenderContext(font.transform, true, true))
            var x: Double = strw.x + when (corner.x) {
                BorderZone.MIDDLE -> strw.x + (strw.width / 2)
                BorderZone.RIGHT -> strw.width
                else -> -strw.x
            }
            var y: Double = strw.y + when (corner.y) {
                BorderZone.MIDDLE -> strw.height / 2
                BorderZone.BOTTOM -> strw.height
                else -> -strw.y
            }
            return IntPoint((strw.width / 2) + strw.x, (strw.height / 2) + strw.y)
        }
    }
}