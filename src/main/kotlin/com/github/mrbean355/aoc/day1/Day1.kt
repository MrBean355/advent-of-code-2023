package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.Puzzle

class Day1(
    private val input: List<String>,
) : Puzzle {

    override fun part1(): Any {
        return input.sumOf { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }

            "$first$last".toInt()
        }
    }

    override fun part2(): Any {
        return input.sumOf { line ->
            val first = line.getFirstNumber()
            val last = line.getLastNumber()

            "$first$last".toInt()
        }
    }
}

private fun String.getFirstNumber(): Char {
    val buffer = StringBuilder()
    forEach { ch ->
        if (ch.isDigit()) {
            return ch
        }
        buffer.append(ch)
        buffer.findNumber()?.let { return it }
    }
    error("Unable to find a number in $this")
}

private fun String.getLastNumber(): Char {
    val buffer = StringBuilder()
    reversed().forEach { ch ->
        if (ch.isDigit()) {
            return ch
        }
        buffer.insert(0, ch)
        buffer.findNumber()?.let { return it }
    }
    error("Unable to find a number in $this")
}

private fun CharSequence.findNumber(): Char? {
    return when {
        "one" in this -> '1'
        "two" in this -> '2'
        "three" in this -> '3'
        "four" in this -> '4'
        "five" in this -> '5'
        "six" in this -> '6'
        "seven" in this -> '7'
        "eight" in this -> '8'
        "nine" in this -> '9'
        else -> null
    }
}