package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Puzzle
import com.github.mrbean355.aoc.base.flatten

object Day14 : Puzzle<Grid> {

    override fun part1(input: Grid): Any {
        input.tilt(Direction.North)
        return input.calculateLoad()
    }

    override fun part2(input: Grid): Any {
        val history = mutableMapOf<Int, Int>()
        var iteration = 1

        while (true) {
            Direction.entries.forEach { input.tilt(it) }

            val snapshot = input.hashCode()
            if (snapshot in history) {
                val diff = iteration - history.getValue(snapshot)
                if (reachesTargetIterations(iteration, diff)) {
                    return input.calculateLoad()
                }
            } else {
                history[snapshot] = iteration
            }

            ++iteration
        }
    }

    override fun mapInput(input: List<String>): Grid {
        return Grid(
            width = input.first().length,
            height = input.size,
            data = input.flatten()
        )
    }
}