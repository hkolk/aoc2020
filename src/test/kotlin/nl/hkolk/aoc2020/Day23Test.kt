package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {

    val testInput = listOf("389125467")
    val realInput = listOf("398254716")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day23(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo("67384529")
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day23(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo("45798623")
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day23(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(291)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day23(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(32338)
        }
    }
}