package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    val testInput = Resources.resourceAsList("day14_test.txt")
    val testInputPart2 = Resources.resourceAsList("day14_test_part2.txt")
    val realInput = Resources.resourceAsList("day14.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            Assertions.assertThat(Day14(listOf()).applyBitmask("XXXXXX10", 9)).isEqualTo(10)
            Assertions.assertThat(Day14(listOf()).applyBitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", 11)).isEqualTo(73)
            Assertions.assertThat(Day14(listOf()).applyBitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", 101)).isEqualTo(101)
            Assertions.assertThat(Day14(listOf()).applyBitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", 0)).isEqualTo(64)

            val answer = Day14(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(165)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day14(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(7611244640053)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            Assertions.assertThat(Day14(listOf()).allMutations("00000000000000000000000000000000X0XX").size).isEqualTo(8)
            val answer = Day14(testInputPart2).solvePart2()
            Assertions.assertThat(answer).isEqualTo(208)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day14(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(3705162613854)
        }
    }
}