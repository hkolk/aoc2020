package nl.hkolk.aoc2020

class Day19(val input: List<String>) {

    sealed class Rule {
        abstract val name: String
        abstract fun compile(): String
        data class StringRule(override val name: String, val charToMatch: Char) : Rule() {
            override fun compile(): String {
                return charToMatch.toString()
            }
        }
        data class SequenceRule(override val name: String, val sequence: List<String>) : Rule() {
            override fun compile(): String {
                return sequence.joinToString("") { ALLRULES[it]!!.compile() }
            }
        }
        data class OrRule(override val name: String, val left: String, val right: String) : Rule() {
            override fun compile(): String = compile(0)
            private fun compile(depth:Int): String {
                return when {
                    depth > 10 -> {
                        "(?:(?:" + ALLRULES["42"]!!.compile() + ")(?:" + ALLRULES["31"]!!.compile() + "))?"
                    }
                    name=="8" -> {
                        "(?:" + ALLRULES["42"]!!.compile() +  "+)"
                    }
                    name=="11" -> {
                        "(?:(?:" + ALLRULES["42"]!!.compile() + ")(?:" + this.compile(depth+1) + ")?(?:" + ALLRULES["31"]!!.compile() + "))"
                    }
                    else -> {
                        "(?:(?:" + ALLRULES[left]!!.compile() + ")|(?:" + ALLRULES[right]!!.compile() + "))"
                    }
                }
            }
        }

        companion object {
            fun fromString(input: String) : List<Rule> {
                val parts = input.split(",", " ", ":").filter { it.isNotEmpty() }
                return when {
                    parts[1].getOrNull(0) == '"' -> {
                        listOf(StringRule(parts[0], parts[1][1]))
                    }
                    parts.contains("|") -> {
                        // piped = true
                        val left = SequenceRule(parts[0]+"a", parts.subList(1,parts.indexOf("|")))
                        val right = SequenceRule(parts[0]+"b", parts.subList(parts.indexOf("|")+1, parts.size))
                        val or = OrRule(parts[0], left.name, right.name)
                        listOf(left, right, or)
                    }
                    else -> {
                        listOf(SequenceRule(parts[0], parts.subList(1, parts.size)))
                    }
                }
            }
            var ALLRULES: Map<String, Rule> = mapOf()
        }
    }

    private fun solve(input: List<String>): Int {
        val parts = input.splitBy { it.isEmpty() }
        Rule.ALLRULES = parts[0].flatMap { Rule.fromString(it) }.map { Pair(it.name, it) }.toMap()

        val regex = Regex("^"+Rule.ALLRULES["0"]!!.compile()+"$")
        return parts[1].filter { regex.matches(it) }.count()
    }
    
    fun solvePart1(): Int = solve(input)
    fun solvePart2(): Int {
        val updatedInput = input.map {
            when (it) {
                "8: 42" -> "8: 42 | 42 8"
                "11: 42 31" -> "11: 42 31 | 42 11 31"
                else -> it
            }
        }
        return solve(updatedInput)
    }
}