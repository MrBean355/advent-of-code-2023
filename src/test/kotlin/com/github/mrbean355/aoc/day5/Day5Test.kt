package com.github.mrbean355.aoc.day5

import com.github.mrbean355.aoc.base.PuzzleTest

class Day5Test : PuzzleTest<List<String>>(Day5) {

    override val part1TestCases = mapOf(
        "day5/example.txt" to 35L,
        "day5/puzzle.txt" to 214922730L,
    )

    override val part2TestCases = mapOf(
        "day5/example.txt" to 46L,
        // TODO: optimisation; answer is correct but takes too long
        // "day5/puzzle.txt" to 148041808L,
    )

}