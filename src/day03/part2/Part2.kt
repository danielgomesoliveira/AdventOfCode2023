package day03.part2

import day03.part1.hasSymbolInSurroundings
import day03.part1.putRegexMatchesWithCoordinates
import kotlin.time.measureTimedValue

object Part2 {

    fun execute(input: List<String>) = measureTimedValue {
        val coordinatesToDigitsMap = hashMapOf<Pair<Int, Int>, String>()
        val coordinatesOfStarToAdjacentDigitsMap = hashMapOf<Pair<Int, Int>, List<String>>()

        val digitPattern = "\\d+".toRegex()
        val symbolPattern = "\\*".toRegex()

        input.forEachIndexed { index, line ->
            val digitsMatched = digitPattern.findAll(line)
            val symbolsMatched = symbolPattern.findAll(line)

            coordinatesToDigitsMap.putRegexMatchesWithCoordinates(digitsMatched, index)
            coordinatesOfStarToAdjacentDigitsMap.putRegexMatchesWithCoordinates(symbolsMatched, index)

        }

        coordinatesToDigitsMap
            .forEach {
                it.addMatchToSymbolsMap(coordinatesOfStarToAdjacentDigitsMap)
            }

        coordinatesOfStarToAdjacentDigitsMap
            .filter { it.value.size == 2 }
            .values
            .sumOf { it[0].toLong() * it[1].toLong() }

    }
}

fun Map.Entry<Pair<Int, Int>, String>.addMatchToSymbolsMap(symbolsCoordinates: MutableMap<Pair<Int, Int>, List<String>>) {

    val content = value
    val coordinates = key
    val startX = (coordinates.first - 1)
    val startY = (coordinates.second - 1)
    val endX = (coordinates.first + content.length)
    val endY = (coordinates.second + 1)

    for (x in startX..endX) {
        for (y in startY..endY) {
            symbolsCoordinates[x to y]?.also {
                symbolsCoordinates[x to y] = it + listOf(content)
                return
            }
        }
    }
}

fun HashMap<Pair<Int, Int>, List<String>>.putRegexMatchesWithCoordinates(matches: Sequence<MatchResult>, y: Int) {
    for (match in matches) {
        put(match.range.first to y, emptyList())
    }
}