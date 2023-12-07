package com.github.mrbean355.aoc.day7

data class Hand(
    val cards: List<CardType>,
    val jokers: Set<Int>,
    val bid: Int,
) : Comparable<Hand> {

    val type: HandType by lazy {
        val counts = cards.groupBy { card -> cards.count { it == card } }

        when {
            5 in counts -> HandType.FiveOfAKind
            4 in counts -> HandType.FourOfAKind
            3 in counts -> {
                if (2 in counts) HandType.FullHouse else HandType.ThreeOfAKind
            }

            2 in counts -> {
                if (counts.getValue(2).size == 4) HandType.TwoPair else HandType.OnePair
            }

            else -> HandType.HighCard
        }
    }

    override fun compareTo(other: Hand): Int {
        if (type != other.type) {
            return type.compareTo(other.type)
        }
        cards.forEachIndexed { index, o1Card ->
            val o1Actual = if (index in jokers) CardType.Joker else o1Card
            val otherCard = if (index in other.jokers) CardType.Joker else other.cards[index]

            if (o1Actual != otherCard) {
                return o1Actual.compareTo(otherCard)
            }
        }
        return 0
    }
}

@Suppress("EnumEntryName")
enum class CardType {
    Joker,
    `2`,
    `3`,
    `4`,
    `5`,
    `6`,
    `7`,
    `8`,
    `9`,
    T,
    J,
    Q,
    K,
    A,
}

enum class HandType {
    HighCard,
    OnePair,
    TwoPair,
    ThreeOfAKind,
    FullHouse,
    FourOfAKind,
    FiveOfAKind,
}