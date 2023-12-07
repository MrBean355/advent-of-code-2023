package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.Puzzle

object Day7 : Puzzle<List<Hand>> {

    override fun part1(input: List<Hand>): Any {
        return input.sorted().calculateWinnings()
    }

    override fun part2(input: List<Hand>): Any {
        return input.map { hand ->
            if (CardType.J in hand.cards) {
                hand.copy(cards = hand.cards.map {
                    if (it == CardType.J) CardType.Joker else it
                })
            } else {
                hand
            }
        }.map(::findBestCombo)
            .sorted()
            .calculateWinnings()
    }

    override fun mapInput(input: List<String>): List<Hand> {
        return input.map { line ->
            val (cards, bid) = line.split(' ')
            Hand(cards.parseCards(), emptySet(), bid.toInt())
        }
    }

    private fun String.parseCards(): List<CardType> {
        return map { CardType.valueOf(it.toString()) }
    }

    private fun List<Hand>.calculateWinnings(): Int {
        return foldIndexed(0) { index, acc, hand ->
            acc + (index + 1) * hand.bid
        }
    }
}

fun findBestCombo(hand: Hand): Hand {
    if (hand.cards.none { it == CardType.Joker }) {
        return hand
    }

    var result = hand
    val joker = hand.cards.indexOfFirst { it == CardType.Joker }

    CardType.entries.filter { it != CardType.Joker && it != CardType.J }
        .forEach { possibleCard ->
            val cards = hand.cards.toMutableList().also {
                it[joker] = possibleCard
            }
            val handCopy = hand.copy(cards = cards, jokers = hand.jokers + joker)
            result = maxOf(result, findBestCombo(handCopy))
        }

    return result
}