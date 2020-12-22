package nl.hkolk.aoc2020

class Day19(val input: List<String>) {

    sealed class Rule() {
        abstract val name: String
        abstract fun match(input: String, prev: List<String>) : List<String>
        abstract fun compile(): String
        data class StringRule(override val name: String, val charToMatch: Char) : Rule() {
            override fun match(input: String, prev: List<String>): List<String> {
                val cnt = getCount()
                if(input.length > 0 && input[0] == charToMatch) {
                    //println("$cnt ${" ".repeat(Rule.INPUTSIZE - input.length)}Applying $this on $input: true")
                    println(prev.joinToString(" -> "))
                    return listOf(input.drop(1))
                } else {
                    //println("$cnt ${" ".repeat(Rule.INPUTSIZE - input.length)}Applying $this on $input: false")
                    println(prev.joinToString(" -> "))
                    return listOf()
                }
            }

            override fun compile(): String {
                return charToMatch.toString()
            }
        }
        data class SequenceRule(override val name: String, val sequence: List<String>) : Rule() {
            override fun match(input: String, prev: List<String>): List<String> {
                val cnt = getCount()
                var remainder = input
                var valid = mutableListOf<String>()


                for(step in 0 until sequence.size) {
                    val result = ALLRULES[sequence[step]]!!.match(remainder, prev + name)


                    if(result.isEmpty()) {
                        //println("$cnt ${" ".repeat(Rule.INPUTSIZE - input.length)}Applying $this on $input: false")
                        return listOf() // no matches all the way to the end
                    } else {
                        // explore all potential new paths
                        return listOf()
                    }
                }
                //println("$cnt ${" ".repeat(Rule.INPUTSIZE - input.length)}Applying $this on $input: true")
                return listOf()
            }

            override fun compile(): String {
                return sequence.map { ALLRULES[it]!!.compile() }.joinToString("")
            }
        }
        data class OrRule(override val name: String, val left: String, val right: String) : Rule() {
            override fun match(input: String, prev: List<String>): List<String> {
                val cnt = getCount()
                val res = ALLRULES[right]!!.match(input, prev + name)
                return if(res.isNotEmpty()) {
                    //println("$cnt ${" ".repeat(Rule.INPUTSIZE - input.length)}Applying $this on $input: right")
                    res
                } else {
                    //println("$cnt ${" ".repeat(Rule.INPUTSIZE - input.length)}Applying $this on $input: left")
                    ALLRULES[left]!!.match(input, prev + name)
                }
            }

            override fun compile(): String {
                if(name=="8") {
                    return "(" + ALLRULES["42"]!!.compile() + "(?:"+ALLRULES[left]!!.compile()+")*" + ")"
                } else if(name=="11") {
                    return "((?:"+ALLRULES["42"]!!.compile() + ")(?:"+ALLRULES[left]!!.compile()+")*(?:" + ALLRULES["31"]!!.compile() + "))"
                } else {
                    return "(?:(?:" + ALLRULES[left]!!.compile() + ")|(?:" + ALLRULES[right]!!.compile() + "))"
                }
            }
        }

        companion object {
            fun fromString(input: String) : List<Rule> {
                val parts = input.split(",", " ", ":").filter { it.isNotEmpty() }
                return when {
                    parts[1].getOrNull(0) == '"' -> {
                        listOf(StringRule(parts[0], parts[1].get(1)))
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
            var INPUTSIZE = 0
            var COUNTER = 0
            fun getCount(): String {
                val ret = COUNTER.toString().padStart(4)
                COUNTER++
                return ret
            }
        }
    }


    fun solvePart1(): Int {
        val parts = input.splitBy { it.isNullOrEmpty() }
        Rule.ALLRULES= parts[0].flatMap { Rule.fromString(it) }.map { Pair(it.name, it) }.toMap()
        println(Rule.ALLRULES["0"]!!.compile())

        return parts[1].map { Rule.ALLRULES["0"]!!.match(it, listOf()) }.count{ it.isEmpty()  }
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
        Rule.ALLRULES.forEach{println(it)}

        println(Rule.ALLRULES["42"]!!.compile())
        println(Rule.ALLRULES["31"]!!.compile())

        println(Rule.ALLRULES["0"]!!.compile())

        for(message in parts[1]) {
            Rule.INPUTSIZE = message.length
            println("$message == ${Rule.ALLRULES["0"]!!.match(message, listOf())}")
        }
        TODO()
        //return parts[1].map { Rule.ALLRULES["0"]!!.match(it, listOf()) }.count{ it.first && it.second.isEmpty() }
    }
}