package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 19")
class Day19Test {

    val testInput = Resources.resourceAsList("day19_test.txt")
    val testInputPart2 = Resources.resourceAsList("day19_test_part2.txt")

    val realInput = Resources.resourceAsList("day19.txt")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day19(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(2)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day19(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(111)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day19(testInputPart2).solvePart2()
            Assertions.assertThat(answer).isEqualTo(12)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day19(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(545115449981968)
        }
    }
}