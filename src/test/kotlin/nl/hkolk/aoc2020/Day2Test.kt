package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*


@DisplayName("Day 2")
class Day2Test {

    val testInput = listOf("1-3 a: abcde\n" ,
            "1-3 b: cdefg\n" ,
            "2-9 c: ccccccccc")
    val realInput = Resources.resourceAsList("day2.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Example`() {
            val answer = Day2(testInput).solvePart1()
            assertThat(answer).isEqualTo(2)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day2(realInput).solvePart1()
            assertThat(answer).isEqualTo(603)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day2(testInput).solvePart2()
            assertThat(answer).isEqualTo(1)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day2(realInput).solvePart2()
            assertThat(answer).isEqualTo(404)
        }
    }
}