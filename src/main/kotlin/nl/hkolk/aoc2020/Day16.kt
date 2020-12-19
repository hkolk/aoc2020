package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException

class Day16(input: List<String>) {


    data class Validation(val name: String, val ranges: List<IntRange>) {
        fun match(value: Int): Boolean {
            for(range in ranges) {
                if(value in range) {
                    return true
                }
            }
            return false
        }
        fun match(value: List<Int>): Boolean = value.none { !match(it) }

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
    fun List<Validation>.validate(ticket: List<Int>): Pair<Boolean, Int> {
        var errors = 0
        var valid = true
        for (value in ticket) {
            if(none { it.match(value) }) {
                errors += value
                valid = false
            }
        }
        return Pair(valid, errors)
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
            validations.validate(ticket).first
        }
    }

    fun solvePart1(): Int = tickets.map{ validations.validate(it).second }.sum()

    fun List<List<Int>>.print() {
        for(line in this) {
            for(item in line) {
                print(item.toString().padStart(5))
            }
            println()
        }
    }
    fun solvePart2(startsWith: String = ""): Long {
        val fields: List<MutableList<Int>> = (validTickets[1].indices).map { mutableListOf() }
        validTickets.forEach { list ->
            list.forEachIndexed { x, value ->
                fields[x].add(value)
            }
        }
        val indices = mutableMapOf<String, Int>()

        val potentialIndices = fields.mapIndexed { index, field ->
            Pair(index, validations.mapNotNull { if(it.match(field)) { it.name } else { null }})
        }.toMap()

        for(i in 1 .. validations.size) {
            potentialIndices.filter { it.value.size == i }.forEach { (key, value) ->
                value.forEach {
                    if(indices[it] == null && !indices.containsValue(key)) {
                        indices[it] = key
                    }
                }
            }
        }

        return indices.keys.filter { it.startsWith(startsWith) }.map {
            myTicket[indices[it]!!]
        }.fold(1L) {acc, it -> acc * it}
    }
}