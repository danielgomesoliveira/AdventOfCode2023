package day01.part1

import kotlin.time.measureTimedValue

object Part1 {

    fun part1A(input: List<String>) = measureTimedValue {
        input.sumOf {
            "${it.first { it.isDigit() }}${it.last { it.isDigit() }}".toInt()
        }
    }


    fun part1B(input: List<String>) = measureTimedValue {
        var result = 0
        input.forEach {
            val value = it.getDigits()
            result += value
        }
        result
    }
}

fun String.getDigits(): Int {
    var start = 0
    var end = this.length - 1
    while (true) {
        val startIsDigit = this[start].isDigit().also { if (!it) start++ }
        val endIsDigit = this[end].isDigit().also { if (!it) end-- }
        if (startIsDigit && endIsDigit) break
    }
    return "${this[start]}${this[end]}".toInt()
}