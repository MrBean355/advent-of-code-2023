package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.Puzzle

object Day1 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        return input.sumOf { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }

            "$first$last".toInt()
        }
    }

    override fun part2(input: List<String>): Any {
        return input.sumOf { line ->
            val first = line.getFirstNumber()
            val last = line.getLastNumber()

            "$first$last".toInt()
        }
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }
}