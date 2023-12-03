package day02.part2

import day02.part1.getDigitsAsInt
import day02.part1.meetsQuantity
import day02.part1.toReveal
import kotlin.time.measureTimedValue

object Part2 {

    fun executeA(input: List<String>) = measureTimedValue {

        val result = input.map {
            it.toReveal()
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

        input.forEach { line ->
            val maxRed = redRegex.findAll(line).map { it.value.getDigitsAsInt() }.max()
            val maxGreen = greenRegex.findAll(line).map { it.value.getDigitsAsInt() }.max()
            val maxBlue = blueRegex.findAll(line).map { it.value.getDigitsAsInt() }.max()
            result += (maxRed * maxGreen * maxBlue)
        }

        result
    }

}

// "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
// only 12 red cubes, 13 green cubes, and 14 blue cubes