package com.boomaa.bcg

class Deck(vararg cards: Card) : ArrayList<Card>() {
    constructor(cards: ArrayList<Card>) : this(*cards.toTypedArray())

    init {
        for (card in cards) {
            this.add(card)
        }
    }

    fun shuffle() {
        //TODO add shuffling
    }

    companion object Creator {
        fun getStandard(): Deck {
            val cardList = ArrayList<Card>()
            for (suit in Card.Suit.values()) {
                for (rank in Card.Rank.values()) {
                    cardList.add(Card(suit, rank, rank.ordinal))
                }
            }
            return Deck(cardList)
        }
    }
}