package com.github.mrbean355.aoc.base

class Grid(
    val width: Int,
    val height: Int,
    data: List<Char>,
) : Iterable<Char> {

    private val data = data.toMutableList()

    init {
        require(width * height == data.size) {
            "Data size mismatch"
        }
    }

    operator fun set(column: Int, row: Int, value: Char) {
        require(column in 0..<width) {
            "Column index out of bounds: $column"
        }
        require(row in 0..<height) {
            "Row index out of bounds: $row"
        }
        data[column + row * width] = value
    }

    fun getRow(index: Int): List<Char> {
        require(index in 0..<height) {
            "Row index out of bounds: $index"
        }
        val start = index * width
        return data.subList(start, start + width)
    }

    fun getColumn(index: Int): List<Char> {
        require(index in 0..<width) {
            "Column index out of bounds: $index"
        }
        return buildList {
            repeat(height) {
                add(data[index + it * width])
            }
        }
    }

    fun getAllRows(): List<List<Char>> {
        return data.chunked(width)
    }

    fun getAllColumns(): List<List<Char>> {
        return (0..<width).map(::getColumn)
    }

    override fun iterator(): Iterator<Char> {
        return object : Iterator<Char> {
            private var index = 0

            override fun hasNext(): Boolean {
                return index < data.size
            }

            override fun next(): Char {
                return data[index++]
            }
        }
    }

    override fun toString(): String {
        return data.joinToString(separator = "")
            .chunked(width)
            .joinToString(separator = "\n")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        return data == other.data
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}