package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Puzzle
import com.github.mrbean355.aoc.base.flatten

private const val ROUNDED_ROCK = 'O'
private const val EMPTY = '.'

object Day14 : Puzzle<Grid> {

    override fun part1(input: Grid): Any {
        var moved = true

        while (moved) {
            moved = false

            (1..<input.height).forEach { rowIndex ->
                val row = input.getRow(rowIndex)
                val above = input.getRow(rowIndex - 1)

                row.forEachIndexed { index, ch ->
                    if (ch == ROUNDED_ROCK && above[index] == EMPTY) {
                        input[index, rowIndex - 1] = ROUNDED_ROCK
                        input[index, rowIndex] = EMPTY
                        moved = true
                    }
                }
            }
        }

        return input.foldIndexed(0) { index, acc, ch ->
            acc + if (ch == ROUNDED_ROCK) {
                input.height - index / input.height
            } else {
                0
            }
        }
    }

    override fun part2(input: Grid): Any {
        return 0
    }

    override fun mapInput(input: List<String>): Grid {
        return Grid(
            width = input.first().length,
            height = input.size,
            data = input.flatten()
        )
    }
}