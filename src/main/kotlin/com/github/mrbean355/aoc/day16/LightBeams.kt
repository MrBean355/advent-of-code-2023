package com.github.mrbean355.aoc.day16

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Point

class Contraption(
    private val grid: Grid,
) {

    fun run(start: Point, direction: Direction): Int {
        val beams = mutableListOf(Beam(start, direction))
        val energized = mutableSetOf<Point>()

        // Keep track of splitters that we have used before.
        // Using a splitter again will always have the same effect, so we don't need to repeat it.
        // It also avoids infinite loops from beams reflecting continuously, which is nice.
        val usedSplitters = mutableSetOf<Point>()

        while (beams.isNotEmpty()) {
            val toRemove = mutableSetOf<Beam>()
            val toAdd = mutableSetOf<Beam>()

            beams.forEach { beam ->
                beam.move()

                if (!grid.isInBounds(beam.location)) {
                    toRemove += beam
                    return@forEach
                }

                energized += beam.location

                when (grid[beam.location.x, beam.location.y]) {
                    '|' -> {
                        if (beam.direction.isHorizontal()) {
                            if (usedSplitters.add(beam.location)) {
                                beam.direction = Direction.Up
                                toAdd += Beam(beam.location, Direction.Down)
                            } else {
                                // The splitter has been used before, this beam can dissipate.
                                toRemove += beam
                            }
                        }
                    }

                    '-' -> {
                        if (beam.direction.isVertical()) {
                            if (usedSplitters.add(beam.location)) {
                                beam.direction = Direction.Left
                                toAdd += Beam(beam.location, Direction.Right)
                            } else {
                                // The splitter has been used before, this beam can dissipate.
                                toRemove += beam
                            }
                        }
                    }

                    '/' -> {
                        beam.direction = when (beam.direction) {
                            Direction.Left -> Direction.Down
                            Direction.Up -> Direction.Right
                            Direction.Right -> Direction.Up
                            Direction.Down -> Direction.Left
                        }
                    }

                    '\\' -> {
                        beam.direction = when (beam.direction) {
                            Direction.Left -> Direction.Up
                            Direction.Up -> Direction.Left
                            Direction.Right -> Direction.Down
                            Direction.Down -> Direction.Right
                        }
                    }

                    '.' -> Unit
                }
            }

            beams -= toRemove
            beams += toAdd
        }

        return energized.size
    }
}

enum class Direction(
    val xVelocity: Int,
    val yVelocity: Int,
) {
    Left(-1, 0),
    Up(0, -1),
    Right(1, 0),
    Down(0, 1),
}

private data class Beam(
    var location: Point,
    var direction: Direction,
)

private fun Beam.move() {
    location = Point(
        x = location.x + direction.xVelocity,
        y = location.y + direction.yVelocity,
    )
}

private fun Grid.isInBounds(point: Point): Boolean {
    return point.x in 0..<width && point.y in 0..<height
}

private fun Direction.isHorizontal(): Boolean {
    return this == Direction.Left || this == Direction.Right
}

private fun Direction.isVertical(): Boolean {
    return !isHorizontal()
}