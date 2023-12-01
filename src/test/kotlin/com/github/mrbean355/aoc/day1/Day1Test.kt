package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.PuzzleTest

class Day1Test : PuzzleTest(Day1::class) {

    override val part1TestCases = mapOf(
        "day1/example.txt" to 142,
        "day1/puzzle.txt" to 54601,
    )

    override val part2TestCases: Map<String, Any>
        get() = emptyMap()

}