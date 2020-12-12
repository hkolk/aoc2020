package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertTrue


@DisplayName("Day 4")
class Day4Test {

    val testInput = Resources.resourceAsList("day4_test.txt")
    val testInputInvalid = Resources.resourceAsList("day4_test_invalid.txt")
    val testInputValid = Resources.resourceAsList("day4_test_valid.txt")


    val realInput = Resources.resourceAsList("day4.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Example`() {
            val answer = Day4(testInput).solvePart1()
            assertThat(answer).isEqualTo(2)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day4(realInput).solvePart1()
            assertThat(answer).isEqualTo(228)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Kotlin tests`() {
            assertTrue { "#aabbcc".matches(Regex("#[0-9a-z]{6}")) }
        }
        @Test
        fun `Matches Example`() {
            val answer = Day4(testInputValid).solvePart2()
            assertThat(answer).isEqualTo(4)
            val answer2 = Day4(testInputInvalid).solvePart2()
            assertThat(answer2).isEqualTo(0)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day4(realInput).solvePart2()
            assertThat(answer).isEqualTo(175)
        }
    }
}