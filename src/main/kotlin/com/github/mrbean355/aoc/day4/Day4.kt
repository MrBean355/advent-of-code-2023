package com.github.mrbean355.aoc.day4

import com.github.mrbean355.aoc.base.Puzzle
import kotlin.math.pow

object Day4 : Puzzle<List<ScratchCard>> {

    override fun part1(input: List<ScratchCard>): Any {
        return input.sumOf { card ->
            2.0.pow(card.matches - 1).toInt()
        }
    }

    override fun part2(input: List<ScratchCard>): Any {
        input.forEachIndexed { index, card ->
            repeat(card.matches) {
                input[index + it + 1].copies += card.copies
            }
        }

        return input.sumOf(ScratchCard::copies)
    }

    override fun mapInput(input: List<String>): List<ScratchCard> {
        return input.map { card ->
            val separator = card.indexOf('|')
            val winningNumbers = card.substring(0, separator).substringAfter(':').toNumbers()
            val myNumbers = card.substring(separator + 1).toNumbers()
            val matches = myNumbers.count { it in winningNumbers }

            ScratchCard(matches)
        }
    }

    private fun String.toNumbers(): List<Int> {
        return split(' ')
            .filter(String::isNotBlank)
            .map(String::toInt)
    }
}

class ScratchCard(
    val matches: Int,
) {
    var copies: Int = 1
}