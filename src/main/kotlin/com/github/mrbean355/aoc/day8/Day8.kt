package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.Puzzle

object Day8 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        return countStepsToEnd(
            parseInstructions(input),
            parseMap(input),
            "AAA",
            listOf("ZZZ")
        )
    }

    override fun part2(input: List<String>): Any {
        val instructions = parseInstructions(input)
        val map = parseMap(input)
        val startNodes = map.keys.filter { it.endsWith('A') }
        val endNodes = map.keys.filter { it.endsWith('Z') }

        return startNodes
            .map { start -> countStepsToEnd(instructions, map, start, endNodes) }
            .findLeastCommonMultiple()
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }

    private fun parseInstructions(input: List<String>): CharArray {
        return input.first().toCharArray()
    }

    private fun parseMap(input: List<String>): Map<String, Pair<String, String>> {
        return input.drop(2).associate { line ->
            val (label, left, right) = line.split(" = (", ", ", ")")
            label to (left to right)
        }
    }

    private fun countStepsToEnd(
        instructions: CharArray,
        map: Map<String, Pair<String, String>>,
        startNode: String,
        endNodes: List<String>
    ): Int {
        var i = 0
        var steps = 0
        var current = startNode

        while (current !in endNodes) {
            val nextNodes = map.getValue(current)

            current = when (instructions[i]) {
                'L' -> nextNodes.first
                'R' -> nextNodes.second
                else -> error("Unexpected instruction: ${instructions[i]}")
            }

            ++steps
            i = (i + 1) % instructions.size
        }

        return steps
    }

    private fun List<Int>.findLeastCommonMultiple(): Long {
        val min = min()
        var result = min.toLong()

        while (true) {
            if (all { result % it == 0L }) {
                return result
            }
            result += min
        }
    }
}