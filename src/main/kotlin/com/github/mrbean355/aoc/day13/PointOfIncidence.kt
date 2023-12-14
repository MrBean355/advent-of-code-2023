package com.github.mrbean355.aoc.day13

import com.github.mrbean355.aoc.base.Grid

private const val ASH = '.'
private const val ROCK = '#'

fun Grid.findReflection(): ReflectionLine {
    return findReflectionExcluding(ReflectionLine.None)
}

fun Grid.findReflectionWithSmudge(): ReflectionLine {
    val original = findReflection()

    forEachIndexed { index, ch ->
        this[index] = if (ch == ROCK) ASH else ROCK
        val other = findReflectionExcluding(original)
        this[index] = ch

        if (other != ReflectionLine.None && other != original) {
            return other
        }
    }

    return ReflectionLine.None
}

private fun Grid.findReflectionExcluding(
    excluding: ReflectionLine,
): ReflectionLine {

    val excludedColumn = if (excluding is ReflectionLine.Vertical) excluding.index else null
    val excludedRow = if (excluding is ReflectionLine.Horizontal) excluding.index else null

    getAllColumns().findReflectionPosition(excludedColumn)
        ?.let { return ReflectionLine.Vertical(it) }

    getAllRows().findReflectionPosition(excludedRow)
        ?.let { return ReflectionLine.Horizontal(it) }

    return ReflectionLine.None
}

private fun List<List<Char>>.findReflectionPosition(excludedIndex: Int?): Int? {
    (0..<size - 1).filter { it != excludedIndex }.forEach { index ->
        if (this[index] == this[index + 1]) {
            var i = index
            var j = index + 1

            while (this[i] == this[j]) {
                --i
                ++j

                if (i == -1 || j == size) {
                    // The reflection has reached the boundary, and we have found a valid solution!
                    return index + 1
                }
            }
        }
    }

    return null
}

sealed interface ReflectionLine {

    val position: Int

    val index: Int get() = position - 1

    data class Vertical(override val position: Int) : ReflectionLine

    data class Horizontal(override val position: Int) : ReflectionLine

    data object None : ReflectionLine {

        override val position: Int
            get() = throw UnsupportedOperationException()

    }
}