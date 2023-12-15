package com.github.mrbean355.aoc.day15

fun String.hash(): Int {
    return fold(0) { acc, ch ->
        (acc + ch.code) * 17 % 256
    }
}

sealed interface Command {

    val label: String

    data class Remove(
        override val label: String,
    ) : Command

    data class Upsert(
        override val label: String,
        val focalLength: Int,
    ) : Command
}

data class Lens(
    val label: String,
    val focalLength: Int,
)