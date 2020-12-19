package nl.hkolk.aoc2020

class Day19(val input: List<String>) {

    sealed class Rule() {
        abstract val name: String
        abstract fun match(input: String) : Pair<Boolean, String>
        data class StringRule(override val name: String, val charToMatch: Char) : Rule() {
            override fun match(input: String): Pair<Boolean, String> {
                if(input.length > 0 && input[0] == charToMatch) {
                    return Pair(true, input.drop(1))
                } else {
                    return Pair(false, input.drop(1))
                }
            }
        }
        data class SequenceRule(override val name: String, val sequence: List<String>) : Rule() {
            override fun match(input: String): Pair<Boolean, String> {
                var remainder = input
                for(step in 0 until sequence.size) {
                    val result = ALLRULES[sequence[step]]!!.match(remainder)
                    if(!result.first) {
                        return Pair(false, result.second)
                    } else {
                        remainder = result.second
                    }
                }
                return Pair(true, remainder)
            }
        }
        data class OrRule(override val name: String, val left: String, val right: String) : Rule() {
            override fun match(input: String): Pair<Boolean, String> {
                val res = ALLRULES[left]!!.match(input)
                return if(res.first) {
                    res
                } else {
                    ALLRULES[right]!!.match(input)
                }
            }
        }

        companion object {
            fun fromString(input: String) : List<Rule> {
                val parts = input.split(",", " ", ":").filter { it.isNotEmpty() }
                return if(parts[1].getOrNull(0) == '"') {
                    listOf(StringRule(parts[0], parts[1].get(1)))
                } else if(parts.contains("|")) {
                    // piped = true
                    val left = SequenceRule(parts[0]+"a", parts.subList(1,parts.indexOf("|")))
                    val right = SequenceRule(parts[0]+"b", parts.subList(parts.indexOf("|")+1, parts.size))
                    val or = OrRule(parts[0], left.name, right.name)
                    listOf(left, right, or)
                } else {
                    listOf(SequenceRule(parts[0], parts.subList(1, parts.size)))
                }
            }
            var ALLRULES: Map<String, Rule> = mapOf()
        }
    }


    fun solvePart1(): Int {
        val parts = input.splitBy { it.isNullOrEmpty() }
        Rule.ALLRULES= parts[0].flatMap { Rule.fromString(it) }.map { Pair(it.name, it) }.toMap()
        return parts[1].map { Rule.ALLRULES["0"]!!.match(it) }.count{ it.first && it.second.isEmpty() }
    }
    fun solvePart2(): Int {
        val updatedInput = input.map {
            if(it == "8: 42") {
                "8: 42 | 42 8"
            } else if(it == "11: 42 31") {
                "11: 42 31 | 42 11 31"
            } else {
                it
            }
        }
        val parts = updatedInput.splitBy { it.isNullOrEmpty() }
        Rule.ALLRULES= parts[0].flatMap { Rule.fromString(it) }.map { Pair(it.name, it) }.toMap()
        for(message in parts[1]) {
            println("$message == ${Rule.ALLRULES["0"]!!.match(message)}")
        }
        return parts[1].map { Rule.ALLRULES["0"]!!.match(it) }.count{ it.first && it.second.isEmpty() }
    }
}