package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.Puzzle

object Day2 : Puzzle<List<Game>> {

    override fun part1(input: List<Game>): Any {
        val maxCubes = mapOf(
            Cube.Red to 12,
            Cube.Green to 13,
            Cube.Blue to 14,
        )

        return input.filter { game ->
            game.draws.all { draw ->
                draw.all {
                    it.amount <= maxCubes.getValue(it.cube)
                }
            }
        }.sumOf(Game::id)
    }

    override fun part2(input: List<Game>): Any {
        return input.sumOf { game ->
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

    override fun mapInput(input: List<String>): List<Game> {
        return input.map { line ->
            val colon = line.indexOf(':')
            val id = line.substring(5, colon).toInt()
            val draws = line.substring(colon + 2)
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

            Game(id, cubeDraws)
        }
    }
}