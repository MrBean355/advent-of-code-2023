package com.github.mrbean355.aoc.day10

import kotlin.math.sqrt

private const val START = 'S'
private const val EMPTY = '.'

class Maze(
    input: List<Char>
) : Iterable<Char> {

    private val size = sqrt(1f * input.size).toInt()
    private val pipes = input

    val start: Int = pipes.indexOf(START)

    fun isEmpty(index: Int): Boolean {
        return pipes[index] == EMPTY
    }

    fun getNeighbours(index: Int): List<Int> {
        return listOfNotNull(
            /* Left   */ (index - 1).takeIf { index % size > 0 },
            /* Top    */ (index - size).takeIf { it >= 0 },
            /* Right  */ (index + 1).takeIf { index % size < size - 1 },
            /* Bottom */ (index + size).takeIf { it < size * size },
        )
    }

    fun canConnect(a: Int, b: Int): Boolean {
        if (pipes[a] == EMPTY || pipes[b] == EMPTY) {
            // Pipes can't connect to nothingness.
            return false
        }

        val pipeA = Pipe.from(pipes[a])
        val pipeB = Pipe.from(pipes[b])

        val (ax, ay) = a.indexToPoint()
        val (bx, by) = b.indexToPoint()

        return if (ay == by) {
            if (ax < bx) {
                pipeA.east && pipeB.west
            } else {
                pipeA.west && pipeB.east
            }
        } else {
            if (ay < by) {
                pipeA.south && pipeB.north
            } else {
                pipeA.north && pipeB.south
            }
        }
    }

    override fun iterator(): Iterator<Char> {
        return object : Iterator<Char> {
            private var index = 0

            override fun hasNext(): Boolean {
                return index < pipes.size
            }

            override fun next(): Char {
                return pipes[index++]
            }
        }
    }

    private fun Int.indexToPoint(): Pair<Int, Int> {
        return mod(size) to div(size)
    }

    @Suppress("EnumEntryName")
    private enum class Pipe(
        val north: Boolean = false,
        val east: Boolean = false,
        val south: Boolean = false,
        val west: Boolean = false,
    ) {
        Start(north = true, east = true, south = true, west = true),
        Vertical(north = true, south = true),
        Horizontal(east = true, west = true),
        LBend(north = true, east = true),
        JBend(north = true, west = true),
        `7Bend`(south = true, west = true),
        FBend(south = true, east = true);

        companion object {

            fun from(char: Char): Pipe {
                return when (char) {
                    START -> Start
                    '|' -> Vertical
                    '-' -> Horizontal
                    'L' -> LBend
                    'J' -> JBend
                    '7' -> `7Bend`
                    'F' -> FBend
                    else -> error("Unexpected pipe: $char")
                }
            }
        }
    }
}