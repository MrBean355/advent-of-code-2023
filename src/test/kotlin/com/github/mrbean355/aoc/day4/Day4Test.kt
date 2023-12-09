package com.github.mrbean355.aoc.day4

import com.github.mrbean355.aoc.base.PuzzleTest

class Day4Test : PuzzleTest<List<ScratchCard>>(Day4) {

    override val part1TestCases = mapOf(
        "day4/example.txt" to 13,
        "day4/puzzle.txt" to 26443,
    )

    override val part2TestCases = mapOf(
        "day4/example.txt" to 30,
        "day4/puzzle.txt" to 6284877,
    )

}