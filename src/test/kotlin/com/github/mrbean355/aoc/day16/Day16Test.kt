package com.github.mrbean355.aoc.day16

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.PuzzleTest

class Day16Test : PuzzleTest<Grid>(Day16) {

    override val part1TestCases = mapOf(
        "day16/example.txt" to 46,
        "day16/puzzle.txt" to 7939,
    )

    override val part2TestCases = mapOf(
        "day16/example.txt" to 51,
        "day16/puzzle.txt" to 8318,
    )

}