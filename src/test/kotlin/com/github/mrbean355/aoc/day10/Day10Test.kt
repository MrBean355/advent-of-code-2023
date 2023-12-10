package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.PuzzleTest

class Day10Test : PuzzleTest<List<String>>(Day10) {

    override val part1TestCases = mapOf(
        "day10/example_1.txt" to 4,
        "day10/example_2.txt" to 8,
        "day10/puzzle.txt" to 6875,
    )

    override val part2TestCases = mapOf(
        "day10/example_1.txt" to 0,
        // "day10/puzzle.txt" to 0,
    )

}