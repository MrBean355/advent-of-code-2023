package com.github.mrbean355.aoc.day15

import com.github.mrbean355.aoc.base.Puzzle

object Day15 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        return input.first().split(',').sumOf {
            it.hash()
        }
    }

    override fun part2(input: List<String>): Any {
        return 0
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }

    private fun String.hash(): Int {
        return fold(0) { acc, ch ->
            (acc + ch.code) * 17 % 256
        }
    }
}