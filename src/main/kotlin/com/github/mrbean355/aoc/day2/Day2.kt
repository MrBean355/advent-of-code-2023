package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.Puzzle

class Day2(
    private val input: List<String>,
) : Puzzle {

    override fun part1(): Int {
        val maxCubes = mapOf(
            Cube.Red to 12,
            Cube.Green to 13,
            Cube.Blue to 14,
        )

        return input.map(Game::from).filter { game ->
            game.draws.all { draw ->
                draw.all {
                    it.amount <= maxCubes.getValue(it.cube)
                }
            }
        }.sumOf(Game::id)
    }

    override fun part2(): Any {
        return input.map(Game::from).sumOf { game ->
            val minCubes = mutableMapOf(
                Cube.Red to 0,
                Cube.Green to 0,
                Cube.Blue to 0,
            )

            game.draws.forEach { draw ->
                draw.forEach {
                    minCubes[it.cube] = maxOf(minCubes.getValue(it.cube), it.amount)
                }
            }

            minCubes.values.reduce { acc, value ->
                acc * value
            }
        }
    }
}

private data class Game(
    val id: Int,
    val draws: List<List<CubeSelection>>,
) {
    companion object {

        fun from(input: String): Game {
            val colon = input.indexOf(':')
            val id = input.substring(5, colon).toInt()
            val draws = input.substring(colon + 2)
            val cubeDraws = draws.split("; ").map { draw ->
                draw.split(", ").map {
                    val space = it.indexOf(' ')
                    val amount = it.substring(0, space).toInt()
                    val cube = when (val colour = it.substring(space + 1)) {
                        "red" -> Cube.Red
                        "green" -> Cube.Green
                        "blue" -> Cube.Blue
                        else -> error("Unexpected colour: $colour")
                    }
                    CubeSelection(cube, amount)
                }
            }

            return Game(id, cubeDraws)
        }
    }
}

private enum class Cube {
    Red, Green, Blue
}

private data class CubeSelection(
    val cube: Cube,
    val amount: Int,
)