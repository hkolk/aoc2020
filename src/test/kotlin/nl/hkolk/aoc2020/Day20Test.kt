package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {

    val testInput = Resources.resourceAsList("day20_test.txt")
    val realInput = Resources.resourceAsList("day20.txt")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {


        @Test
        fun `rotate test`() {
            val input = listOf("13", "46", "79")
            Assertions.assertThat(input.rotate()).isEqualTo(listOf("741", "852", "963"))
        }
        @Test
        fun `Matches Example`() {
            val answer = Day20(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(20899048083289)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day20(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(17032646100079)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day20(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(273)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day20(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(2006)
        }
    }
}