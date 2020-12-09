package nl.hkolk.aoc2020

class Day9(input: List<String>, val windowSize:Int=25) {
    val input = input.map { it.toLongOrNull()?: throw IllegalArgumentException("Could not parse $it to integer") }
    fun findInvalid(): Long {
        val buffer = input.take(windowSize).toMutableList()
        for(i in windowSize until input.size) {
            var valid = false;
            for(combination in buffer.combinations(2)) {
                if(combination.sum() == input[i]) {
                    valid = true
                    break
                }
            }
            if(!valid) {
                return input[i]
            }
            buffer.removeFirst()
            buffer.add(input[i])
        }
        throw IllegalStateException("Could not find invalid number")
    }
    fun solvePart1(): Long = findInvalid()
    fun solvePart2(): Long {
        val invalid = findInvalid()
        for(size in 2..input.size) {
            for(combination in input.sliding(size)) {
                if(combination.sum() == invalid) {
                    println("Found match: $combination")
                    return combination.minOrNull()!! + combination.maxOrNull()!!
                }
            }
        }
        TODO()
    }


    fun List<Long>.sliding(size: Int): Sequence<List<Long>> = sequence {
        val iterator = iterator()
        val window = mutableListOf<Long>()
        repeat((1..size).count()) { if (iterator.hasNext()) window.add(iterator.next()) }
        yield(window)
        while (iterator.hasNext()) {
            window.removeFirstOrNull()
            window.add(iterator.next())
            yield(window)
        }
    }
}