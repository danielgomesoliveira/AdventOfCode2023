
import day02.part1.Part1
import day02.part2.Part2
import kotlin.time.TimedValue

fun main() {
    fun part1(input: List<String>): TimedValue<Int> {
        return Part1.executeB(input)
    }

    fun part2(input: List<String>): TimedValue<Int> {
        return Part2.executeB(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput(name = "day02/part1/Part1_test")
    check(part1(testInputPart1).value == 8)
    val testInputPart2 = readInput(name = "day02/part1/Part1_test")
    check(part2(testInputPart2).value == 2286)

    val input = readInput("day02/Day02_input")
    part1(input).println()
    part2(input).println()
}
