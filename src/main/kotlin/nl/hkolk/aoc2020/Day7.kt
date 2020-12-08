package nl.hkolk.aoc2020

class Day7(private val input: List<String>) {

    fun Map<String, Set<String>>.findParents(name: String, depth: Int = 0): Set<String> {
        println(" ".repeat(depth) + name)
        get(name)?.let {
            return it.flatMap { this.findParents(it, depth + 1) }.toSet().plus(name)
        }
        return setOf(name)
    }

    fun Map<String, List<Pair<Int, String>>>.countContents(name: String): Int {
        return get(name)?.map {
            (it.first * this.countContents(it.second)) + it.first
        }?.sum() ?: 0
    }
    val containedIn = mutableMapOf<String, Set<String>>()
    val bagContents = mutableMapOf<String, List<Pair<Int, String>>>()
    init {
        val bagMatcher = Regex("""([0-9]+ )?([a-z]+ [a-z]+) bag""")
        for(line in input) {
            val parts = line.split("contain")
            val bagName = bagMatcher.find(parts[0])!!.groupValues[2]
            val contents = bagMatcher.findAll(parts[1])
            for(content in contents) {
                val name = content.groupValues[2]
                val amount = content.groupValues[1].trim().toIntOrNull() ?: 0
                containedIn[name] = containedIn.getOrDefault(name, setOf()).plus(bagName)
                bagContents[bagName] = bagContents.getOrDefault(bagName, listOf()).plus(Pair(amount, name));
            }
        }
    }


    fun solvePart1(): Int = containedIn.findParents("shiny gold").size - 1
    fun solvePart2(): Int = bagContents.countContents("shiny gold")
}