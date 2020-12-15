package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException

class Day15(input: List<String>) {
    val input = input[0].split(",").map { it.toIntOrNull() ?: throw IllegalArgumentException("Could not map $it to int") }

    fun solve(stopAt: Int): Int {
        var last = -1
        val memory = input.mapIndexed { index, i ->  Pair(i, index+1)}.toMap().toMutableMap()
        for(step in input.size+1..stopAt) {
            val next = if(memory.get(last) != null) {
                (step - 1 - memory.get(last)!!)
            } else {
                0
            }
            memory[last] = step-1
            last = next
        }
        return last
    }
    fun solvePart1():Int = solve(2020)
    fun solvePart2():Int = solve(30000000)
}