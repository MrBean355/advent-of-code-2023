package com.github.mrbean355.aoc.day1

fun String.getFirstNumber(): Char {
    val buffer = StringBuilder()
    forEach { ch ->
        if (ch.isDigit()) {
            return ch
        }
        buffer.append(ch)
        buffer.findNumber()?.let { return it }
    }
    error("Unable to find a number in $this")
}

fun String.getLastNumber(): Char {
    val buffer = StringBuilder()
    reversed().forEach { ch ->
        if (ch.isDigit()) {
            return ch
        }
        buffer.insert(0, ch)
        buffer.findNumber()?.let { return it }
    }
    error("Unable to find a number in $this")
}

private fun CharSequence.findNumber(): Char? {
    return when {
        "one" in this -> '1'
        "two" in this -> '2'
        "three" in this -> '3'
        "four" in this -> '4'
        "five" in this -> '5'
        "six" in this -> '6'
        "seven" in this -> '7'
        "eight" in this -> '8'
        "nine" in this -> '9'
        else -> null
    }
}