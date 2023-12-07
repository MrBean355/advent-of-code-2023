package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.PuzzleTest

class Day7Test : PuzzleTest<List<Hand>>(Day7) {

    override val part1TestCases = mapOf(
        "day7/example.txt" to 6440,
        "day7/puzzle.txt" to 250120186,
    )

    override val part2TestCases = mapOf(
        "day7/example.txt" to 5905,
        "day7/puzzle.txt" to 250665248,
    )

}