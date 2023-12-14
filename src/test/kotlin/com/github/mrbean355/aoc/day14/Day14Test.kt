package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.PuzzleTest

class Day14Test : PuzzleTest<Grid>(Day14) {

    override val part1TestCases = mapOf(
        "day14/example.txt" to 136,
        "day14/puzzle.txt" to 106186,
    )

    override val part2TestCases = mapOf(
        "day14/example.txt" to 0,
        // "day14/puzzle.txt" to 0,
    )
}