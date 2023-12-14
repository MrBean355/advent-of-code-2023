package com.github.mrbean355.aoc.day13

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.PuzzleTest

class Day13Test : PuzzleTest<List<Grid>>(Day13) {

    override val part1TestCases = mapOf(
        "day13/example.txt" to 405,
        "day13/puzzle.txt" to 33195,
    )

    override val part2TestCases = mapOf(
        "day13/example.txt" to 400,
        "day13/puzzle.txt" to 31836,
    )

}