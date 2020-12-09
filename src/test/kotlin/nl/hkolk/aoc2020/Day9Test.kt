package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day9Test {

    val testInput = Resources.resourceAsList("day9_test.txt")
    val realInput = Resources.resourceAsList("day9.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day9(testInput, 5).solvePart1()
            Assertions.assertThat(answer).isEqualTo(127)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day9(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(15353384)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day9(testInput, 5).solvePart2()
            Assertions.assertThat(answer).isEqualTo(62)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day9(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(833)
        }
    }
}