package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    val testInput = Resources.resourceAsList("day11_test.txt")
    val realInput = Resources.resourceAsList("day11.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day11(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(37)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day11(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(2281)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day11(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(19208)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day11(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(28346956187648)
        }
    }
}