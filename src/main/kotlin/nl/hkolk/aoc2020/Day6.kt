package nl.hkolk.aoc2020

class Day6(private val input: String) {
    fun solvePart1(): Int = input.split("\n\n").map { it.filter { it in 'a'..'z' }.groupBy { it }.size }.sum()
    fun solvePart2(): Int = input.split("\n\n").map { group -> group.filter { it in 'a'..'z' }.groupBy { it }.filter { it.value.size == group.filter{ it == '\n'}.count() + 1 }.size }.sum()
}