package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


@DisplayName("Day 7")
class Day7Test {

    val testInput = Resources.resourceAsList("day7_test.txt")
    val realInput = Resources.resourceAsList("day7.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `String Repeat`() {
            assertThat("Word".repeat(3)).isEqualTo("WordWordWord")
        }
        @Test
        fun `Matches Example`() {
            val answer = Day7(testInput).solvePart1()
            assertThat(answer).isEqualTo(4)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day7(realInput).solvePart1()
            assertThat(answer).isEqualTo(161)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day7(testInput).solvePart2()
            assertThat(answer).isEqualTo(32)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day7(realInput).solvePart2()
            assertThat(answer).isEqualTo(30899)
        }
    }
}