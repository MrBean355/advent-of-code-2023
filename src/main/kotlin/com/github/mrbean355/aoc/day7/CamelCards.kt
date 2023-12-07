package com.github.mrbean355.aoc.day7

data class CardHand(
    val cards: List<Card>,
    val bidAmount: Int,
    /** Keep track of cards that used to be jokers. */
    val jokerIndices: Set<Int> = emptySet(),
) {

    val type: HandType = HandType.of(cards)

    fun substituteJoker(index: Int, replacement: Card): CardHand {
        return copy(
            cards = cards.toMutableList().apply {
                set(index, replacement)
            },
            jokerIndices = jokerIndices.plus(index)
        )
    }
}

fun findBestHand(cardHand: CardHand): CardHand {
    if (cardHand.cards.none { it == Card.J }) {
        return cardHand
    }

    var bestHand = cardHand
    val jokerIndex = cardHand.cards.indexOfFirst { it == Card.J }

    Card.entries.filter { it != Card.J }
        .forEach { possibleCard ->
            val handCopy = cardHand.substituteJoker(jokerIndex, possibleCard)
            bestHand = maxOf(bestHand, findBestHand(handCopy), HandWithJokersComparator)
        }

    return bestHand
}

object HandComparator : Comparator<CardHand> {

    override fun compare(lhs: CardHand, rhs: CardHand): Int {
        if (lhs.type != rhs.type) {
            return lhs.type.compareTo(rhs.type)
        }
        lhs.cards.forEachIndexed { index, lhsCard ->
            val rhsCard = rhs.cards[index]
            if (lhsCard != rhsCard) {
                return lhsCard.compareTo(rhsCard)
            }
        }
        return 0
    }
}

object HandWithJokersComparator : Comparator<CardHand> {
    private const val JOKER_STRENGTH = -1

    override fun compare(lhs: CardHand, rhs: CardHand): Int {
        if (lhs.type != rhs.type) {
            return lhs.type.compareTo(rhs.type)
        }

        lhs.cards.indices.forEach { index ->
            val lhsStrength = lhs.getCardStrength(index)
            val rhsStrength = rhs.getCardStrength(index)

            if (lhsStrength != rhsStrength) {
                return lhsStrength.compareTo(rhsStrength)
            }
        }

        return 0
    }

    private fun CardHand.getCardStrength(index: Int): Int {
        // Joker cards are always the weakest.
        return if (cards[index] == Card.J || index in jokerIndices) {
            JOKER_STRENGTH
        } else {
            cards[index].ordinal
        }
    }
}

@Suppress("EnumEntryName")
enum class Card {
    `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, T, J, Q, K, A,
}

enum class HandType {
    HighCard,
    OnePair,
    TwoPair,
    ThreeOfAKind,
    FullHouse,
    FourOfAKind,
    FiveOfAKind;

    companion object {

        fun of(cards: List<Card>): HandType {
            val counts = cards.groupBy { card -> cards.count { it == card } }

            return when {
                5 in counts -> FiveOfAKind
                4 in counts -> FourOfAKind
                3 in counts -> if (2 in counts) FullHouse else ThreeOfAKind
                2 in counts -> if (counts.getValue(2).size == 4) TwoPair else OnePair
                else -> HighCard
            }
        }
    }
}