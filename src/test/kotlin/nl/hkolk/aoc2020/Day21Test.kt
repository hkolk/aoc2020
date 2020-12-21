package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {

    val testInput = Resources.resourceAsList("day21_test.txt")
    val realInput = Resources.resourceAsList("day21.txt")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day21(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(5)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day21(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(2203)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day21(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo("mxmxvkd,sqjhc,fvjkl")
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day21(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo("fqfm,kxjttzg,ldm,mnzbc,zjmdst,ndvrq,fkjmz,kjkrm")
        }
    }
}