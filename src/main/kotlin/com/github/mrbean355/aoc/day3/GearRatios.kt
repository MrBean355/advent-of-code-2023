package com.github.mrbean355.aoc.day3

data class PartNumber(
    val index: Int,
    val length: Int,
    val value: Int,
) {

    val indices: IntRange = index..<index + length

}

data class Symbol(
    val index: Int,
    val value: Char,
)
