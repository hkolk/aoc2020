package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
class Day18Test {

    val testInput = Resources.resourceAsList("day18_test.txt")

    val realInput = Resources.resourceAsList("day18.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day18(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(26457)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day18(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(14006719520523)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day18(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(694_173)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day18(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(545115449981968)
        }
    }
}