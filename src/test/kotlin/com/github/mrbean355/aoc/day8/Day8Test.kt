package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.PuzzleTest

class Day8Test : PuzzleTest<List<String>>(Day8) {

    override val part1TestCases = mapOf(
        "day8/example_1.txt" to 2,
        "day8/example_2.txt" to 6,
        "day8/puzzle.txt" to 17621,
    )

    override val part2TestCases = mapOf(
        "day8/example_3.txt" to 6L,
        "day8/puzzle.txt" to 20685524831999L,
    )

}