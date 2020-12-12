package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    val testInput = Resources.resourceAsList("day12_test.txt")
    val realInput = Resources.resourceAsList("day12.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day12(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(25)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day12(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(2280)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day12(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(26)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day12(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(2085)
        }
    }
}