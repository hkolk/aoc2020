package nl.hkolk.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 25")
class Day25Test {

    val testInput = listOf("5764801", "17807724")
    val realInput = listOf("14788856", "19316454")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches Example`() {
            val answer = Day25(testInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(14897079)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day25(realInput).solvePart1()
            Assertions.assertThat(answer).isEqualTo(545789)
        }
    }
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Example`() {
            val answer = Day25(testInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(291)
        }
        @Test
        fun `Actual Answer`() {
            val answer = Day25(realInput).solvePart2()
            Assertions.assertThat(answer).isEqualTo(32338)
        }
    }
}