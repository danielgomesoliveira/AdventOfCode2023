
import day03.part1.Part1
import day03.part2.Part2
import kotlin.time.TimedValue

fun main() {
    fun part1(input: List<String>): TimedValue<Int> {
        return Part1.execute(input)
    }

    fun part2(input: List<String>): TimedValue<Long> {
        return Part2.execute(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput(name = "day03/part1/Part1_test")
    check(part1(testInputPart1).value == 4361)
    val testInputPart2 = readInput(name = "day03/part1/Part1_test")
    check(part2(testInputPart2).value == 467835L)

    val input = readInput("day03/Day03_input")
    part1(input).println()
    part2(input).println()
}
