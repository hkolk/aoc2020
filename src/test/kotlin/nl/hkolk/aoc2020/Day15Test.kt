package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    val testInput = listOf("0,3,6")
    val realInput = listOf("2,0,1,9,5,19")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {

            val answer = Day15(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(436)

            Assertions.assertThat(Day15(listOf("1,3,2")).solvePart1()).isEqualTo(1)
            Assertions.assertThat(Day15(listOf("2,1,3")).solvePart1()).isEqualTo(10)

        }
        @Test
        fun `Actual Answer`() {
            val answer = Day15(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(1009)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day15(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(175594)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day15(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(62714)
        }
    }
}