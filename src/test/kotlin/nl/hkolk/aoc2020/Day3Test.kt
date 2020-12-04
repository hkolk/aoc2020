package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*


@DisplayName("Day 3")
class Day3Test {

    val testInput = Resources.resourceAsList("day3_test.txt")
    val realInput = Resources.resourceAsList("day3.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Example`() {
            val answer = Day3(testInput).solvePart1()
            assertThat(answer).isEqualTo(7)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day3(realInput).solvePart1()
            assertThat(answer).isEqualTo(164)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day3(testInput).solvePart2()
            assertThat(answer).isEqualTo(336)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day3(realInput).solvePart2()
            assertThat(answer).isEqualTo(5007658656)
        }
    }
}