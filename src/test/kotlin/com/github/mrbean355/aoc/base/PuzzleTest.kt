package com.github.mrbean355.aoc.base

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.fail
import kotlin.time.measureTimedValue

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
        part1TestCases.forEach { (inputFileName, expected) ->
            runTestCase(inputFileName, expected, implementation::part1)
        }
    }

    @Test
    fun runPart2TestCases() {
        part2TestCases.forEach { (inputFileName, expected) ->
            runTestCase(inputFileName, expected, implementation::part2)
        }
    }

    private fun runTestCase(
        inputFileName: String,
        expected: Any,
        action: (input: Input) -> Any,
    ) {
        val input = implementation.mapInput(inputFileName.loadFileContent())

        val timedValue = runCatching {
            measureTimedValue { action(input) }
        }.getOrElse {
            fail("An exception was thrown for input $inputFileName: ${it.stackTraceToString()}")
        }

        println("$inputFileName took ${timedValue.duration}")
        assertEquals(expected, timedValue.value, "Wrong output for $inputFileName:")
    }

    private fun String.loadFileContent(): List<String> {
        val res = PuzzleTest::class.java.classLoader.getResource(this)
        check(res != null) { "Couldn't load resource: $this" }
        return File(res.toURI()).readLines()
    }
}