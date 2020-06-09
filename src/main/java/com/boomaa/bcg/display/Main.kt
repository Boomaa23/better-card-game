package com.boomaa.bcg.display

import com.boomaa.bcg.mech.Deck
import com.boomaa.bcg.lib.Dimensions

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Deck.getStandard()[7].draw(
            Dimensions(
                5,
                7
            ).scaleToMaxAxis(400, Dimensions.Axis.HEIGHT))
    }
}