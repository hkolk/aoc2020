package nl.hkolk.aoc2020

class Day10(input: List<String>) {
    private val adapters = input.map { it.toIntOrNull() ?: throw IllegalArgumentException("Not an integer: $it") }.sorted()
    fun solvePart1():Int {
        var singleIncrements = 0
        var tripleIncrements = 1 // Start at one because of the final addition
        var previousAdapter = 0
        for(adapter in adapters) {
            when(adapter - previousAdapter) {
                1 -> singleIncrements++
                3 -> tripleIncrements++
                else -> throw IllegalStateException("Could not calculate difference between $adapter and $previousAdapter")
            }
            previousAdapter = adapter
        }
        return tripleIncrements * singleIncrements
    }

    fun solvePart2():Long {
        val prevPaths = mutableMapOf<Int, Long>(0 to 1).withDefault { 0 }
        for(adapter in adapters) {
            // ways to get to this adapter is the sum of the options before it
            prevPaths[adapter] = prevPaths.filter { it.key in adapter-3 until adapter }.map { it.value }.sum()
        }
        println(prevPaths)
        return prevPaths[prevPaths.keys.maxOrNull()!!]!!
    }

    /// DEAD CODE THAT TOOK FOREVER
    val optionPaths = mutableSetOf<Pair<Int, Int>>()
    fun recurseChain(remainder: List<Int>, previousValue:Int): Long {
        val options = remainder.filter { it in previousValue+1..previousValue+3 }.size
        if(options > 1) {
            //println("$previousValue - $options")
            optionPaths.add(Pair(previousValue, options))
        }
        return if(remainder.isEmpty()) {
            1
        } else {
            remainder.filter { it in previousValue+1..previousValue+3 }.map { lowerBound ->
                recurseChain(adapters.filter { it > lowerBound }, lowerBound)
            }.sum()
        }
    }
    fun solvePartRecursive2():Long {
        val ret = recurseChain(adapters, 0)
        println(optionPaths.groupBy { it.second }.map { Pair(it.key, it.value.size) })
        return ret
    }
}