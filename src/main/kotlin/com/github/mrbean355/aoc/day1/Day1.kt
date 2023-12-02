package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.StringPuzzle

object Day1 : StringPuzzle() {

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
}