package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.Puzzle

class Day1(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.sumOf { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }

            "$first$last".toInt()
        }
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }
}