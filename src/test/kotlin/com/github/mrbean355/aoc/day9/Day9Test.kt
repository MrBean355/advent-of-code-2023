package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.PuzzleTest

class Day9Test : PuzzleTest<List<List<Int>>>(Day9) {

    override val part1TestCases = mapOf(
        "day9/example.txt" to 114,
        "day9/puzzle.txt" to 1898776583,
    )

    override val part2TestCases = mapOf(
        "day9/example.txt" to 2,
        "day9/puzzle.txt" to 1100,
    )

}