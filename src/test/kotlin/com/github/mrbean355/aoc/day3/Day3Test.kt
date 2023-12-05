package com.github.mrbean355.aoc.day3

import com.github.mrbean355.aoc.base.PuzzleTest

class Day3Test : PuzzleTest<List<String>>(Day3) {

    override val part1TestCases = mapOf(
        "day3/example.txt" to 4_361,
        "day3/puzzle.txt" to 536_576,
    )

    override val part2TestCases: Map<String, Int> = mapOf(
        "day3/example.txt" to 467_835,
        "day3/puzzle.txt" to 75_741_499,
    )

}