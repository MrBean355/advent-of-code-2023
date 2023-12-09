package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.Puzzle

object Day9 : Puzzle<List<List<Int>>> {

    override fun part1(input: List<List<Int>>): Any {
        return input.sumOf {
            predictNextValue(it)
        }
    }

    override fun part2(input: List<List<Int>>): Any {
        return input.sumOf {
            extrapolatePreviousValue(it)
        }
    }

    override fun mapInput(input: List<String>): List<List<Int>> {
        return input.map { line ->
            line.split(' ').map(String::toInt)
        }
    }

    private fun predictNextValue(values: List<Int>): Int {
        val diffs = values.differences()
        if (diffs.all { it == 0 }) {
            return values.last()
        }
        return values.last() + predictNextValue(diffs)
    }

    private fun extrapolatePreviousValue(values: List<Int>): Int {
        val diffs = values.differences()
        if (diffs.all { it == 0 }) {
            return values.first()
        }
        return values.first() - extrapolatePreviousValue(diffs)
    }

    private fun List<Int>.differences(): List<Int> {
        return windowed(size = 2).map { (left, right) ->
            right - left
        }
    }
}