package com.github.mrbean355.aoc.day13

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Puzzle

object Day13 : Puzzle<List<Grid>> {

    override fun part1(input: List<Grid>): Any {
        return input.fold(0) { acc, grid ->
            acc + (findReflectionPosition(grid.getAllColumns())
                ?: findReflectionPosition(grid.getAllRows())?.times(100)
                ?: 0)
        }
    }

    override fun part2(input: List<Grid>): Any {
        return 0
    }

    override fun mapInput(input: List<String>): List<Grid> {
        val grids = mutableListOf<List<String>>()
        var nextGrid = mutableListOf<String>()

        input.forEach { line ->
            if (line.isBlank()) {
                grids += nextGrid
                nextGrid = mutableListOf()
            } else {
                nextGrid += line
            }
        }

        grids += nextGrid

        return grids.map { grid ->
            Grid(
                width = grid.first().length,
                height = grid.size,
                data = grid.flatMap { it.toCharArray().toList() }
            )
        }
    }

    private fun findReflectionPosition(data: List<List<Char>>): Int? {
        (0..<data.size - 1).forEach { index ->
            if (data[index] == data[index + 1]) {
                var i = index
                var j = index + 1

                while (data[i] == data[j]) {
                    --i
                    ++j

                    if (i == -1 || j == data.size) {
                        // The reflection has reached the boundary, and we have found a valid solution!
                        return index + 1
                    }
                }
            }
        }

        return null
    }
}
