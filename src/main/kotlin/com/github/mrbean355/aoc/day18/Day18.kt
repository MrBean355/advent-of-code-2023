package com.github.mrbean355.aoc.day18

import com.github.mrbean355.aoc.base.Point
import com.github.mrbean355.aoc.base.Puzzle
import kotlin.math.max
import kotlin.math.min

object Day18 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        val boundary = constructBoundary(input)

        val minX = boundary.minOf(Point::x)
        val maxX = boundary.maxOf(Point::x)
        val minY = boundary.minOf(Point::y)
        val maxY = boundary.maxOf(Point::y)

        var inside = 0

        (minY..maxY).forEach { y ->
            (minX..maxX).forEach { x ->
                val point = Point(x, y)
                if (point in boundary || point.isInsidePit(boundary)) {
                    ++inside
                }
            }
        }

        return inside
    }

    override fun part2(input: List<String>): Any {
        return 0
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }

    private fun constructBoundary(input: List<String>): List<Point> {
        val boundary = mutableListOf<Point>()
        var x = 0
        var y = 0

        input.forEach { line ->
            val parts = line.split(' ')
            val direction = parts[0].single()
            val amount = parts[1].toInt()

            repeat(amount) {
                boundary += when (direction) {
                    'R' -> Point(++x, y)
                    'L' -> Point(--x, y)
                    'D' -> Point(x, ++y)
                    'U' -> Point(x, --y)
                    else -> error("Unexpected direction: $direction")
                }
            }
        }

        return boundary
    }

    private fun Point.isInsidePit(boundary: List<Point>): Boolean {
        var inside = false

        for (index in boundary.indices) {
            val current = boundary[index]
            val next = boundary[(index + 1) % boundary.size]

            if (y > min(current.y, next.y) &&
                y <= max(current.y, next.y) &&
                x <= max(current.x, next.x) &&
                current.y != next.y
            ) {
                val xIntersection = (y - current.y) * (next.x - current.x) / (next.y - current.y) + current.x
                if (current.x == next.x || x <= xIntersection) {
                    inside = !inside
                }
            }
        }

        return inside
    }
}