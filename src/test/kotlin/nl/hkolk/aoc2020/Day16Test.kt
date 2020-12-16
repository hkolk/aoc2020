package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {

    val testInput = Resources.resourceAsList("day16_test.txt")
    val realInput = Resources.resourceAsList("day16.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day16(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(71)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day16(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(24021)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day16(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(286)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day16(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(38693)
        }
    }
}