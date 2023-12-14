package com.github.mrbean355.aoc.base

fun List<String>.flatten(): List<Char> {
    return flatMap { it.toCharArray().toList() }
}