package com.github.mrbean355.aoc.day5

import com.github.mrbean355.aoc.base.Puzzle

object Day5 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        val seeds = parseAlmanacSeeds(input)
        val sections = parseAlmanacSections(input)

        return seeds.minOf { seed ->
            sections.mapSeedToLocation(seed)
        }
    }

    override fun part2(input: List<String>): Any {
        val seedRanges = parseAlmanacSeedRanges(input)
        val sections = parseAlmanacSections(input)

        return seedRanges.minOf { range ->
            range.minOf { seed ->
                sections.mapSeedToLocation(seed)
            }
        }
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }

    private fun List<Section>.mapSeedToLocation(seed: Long): Long {
        return fold(seed) { acc, section ->
            section.mapSourceToDestination(acc)
        }
    }
}