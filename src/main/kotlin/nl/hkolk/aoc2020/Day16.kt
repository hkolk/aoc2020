package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException

class Day16(input: List<String>) {
    fun List<String>.splitBy(func: (String) -> Boolean): List<List<String>> {
        val ret = mutableListOf<List<String>>()
        var collect = mutableListOf<String>()
        for(line in this) {
            if(func(line)) {
                ret.add(collect)
                collect = mutableListOf()
            } else {
                collect.add(line)
            }
        }
        if(collect.isNotEmpty()) ret.add(collect)
        return ret
    }

    data class Validation(val name: String, val ranges: List<IntRange>) {
        fun match(value: Int): Boolean {
            for(range in ranges) {
                if(value in range) {
                    return true
                }
            }
            return false
        }

        fun validate(ticket: List<Int>): Int {
            var errors = 0
            for(value in ticket) {
                if(!match(value)) {
                    errors += value
                }
            }
            return errors
        }

        companion object {
            fun fromLine(line: String): Validation {
                val subParts = line.split(":", "-", " or ")
                val name = subParts[0]
                val ranges = subParts.drop(1).map {
                    it.trim().toIntOrNull() ?: throw IllegalArgumentException("Bad value: '$it' in '$line'")
                }.chunked(2).map{IntRange(it.first(), it.last())}
                return Validation(name, ranges)
            }
        }
    }

    val validations: List<Validation>
    val myTicket: List<Int>
    val tickets: List<List<Int>>
    val validTickets: List<List<Int>>
    init {
        val parts = input.splitBy( {it.isNullOrEmpty()} )
        validations = parts[0].map { Validation.fromLine(it) }

        myTicket = parts[1].last().split(",").map { it.toIntOrNull() ?: throw IllegalArgumentException("$it is not an integer") }

        tickets = parts[2].drop(1).map { line ->
            line.split(",").map { it.toIntOrNull() ?: throw IllegalArgumentException("$it is not an integer") }
        }
        validTickets = tickets.filter { ticket ->
            validations.map { validation -> validation.validate(ticket) }.sum() == 0
        }
    }

    fun solvePart1(): Int {
        println(tickets)
        println(validations)
        var ret = 0
        for(ticket in tickets) {
            for(value in ticket) {
                if(validations.map { it.match(value) }.filter { it }.isEmpty()) {
                    ret += value
                    println("Invalid: $value")
                }
            }
        }
        return ret
    }
    fun solvePart2(): Int {
        TODO()
    }
}