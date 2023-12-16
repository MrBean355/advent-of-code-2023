package com.github.mrbean355.aoc.day16

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Point
import com.github.mrbean355.aoc.base.Puzzle
import com.github.mrbean355.aoc.base.flatten

object Day16 : Puzzle<Grid> {

    override fun part1(input: Grid): Any {
        return Contraption(input).run(Point(-1, 0), Direction.Right)
    }

    override fun part2(input: Grid): Any {
        val contraption = Contraption(input)

        return maxOf(
            // Top-down
            (0..<input.width).maxOf {
                contraption.run(Point(it, -1), Direction.Down)
            },
            // Bottom-up
            (0..<input.width).maxOf {
                contraption.run(Point(it, input.height), Direction.Up)
            },
            // Left-to-right
            (0..<input.height).maxOf {
                contraption.run(Point(-1, it), Direction.Right)
            },
            // Right-to-left
            (0..<input.height).maxOf {
                contraption.run(Point(input.width, it), Direction.Left)
            },
        )
    }

    override fun mapInput(input: List<String>): Grid {
        return Grid(
            width = input.first().length,
            height = input.size,
            data = input.flatten()
        )
    }
}