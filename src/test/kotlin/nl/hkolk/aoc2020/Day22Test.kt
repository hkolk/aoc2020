package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    val testInput = Resources.resourceAsList("day22_test.txt")
    val realInput = Resources.resourceAsList("day22.txt")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day22(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(306)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day22(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(30780)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day22(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(291)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day22(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(32338)
        }
    }
}