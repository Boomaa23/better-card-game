package com.boomaa.bcg.mech

import com.boomaa.bcg.lib.Corner
import com.boomaa.bcg.lib.Dimensions
import com.boomaa.bcg.lib.IntPoint
import com.boomaa.bcg.util.SwingHelpers
import com.boomaa.bcg.util.Textures
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.BufferedImage

class Card(val suit: Suit, val rank: Rank, val value: Int) {
    enum class Suit {
        DIAMOND, CLUB, HEART, SPADE;

        val texture: Image = Textures.load("suits/$name.png")
    }
    enum class Rank(vararg val placeMatrix: Int) {
        ACE(0, 0, 1, 0, 0),
        TWO(1, 0, 0, 0, 1),
        THREE(1, 0, 1, 0, 1),
        FOUR(2, 0, 0, 0, 2),
        FIVE(2, 0, 1, 0, 2),
        SIX(2, 0, 2, 0, 2),
        SEVEN(2, 1, 2, 0, 2),
        EIGHT(2, 1, 2, 1, 2),
        NINE(2, 2, 1, 2, 2),
        TEN(2, 2, 2, 2, 2),
        JACK(),
        QUEEN(),
        KING();

        val texture: Image? = if (placeMatrix.isEmpty()) Textures.load("ranks/$name.png") else null
    }

    fun draw(dim: Dimensions): Image {
        val image = BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB)
        val g2 = image.graphics as Graphics2D
        val margin = dim.width / 10
        val vspc = (dim.height - 0) / (rank.placeMatrix.size + 1)
        val resizeImg = dim.height / 10
        val scaledSuit = suit.texture.getScaledInstance(resizeImg, resizeImg, BufferedImage.SCALE_SMOOTH)

        for ((index, num) in rank.placeMatrix.withIndex()) {
            if (num != 0) {
                val hspc = (dim.width - 0) / (num + 1)
                for (lnum in 1..num) {
                    g2.drawImage(scaledSuit, (hspc * lnum) - (resizeImg / 2), (vspc * (index + 1)) - (resizeImg / 2), null)
                }
            }
        }

        g2.fillRect(margin, margin, dim.width - (margin * 2), dim.height - (margin * 2))

        val fsize: Int = (margin * 1.5).toInt()
        val drawString = if (intArrayOf(0, 10, 11, 12).contains(rank.ordinal)) rank.name.substring(0) else (rank.ordinal + 1).toString()

        SwingHelpers.drawCenteredString(
            drawString,
            IntPoint(margin, margin),
            Font("Arial", Font.BOLD, fsize),
            Color.BLACK,
            g2
        )
        val ctrImg = IntPoint(0, 0).add(
            IntPoint.imageCoords(
                scaledSuit,
                Corner.CENTER
            ).invert())
        g2.drawImage(scaledSuit, ctrImg.x, ctrImg.y, null)
//        g2.drawImage(scaledSuit, imgPos.x, imgPos.y, null)
//        g2.drawImage(scaledSuit, dim.width - imgPos.x, dim.height - imgPos.y, null)
        return image
    }
}