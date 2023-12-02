package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.Puzzle

class Day2(
    private val input: List<String>,
) : Puzzle {

    override fun part1(): Int {
        return input.map(Game::from).filter { game ->
            game.draws.all { draw ->
                draw.all {
                    it.second <= when (it.first) {
                        Cube.Red -> 12
                        Cube.Green -> 13
                        Cube.Blue -> 14
                    }
                }
            }
        }.sumOf(Game::id)
    }

    override fun part2(): Any {
        return input.map(Game::from).sumOf { game ->
            var minRed = 0
            var minGreen = 0
            var minBlue = 0

            game.draws.forEach { draw ->
                draw.forEach {
                    when (it.first) {
                        Cube.Red -> minRed = maxOf(minRed, it.second)
                        Cube.Green -> minGreen = maxOf(minGreen, it.second)
                        Cube.Blue -> minBlue = maxOf(minBlue, it.second)
                    }
                }
            }

            minRed * minGreen * minBlue
        }
    }
}

private data class Game(
    val id: Int,
    val draws: List<List<Pair<Cube, Int>>>,
) {
    companion object {

        fun from(input: String): Game {
            val colon = input.indexOf(':')
            val id = input.substring(5, colon).toInt()
            val draws = input.substring(colon + 2)

            val m = draws.split("; ").map { draw ->
                draw.split(", ").map {
                    val amount = it.substringBefore(' ').toInt()
                    val colour = it.substringAfter(' ')
                    val cube = when (colour) {
                        "red" -> Cube.Red
                        "green" -> Cube.Green
                        "blue" -> Cube.Blue
                        else -> error("Unexpected colour: $colour")
                    }
                    cube to amount
                }
            }

            return Game(id, m)
        }
    }
}

private enum class Cube {
    Red, Green, Blue
}