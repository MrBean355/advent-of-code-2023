package com.github.mrbean355.aoc.day11

import com.github.mrbean355.aoc.base.Puzzle

object Day11 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        val space = Space(
            size = input.size,
            chars = input.flatMap { it.toCharArray().toList() },
            expansionRate = 1
        )

        return space.calculateDistances().sum()
    }

    override fun part2(input: List<String>): Any {
        val space = Space(
            size = input.size,
            chars = input.flatMap { it.toCharArray().toList() },
            expansionRate = 999_999
        )

        return space.calculateDistances().sum()
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }
}