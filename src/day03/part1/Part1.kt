package day03.part1

import kotlin.time.measureTimedValue

object Part1 {

    fun execute(input: List<String>) = measureTimedValue {

        val coordinatesToDigitsMap = hashMapOf<Pair<Int, Int>, String>()
        val coordinatesToSymbolsMap = hashMapOf<Pair<Int, Int>, String>()

        val digitPattern = "\\d+".toRegex()
        val symbolPattern = "[^\\w\\s.]".toRegex()

        input.forEachIndexed { index, line ->
            val digitsMatched = digitPattern.findAll(line)
            val symbolsMatched = symbolPattern.findAll(line)

            coordinatesToDigitsMap.putRegexMatchesWithCoordinates(digitsMatched, index)
            coordinatesToSymbolsMap.putRegexMatchesWithCoordinates(symbolsMatched, index)
        }

        coordinatesToDigitsMap
            .filter { it.hasSymbolInSurroundings(coordinatesToSymbolsMap.keys) }
            .values
            .sumOf { it.toInt() }
    }
}

fun Map.Entry<Pair<Int, Int>, String>.hasSymbolInSurroundings(symbolsCoordinates: Collection<Pair<Int, Int>>): Boolean {

    val content = value
    val coordinates = key
    val startX = (coordinates.first - 1)
    val startY = (coordinates.second - 1)
    val endX = (coordinates.first + content.length)
    val endY = (coordinates.second + 1)

    for (x in startX..endX) {
        for (y in startY..endY) {
            if (symbolsCoordinates.contains(x to y)) return true
        }
    }

    return false
}

fun HashMap<Pair<Int, Int>, String>.putRegexMatchesWithCoordinates(matches: Sequence<MatchResult>, y: Int) {
    for (match in matches) {
        put(match.range.first to y, match.value)
    }
}