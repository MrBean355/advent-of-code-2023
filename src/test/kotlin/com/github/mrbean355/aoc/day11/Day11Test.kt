package com.github.mrbean355.aoc.day11

import com.github.mrbean355.aoc.base.PuzzleTest

class Day11Test : PuzzleTest<List<String>>(Day11) {

    override val part1TestCases = mapOf(
        "day11/example.txt" to 374L,
        "day11/puzzle.txt" to 9639160L
    )

    override val part2TestCases = mapOf(
        "day11/puzzle.txt" to 752936133304L,
    )

}