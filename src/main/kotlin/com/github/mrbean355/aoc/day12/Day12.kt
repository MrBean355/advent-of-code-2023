package com.github.mrbean355.aoc.day12

import com.github.mrbean355.aoc.base.Puzzle

private const val WORKING = '.'
private const val BROKEN = '#'
private const val UNKNOWN = '?'

object Day12 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        return input.sumOf { line ->
            val (springs, grouping) = line.split(' ')
            val groups = grouping.split(',').map(String::toInt)
            val regex = buildRegex(groups)

            countMatchingCombinations(springs, regex)
        }
    }

    override fun part2(input: List<String>): Any {
        return 0
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }

    private fun buildRegex(groups: List<Int>): Regex {
        return buildString {
            append("""\.*""")
            groups.forEachIndexed { index, count ->
                if (index < groups.size - 1) {
                    append("#{$count}\\.+")
                } else {
                    append("#{$count}\\.*")
                }
            }
        }.toRegex()
    }

    private fun countMatchingCombinations(springs: String, regex: Regex): Int {
        val unknown = springs.indexOf(UNKNOWN)
        if (unknown == -1) {
            return if (regex.matches(springs)) 1 else 0
        }
        return countMatchingCombinations(springs.replaceRange(unknown, unknown + 1, WORKING.toString()), regex) +
                countMatchingCombinations(springs.replaceRange(unknown, unknown + 1, BROKEN.toString()), regex)
    }
}