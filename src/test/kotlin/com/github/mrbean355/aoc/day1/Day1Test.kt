package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.PuzzleTest

class Day1Test : PuzzleTest<List<String>>(Day1) {

    override val part1TestCases = mapOf(
        "day1/example_part1.txt" to 142,
        "day1/puzzle.txt" to 54601,
    )

    override val part2TestCases = mapOf(
        "day1/example_part2.txt" to 281,
        "day1/puzzle.txt" to 54078,
    )

}