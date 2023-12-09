package com.github.mrbean355.aoc.day6

import com.github.mrbean355.aoc.base.PuzzleTest

class Day6Test : PuzzleTest<List<String>>(Day6) {

    override val part1TestCases = mapOf(
        "day6/example.txt" to 288,
        "day6/puzzle.txt" to 840336,
    )

    override val part2TestCases = mapOf(
        "day6/example.txt" to 71503,
        "day6/puzzle.txt" to 41382569,
    )

}