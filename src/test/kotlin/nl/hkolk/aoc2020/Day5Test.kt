package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertTrue


@DisplayName("Day 5")
class Day5Test {

    val testInput = listOf("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")

    val realInput = Resources.resourceAsList("day5.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Example`() {
            assertThat(Day5(testInput).parseBoardingPass("FBFBBFFRLR")).isEqualTo(357)
            assertThat(Day5(testInput).parseBoardingPass("BFFFBBFRRR")).isEqualTo(567)
            assertThat(Day5(testInput).parseBoardingPass("FFFBBBFRRR")).isEqualTo(119)
            assertThat(Day5(testInput).parseBoardingPass("BBFFBBFRLL")).isEqualTo(820)
            assertThat(Day5(testInput).solvePart1()).isEqualTo(820)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day5(realInput).solvePart1()
            assertThat(answer).isEqualTo(904)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Actual Answer`() {
            val answer = Day5(realInput).solvePart2()
            assertThat(answer).isEqualTo(669)
        }
    }
}