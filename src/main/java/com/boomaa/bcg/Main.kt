package com.boomaa.bcg

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Deck.getStandard()[7].draw(400, 600)
    }
}