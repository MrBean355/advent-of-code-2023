package com.github.mrbean355.aoc.day2

data class Game(
    val id: Int,
    val draws: List<List<CubeSelection>>,
)

enum class Cube {
    Red, Green, Blue
}

data class CubeSelection(
    val cube: Cube,
    val amount: Int,
)