package com.boomaa.bcg

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

    fun draw(width: Int, height: Int): Image {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g2 = image.graphics as Graphics2D
        val margin = 20 //TODO figure out an actual way to do this
        val vspc = (height - (margin * 2)) / (rank.placeMatrix.size + 1)
        val resizeImg = height / 10 //TODO figure out an actual way to do this
        val scaledSuit = suit.texture.getScaledInstance(resizeImg, resizeImg, BufferedImage.SCALE_SMOOTH)

        for ((index, num) in rank.placeMatrix.withIndex()) {
            if (num != 0) {
                val hspc = (width - (margin * 2)) / (num + 1)
                for (lnum in 1..num) {
                    g2.drawImage(scaledSuit, margin + (hspc * lnum) - (resizeImg / 2), margin + (vspc * (index + 1)) - (resizeImg / 2), null)
                }
            }
        }

        val fsize = margin * 2 //TODO figure out an actual way to do this
        g2.font = Font("Arial", Font.BOLD, fsize)
        g2.color = Color.BLACK
        val drawString = if (intArrayOf(0, 10, 11, 12).contains(rank.ordinal)) rank.name.substring(0) else (rank.ordinal + 1).toString()
        g2.drawString(drawString, margin + (resizeImg / 2), margin * 2)
        g2.drawImage(scaledSuit, margin, margin + fsize, null)
        return image
    }
}