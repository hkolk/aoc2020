package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 24")
class Day24Test {

    val testInput = Resources.resourceAsList("day24_test.txt")
    val realInput = Resources.resourceAsList("day24.txt")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Coord test`() {
            Assertions.assertThat(Day24.Point3D.parseTile("nwwswee")).isEqualTo(Day24.Point3D(0, 0, 0))
        }
        @Test
        fun `Matches Example`() {
            val answer = Day24(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(10)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day24(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(434)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day24(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(2208)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day24(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(3955)
        }
    }
}