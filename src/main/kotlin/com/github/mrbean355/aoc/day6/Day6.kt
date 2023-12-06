package com.github.mrbean355.aoc.day6

import com.github.mrbean355.aoc.base.Puzzle

object Day6 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        val times = input[0].asNumbers()
        val distances = input[1].asNumbers()

        return times.foldIndexed(1) { index, acc, raceDuration ->
            val bestDistance = distances[index]
            acc * countRecordBeatingSolutions(raceDuration, bestDistance)
        }
    }

    override fun part2(input: List<String>): Any {
        val time = input[0].asSingleNumber()
        val distance = input[1].asSingleNumber()

        return countRecordBeatingSolutions(time, distance)
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }

    private fun String.asNumbers(): List<Long> {
        return substringAfter(':')
            .split(' ')
            .filter(String::isNotBlank)
            .map(String::toLong)
    }

    private fun String.asSingleNumber(): Long {
        return substringAfter(':')
            .replace(" ", "")
            .toLong()
    }

    private fun countRecordBeatingSolutions(raceDuration: Long, bestDistance: Long): Int {
        // This can be optimised, but does not actually provide much benefit, since only arithmetic operations are happening.
        // As the button press duration increases from 0, the distance travelled also increases.
        // However, we reach a point where the distance starts to decrease as the press duration increases.
        // Once the distance has gone lower that the best distance, we can discard all leftover iterations.
        return (1..<raceDuration).count { buttonPressDuration ->
            calculateDistance(buttonPressDuration, raceDuration) > bestDistance
        }
    }

    private fun calculateDistance(buttonPressDuration: Long, raceDuration: Long): Long {
        val speed = buttonPressDuration
        val duration = raceDuration - buttonPressDuration
        return speed * duration
    }
}