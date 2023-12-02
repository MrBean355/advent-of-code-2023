package com.github.mrbean355.aoc.base

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

/**
 * Base test class to make unit testing puzzles easier.
 */
abstract class PuzzleTest<Input>(
    private val implementation: Puzzle<Input>,
) {

    /** Map of input files to expected outputs. */
    abstract val part1TestCases: Map<String, Any>

    /** Map of input files to expected outputs. */
    abstract val part2TestCases: Map<String, Any>

    @Test
    fun runPart1TestCases() {
        part1TestCases.forEach { (inputFile, expected) ->
            val input = implementation.mapInput(inputFile.load())

            val actual = implementation.part1(input)

            assertEquals(expected, actual, "Wrong output for $inputFile:")
        }
    }

    @Test
    fun runPart2TestCases() {
        part2TestCases.forEach { (inputFile, expected) ->
            val input = implementation.mapInput(inputFile.load())

            val actual = implementation.part2(input)

            assertEquals(expected, actual, "Wrong output for $inputFile:")
        }
    }

    private fun String.load(): List<String> {
        val res = PuzzleTest::class.java.classLoader.getResource(this)
        check(res != null) { "Couldn't load resource: $this" }
        return File(res.toURI()).readLines()
    }
}