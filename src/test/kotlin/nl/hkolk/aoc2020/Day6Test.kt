package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*


@DisplayName("Day 6")
class Day6Test {

    val testInput = Resources.resourceAsString("day6_test.txt", "\n")
    val realInput = Resources.resourceAsString("day6.txt", "\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Example`() {
            val answer = Day6(testInput).solvePart1()
            assertThat(answer).isEqualTo(11)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day6(realInput).solvePart1()
            assertThat(answer).isEqualTo(6809)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day6(testInput).solvePart2()
            assertThat(answer).isEqualTo(6)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day6(realInput).solvePart2()
            assertThat(answer).isEqualTo(3394)
        }
    }
}