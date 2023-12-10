package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.Puzzle

object Day10 : Puzzle<List<String>> {

    override fun part1(input: List<String>): Any {
        val maze = Maze(input.flatMap { it.toCharArray().toList() })
        val dist = mutableMapOf<Int, Int>()
        val queue = mutableListOf<Int>()
        val source = maze.start

        maze.forEachIndexed { index, _ ->
            if (!maze.isEmpty(index)) {
                dist[index] = Int.MAX_VALUE
                queue += index
            }
        }

        dist[source] = 0

        while (queue.isNotEmpty()) {
            val u = queue.minBy { dist.getValue(it) }
            queue.remove(u)

            val neighbours = maze.getNeighbours(u)
                .filter { it in queue }
                .filter { maze.canConnect(u, it) }

            neighbours.forEach { v ->
                val alt = dist.getValue(u) + 1
                if (alt < dist.getValue(v)) {
                    dist[v] = alt
                }
            }
        }

        return dist.values
            .filter { it != Int.MAX_VALUE }
            .max()
    }

    override fun part2(input: List<String>): Any {
        return 0
    }

    override fun mapInput(input: List<String>): List<String> {
        return input
    }
}
