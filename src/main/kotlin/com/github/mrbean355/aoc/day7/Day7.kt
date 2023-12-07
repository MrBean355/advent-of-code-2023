package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.Puzzle

object Day7 : Puzzle<List<CardHand>> {

    override fun part1(input: List<CardHand>): Any {
        return input
            .sortedWith(HandComparator)
            .calculateWinnings()
    }

    override fun part2(input: List<CardHand>): Any {
        return input
            .map(::findBestHand)
            .sortedWith(HandWithJokersComparator)
            .calculateWinnings()
    }

    override fun mapInput(input: List<String>): List<CardHand> {
        return input.map { line ->
            val (cards, bidAmount) = line.split(' ')
            CardHand(
                cards.map { Card.valueOf(it.toString()) },
                bidAmount.toInt()
            )
        }
    }

    private fun List<CardHand>.calculateWinnings(): Int {
        return foldIndexed(0) { index, acc, hand ->
            acc + (index + 1) * hand.bidAmount
        }
    }
}