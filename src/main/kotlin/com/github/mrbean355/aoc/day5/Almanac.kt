package com.github.mrbean355.aoc.day5

data class Section(
    val mappings: List<Mapping>,
) {

    fun mapSourceToDestination(source: Long): Long {
        val mapping = mappings.find { source in it.sourceRange }
            ?: return source

        val sourceStartOffset = source - mapping.sourceRange.first
        return mapping.destinationStart + sourceStartOffset
    }
}

data class Mapping(
    val sourceRange: LongRange,
    val destinationStart: Long,
)

fun parseAlmanacSeeds(input: List<String>): List<Long> {
    return input.first().substringAfter(": ").split(' ').map(String::toLong)
}

fun parseAlmanacSeedRanges(input: List<String>): List<LongRange> {
    return parseAlmanacSeeds(input).windowed(size = 2, step = 2)
        .map { it[0]..<it[0] + it[1] }
}

fun parseAlmanacSections(input: List<String>): List<Section> {
    val sections = mutableListOf<Section>()
    var lineIndex = 2

    while (lineIndex < input.size) {
        val mappings = mutableListOf<Mapping>()
        var line: String
        ++lineIndex

        while (true) {
            line = input[lineIndex]
            if (line.isBlank()) {
                break
            }
            val (destStart, srcStart, length) = line.split(' ').map(String::toLong)
            mappings += Mapping(
                srcStart..<(srcStart + length),
                destStart,
            )
            if (++lineIndex == input.size) {
                break
            }
        }

        sections += Section(mappings)
        ++lineIndex
    }

    return sections
}