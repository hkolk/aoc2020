package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    val testInput = Resources.resourceAsList("day10_test.txt")
    val realInput = Resources.resourceAsList("day10.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day10(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(220)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day10(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(2040)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day10(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(19208)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day10(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(28346956187648)
        }
    }
}