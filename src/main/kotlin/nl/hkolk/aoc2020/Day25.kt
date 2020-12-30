package nl.hkolk.aoc2020

import java.lang.IllegalStateException

class Day25(val input: List<String>) {
    val cardPK = input[0].toLong()
    val doorPK = input[1].toLong()

    fun solvePart1(): Long {
        val subjectNumber = 7
        var value = 1L
        var cardLoop: Int? = null
        var doorLoop: Int? = null
        for(loop in 1..1_000_000_000) {
            value *= subjectNumber
            value %= 20201227
            //println("$loop: $value")
            if(value == cardPK) {
                cardLoop = loop
            }
            if(value == doorPK) {
                doorLoop = loop
            }
            if(cardLoop != null && doorLoop != null) {
                break
            }
        }
        if(cardLoop == null || doorLoop == null) {
            throw IllegalStateException("Could not find a match within the rounds: card: $cardLoop, door: $doorLoop")
        }
        value = 1
        for(i in 1..cardLoop) {
            value *= doorPK
            value %= 20201227
        }
        return value
    }
    fun solvePart2(): Long {
        TODO()
    }
}