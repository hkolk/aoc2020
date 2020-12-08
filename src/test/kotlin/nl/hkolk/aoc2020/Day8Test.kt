package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day *")
class Day8Test {

    val testInput = Resources.resourceAsList("day8_test.txt")
    val realInput = Resources.resourceAsList("day8.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day8(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(5)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day8(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(1654)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day8(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(8)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day8(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(833)
        }
    }
}