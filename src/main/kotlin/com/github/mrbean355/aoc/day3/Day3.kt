package com.github.mrbean355.aoc.day3

import com.github.mrbean355.aoc.base.Puzzle

private const val EMPTY = '.'
private const val GEAR = '*'

object Day3 : Puzzle<List<String>> {

    private val partNumberPattern = """(\d+)""".toRegex()

    override fun part1(input: List<String>): Any {
        val (partNumbers, symbols) = parse(input)

        return partNumbers.fold(0) { acc, partNumber ->
            val boundary = getBoundary(input, partNumber.index, partNumber.length)
            val hasSymbol = symbols.any {
                it.index in boundary
            }
            acc + if (hasSymbol) partNumber.value else 0
        }
    }

    override fun part2(input: List<String>): Any {
        val (partNumbers, symbols) = parse(input)

        return symbols.fold(0) { acc, symbol ->
            acc + if (symbol.value == GEAR) {
                val boundary = getBoundary(input, symbol.index, 1)
                val adjacent = partNumbers.filter { partNumber ->
                    partNumber.indices.any { it in boundary }
                }
                if (adjacent.size == 2) {
                    adjacent.fold(1) { acc, partNumber ->
                        acc * partNumber.value
                    }
                } else {
                    0
                }
            } else {
                0
            }
        }
    }

    private fun parse(input: List<String>): Pair<List<PartNumber>, List<Symbol>> {
        val width = input.first().length
        val partNumbers = mutableListOf<PartNumber>()
        val symbols = mutableListOf<Symbol>()

        input.forEachIndexed { y, line ->
            var x = 0

            while (x < line.length) {
                val match = partNumberPattern.matchAt(line, x)
                val index = y * width + x

                if (match != null) {
                    x += match.value.length
                    partNumbers += PartNumber(index, match.value.length, match.value.toInt())
                } else {
                    if (line[x] != EMPTY) {
                        symbols += Symbol(index, line[x])
                    }
                    ++x
                }
            }
        }

        return partNumbers to symbols
    }

    private fun getBoundary(input: List<String>, index: Int, length: Int): List<Int> {
        val width = input.first().length
        val height = input.size
        val startPos = index / height
        val endIndex = index + length - 1

        return buildList {
            val leftOffset = if (index % width == 0) 0 else 1
            val rightOffset = if (endIndex % width == width - 1) -1 else 0

            // above
            addAll((index - width - leftOffset)..(index - width + length + rightOffset))

            // left
            if ((index - 1) / height == startPos) {
                add(index - 1)
            }

            // right
            if ((index + length) / height == startPos) {
                add(index + length)
            }

            // below
            addAll((index + width - leftOffset)..(index + width + length + rightOffset))
        }.filter {
            it > 0 && it < width * height
        }
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }
}