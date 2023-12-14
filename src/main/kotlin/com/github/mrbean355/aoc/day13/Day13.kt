package com.github.mrbean355.aoc.day13

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Puzzle

object Day13 : Puzzle<List<Grid>> {

    override fun part1(input: List<Grid>): Any {
        return input.summarise(Grid::findReflection)
    }

    override fun part2(input: List<Grid>): Any {
        return input.summarise(Grid::findReflectionWithSmudge)
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

    private fun List<Grid>.summarise(
        findReflection: Grid.() -> ReflectionLine,
    ): Int {
        return fold(0) { acc, grid ->
            acc + when (val r = grid.findReflection()) {
                is ReflectionLine.Vertical -> r.position
                is ReflectionLine.Horizontal -> r.position * 100
                ReflectionLine.None -> 0
            }
        }
    }
}
