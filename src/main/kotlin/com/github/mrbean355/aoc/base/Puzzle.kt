package com.github.mrbean355.aoc.base

interface Puzzle<Input> {

    fun part1(input: Input): Any

    fun part2(input: Input): Any

    /** Map input file lines to the puzzle input type. */
    fun mapInput(input: List<String>): Input

}
