package com.boomaa.bcg.util

import com.boomaa.bcg.lib.IntPoint
import com.boomaa.bcg.lib.Corner
import java.awt.Color
import java.awt.Font
import java.awt.Graphics

object SwingHelpers {
    fun drawCenteredString(text: String, pos: IntPoint, font: Font, color: Color, g: Graphics) {
        g.font = font
        g.color = color
        val fpos = pos.add(IntPoint.stringCoords(text, font, Corner.CENTER).invert())
        g.drawString(text, fpos.x, fpos.y)
    }
}