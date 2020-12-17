package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {

    val testInput = Resources.resourceAsList("day17_test.txt")

    val realInput = Resources.resourceAsList("day17.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day17(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(112)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day17(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(315)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day17(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(1716)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day17(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(1289178686687)
        }
    }
}