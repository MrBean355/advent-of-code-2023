package com.github.mrbean355.aoc.day11

import kotlin.math.abs

private const val GALAXY = '#'

class Space(
    private val size: Int,
    private val chars: List<Char>,
    private val expansionRate: Long,
) {

    private val galaxies = chars.withIndex().filter { it.value == GALAXY }.map { it.index }
    private val emptyRows = (0..<size).filter { index -> row(index).none { it == GALAXY } }
    private val emptyColumns = (0..<size).filter { index -> column(index).none { it == GALAXY } }

    fun calculateDistances(): List<Long> {
        val result = mutableListOf<Long>()

        (0..<galaxies.size - 1).forEach { source ->
            val (sx, sy) = galaxies[source].xy()

            (source + 1..<galaxies.size).forEach { destination ->
                val (dx, dy) = galaxies[destination].xy()

                val dist = abs(dx - sx) + abs(dy - sy)
                val expandedColumns = valuesInBetween(sx, dx).count { it in emptyColumns }
                val expandedRows = valuesInBetween(sy, dy).count { it in emptyRows }

                result += dist + expandedColumns * expansionRate + expandedRows * expansionRate
            }
        }

        return result
    }

    private fun column(index: Int): List<Char> {
        return buildList {
            repeat(this@Space.size) {
                add(chars[index + it * this@Space.size])
            }
        }
    }

    private fun row(index: Int): List<Char> {
        val start = index * size
        return chars.subList(start, start + size)
    }

    private fun Int.xy(): Pair<Int, Int> {
        return mod(size) to div(size)
    }

    private fun valuesInBetween(a: Int, b: Int): Iterable<Int> {
        return if (a < b) {
            (a + 1..<b)
        } else {
            (b + 1..<a)
        }
    }
}