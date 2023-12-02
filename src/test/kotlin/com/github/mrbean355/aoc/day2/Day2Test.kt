package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.PuzzleTest

class Day2Test : PuzzleTest<List<Game>>(Day2) {

    override val part1TestCases = mapOf(
        "day2/example.txt" to 8,
        "day2/puzzle.txt" to 2617,
    )

    override val part2TestCases = mapOf(
        "day2/example.txt" to 2286,
        "day2/puzzle.txt" to 59795,
    )

}