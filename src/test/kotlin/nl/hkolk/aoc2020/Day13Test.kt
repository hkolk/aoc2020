package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {

    val testInput = listOf("939", "7,13,x,x,59,x,31,19")
    val realInput = listOf("1000510", "19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,523,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17,13,x,x,x,x,x,x,x,x,x,x,29,x,853,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,23")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day13(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(295)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day13(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(259)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            Assertions.assertThat(Day13(listOf("1", "2,3,4,5,7")).solvePart2()).isEqualTo(122)
            val answer = Day13(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(1068781)
            Assertions.assertThat(Day13(listOf("1", "17,x,13,19")).solvePart2()).isEqualTo(3417)
            Assertions.assertThat(Day13(listOf("1", "67,7,59,61")).solvePart2()).isEqualTo(754018)
            Assertions.assertThat(Day13(listOf("1", "67,x,7,59,61")).solvePart2()).isEqualTo(779210)
            Assertions.assertThat(Day13(listOf("1", "67,7,x,59,61")).solvePart2()).isEqualTo(1261476)
            Assertions.assertThat(Day13(listOf("1", "1789,37,47,1889")).solvePart2()).isEqualTo(1202161486)

        }
        @Test
        fun `Actual Answer`() {
            val answer = Day13(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(210612924879242L)
        }
    }
}