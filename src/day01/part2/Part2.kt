package day01.part2

import java.lang.Exception
import kotlin.time.measureTimedValue

internal object Part2 {

    fun execute(input: List<String>) = measureTimedValue {
        input.sumOf {
            listOfNotNull(
                it.findFirstSpelledDigitOrNull(),
                it.findFirstDigitOrNull(),
                it.findLastSpelledDigitOrNull(),
                it.findLastDigitOrNull()
            ).run {
                ("${minBy { it.first }.second}" + "${maxBy { it.first }.second}").toInt()
            }
        }
    }
}

fun String.findFirstSpelledDigitOrNull(): Pair<Int, Int>? = findAnyOf(spelledDigits)?.let {
    it.first to it.second.asDigit()
}

fun String.findLastSpelledDigitOrNull(): Pair<Int, Int>? = findLastAnyOf(spelledDigits)?.let {
    it.first to it.second.asDigit()
}

fun String.findFirstDigitOrNull(): Pair<Int, Char>? = indexOfFirstDigitOrNull()?.let {
    it to this[it]
}

fun String.findLastDigitOrNull(): Pair<Int, Char>? = indexOfLastDigitOrNull()?.let {
    it to this[it]
}

fun String.indexOfFirstDigitOrNull(): Int? = indexOfFirst { it.isDigit() }.let {
    if (it == -1) null else it
}

fun String.indexOfLastDigitOrNull(): Int? = indexOfLast { it.isDigit() }.let {
    if (it == -1) null else it
}

fun String.asDigit() = when (this) {
    "one" -> 1
    "two" -> 2
    "three" -> 3
    "four" -> 4
    "five" -> 5
    "six" -> 6
    "seven" -> 7
    "eight" -> 8
    "nine" -> 9
    else -> throw Exception("provided string is not a spelled digit")
}

val spelledDigits = listOf(
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine"
)