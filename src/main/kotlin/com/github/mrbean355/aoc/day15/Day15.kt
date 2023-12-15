package com.github.mrbean355.aoc.day15

import com.github.mrbean355.aoc.base.Puzzle

private const val DELIMITER = ','
private const val REMOVE_COMMAND = '-'
private const val UPSERT_COMMAND = '='

object Day15 : Puzzle<String> {

    override fun part1(input: String): Any {
        return input.split(DELIMITER).sumOf(String::hash)
    }

    override fun part2(input: String): Any {
        val commands = parseCommands(input)
        val boxes = mutableMapOf<Int, MutableList<Lens>>()

        commands.forEach { command ->
            when (command) {
                is Command.Remove -> {
                    boxes[command.label.hash()]?.removeAll { it.label == command.label }
                }

                is Command.Upsert -> {
                    val box = boxes.getOrPut(command.label.hash(), ::mutableListOf)
                    val current = box.indexOfFirst { it.label == command.label }
                    val newLens = Lens(command.label, command.focalLength)

                    if (current != -1) {
                        box[current] = newLens
                    } else {
                        box.add(newLens)
                    }
                }
            }
        }

        return boxes.keys.fold(0) { totalAcc, box ->
            totalAcc + boxes.getValue(box).foldIndexed(0) { index, acc, lens ->
                acc + (box + 1) * (index + 1) * lens.focalLength
            }
        }
    }

    override fun mapInput(input: List<String>): String {
        return input.single()
    }

    private fun parseCommands(input: String): List<Command> {
        return input.split(DELIMITER).map { instruction ->
            if (REMOVE_COMMAND in instruction) {
                Command.Remove(
                    label = instruction.substringBefore(REMOVE_COMMAND)
                )
            } else {
                Command.Upsert(
                    label = instruction.substringBefore(UPSERT_COMMAND),
                    focalLength = instruction.substringAfter(UPSERT_COMMAND).toInt(),
                )
            }
        }
    }
}