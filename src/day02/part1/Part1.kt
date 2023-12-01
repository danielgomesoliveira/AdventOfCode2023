package day02.part1

import kotlin.time.measureTimedValue

object Part1 {

    fun executeA(input: List<String>) = measureTimedValue {

        val result = input.map {
            it.takeLastWhile { it != ':' }.toReveal()
        }.withIndex().sumOf {
            if (it.value.meetsQuantity()) it.index + 1
            else 0
        }

        result
    }

    fun executeB(input: List<String>) = measureTimedValue {
        var result = 0

        val redRegex = """\b\d+\s+red\b""".toRegex()
        val greenRegex = """\b\d+\s+green\b""".toRegex()
        val blueRegex = """\b\d+\s+blue\b""".toRegex()

        input.forEachIndexed { index, line ->
            val maxRed = redRegex.findAll(line).map { it.value.getDigitsAsInt() }.max()
            val maxGreen = greenRegex.findAll(line).map { it.value.getDigitsAsInt() }.max()
            val maxBlue = blueRegex.findAll(line).map { it.value.getDigitsAsInt() }.max()

            if (meetsQuantity(maxRed, maxGreen, maxBlue)) {
                result += index + 1
            }

        }

        result
    }

}

fun String.getDigitsAsInt() = filter { it.isDigit() }.toInt()

fun meetsQuantity(red: Int, green: Int, blue: Int) = red <= 12 && green <= 13 && blue <= 14

fun Map<String, Int>.meetsQuantity() =
    getOrDefault("red", 0) <= 12 &&
            getOrDefault("green", 0) <= 13 &&
            getOrDefault("blue", 0) <= 14

fun String.toReveal(): Map<String, Int> =
    filter { it != ',' && it != ';' }.split(" ").filter { it.isNotBlank() }.chunked(2) {
        it[1] to it[0].toInt()
    }.groupBy { it.first }.mapValues { it.value.maxOf { it.second } }

// "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
// only 12 red cubes, 13 green cubes, and 14 blue cubes