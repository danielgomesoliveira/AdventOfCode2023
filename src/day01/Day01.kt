import day01.part1.Part1
import day01.part2.Part2
import kotlin.time.TimedValue

fun main() {
    fun part1(input: List<String>): TimedValue<Int> {
        return Part1.executeA(input)
    }

    fun part2(input: List<String>): TimedValue<Int> {
        return Part2.execute(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInputPart1 = readInput(name = "day01/part1/Part1_test")
    check(part1(testInputPart1).value == 142)
    val testInputPart2 = readInput(name = "day01/part2/Part2_test")
    check(part2(testInputPart2).value == 281)

    val input = readInput("day01/Day01_input")
    part1(input).println()
    part2(input).println()
}
