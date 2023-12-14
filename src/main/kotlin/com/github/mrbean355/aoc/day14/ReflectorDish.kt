package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Grid

private const val ROUND_ROCK = 'O'
private const val EMPTY = '.'
private const val ITERATIONS = 1_000_000_000

enum class Direction {
    North, West, South, East
}

fun Grid.tilt(direction: Direction) {
    when (direction) {
        Direction.North -> tiltVertically(1..<height, -1)
        Direction.West -> tiltHorizontally(1..<width, -1)
        Direction.South -> tiltVertically(height - 2 downTo 0, 1)
        Direction.East -> tiltHorizontally(width - 2 downTo 0, 1)
    }
}

fun reachesTargetIterations(start: Int, increment: Int): Boolean {
    var iteration = start

    while (iteration < ITERATIONS) {
        iteration += increment
    }

    return iteration == ITERATIONS
}

fun Grid.calculateLoad(): Int {
    return foldIndexed(0) { index, acc, ch ->
        acc + if (ch == ROUND_ROCK) {
            (height - index / height)
        } else {
            0
        }
    }
}

private fun Grid.tiltVertically(
    rowIndices: Iterable<Int>,
    offset: Int,
) {
    while (true) {
        var moved = false

        rowIndices.forEach { rowIndex ->
            val current = getRow(rowIndex)
            val other = getRow(rowIndex + offset)

            current.forEachIndexed { index, ch ->
                if (ch == ROUND_ROCK && other[index] == EMPTY) {
                    set(index, rowIndex + offset, ROUND_ROCK)
                    set(index, rowIndex, EMPTY)
                    moved = true
                }
            }
        }

        if (!moved) {
            break
        }
    }
}

private fun Grid.tiltHorizontally(
    columnIndices: Iterable<Int>,
    offset: Int,
) {
    while (true) {
        var moved = false

        columnIndices.forEach { columnIndex ->
            val current = getColumn(columnIndex)
            val other = getColumn(columnIndex + offset)

            current.forEachIndexed { index, ch ->
                if (ch == ROUND_ROCK && other[index] == EMPTY) {
                    set(columnIndex + offset, index, ROUND_ROCK)
                    set(columnIndex, index, EMPTY)
                    moved = true
                }
            }
        }

        if (!moved) {
            break
        }
    }
}