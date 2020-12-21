package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException

class Day21(val input: List<String>) {
    data class Food(val ingredients:Set<String>, val allergens:Set<String>) {
        companion object {
            private val PARSER = Regex("""^(.*) \(contains (.*)\)""")
            fun fromLine(line: String): Food {
                val grouped = PARSER.matchEntire(line)?.groupValues ?: throw IllegalArgumentException("could not parse $line")
                return Food(grouped[1].split(" ").toSet(), grouped[2].split(", ").toSet())
            }
        }
    }
    fun solvePart1(): Long {
        val foods = input.map { Food.fromLine(it) }
        val allergens = foods.flatMap { food -> food.allergens.map { Pair(it, food.ingredients)} }.groupBy { it.first }.map { Pair(it.key, it.value.map { it.second }) }
        val intersections = allergens.map { item ->  Pair(item.first, item.second.reduce { acc, list -> acc.intersect(list) } ) }
        val potentialAllergens = intersections.flatMap { it.second }.toSet()
        val safefoods = foods.flatMap { it.ingredients.filter { ingredient -> !potentialAllergens.contains(ingredient) } }
        return safefoods.count().toLong()
    }
    fun solvePart2(): String {
        val foods = input.map { Food.fromLine(it) }
        val allergens = foods.flatMap { food -> food.allergens.map { Pair(it, food.ingredients)} }.groupBy { it.first }.map { Pair(it.key, it.value.map { it.second }) }
        val intersections = allergens.map { item ->  Pair(item.first, item.second.reduce { acc, list -> acc.intersect(list) } ) }
        fun iter(remaining: List<Pair<String, Set<String>>>, taken: Map<String, String> = mapOf(), ): Map<String, String>? {
            return if(remaining.isEmpty()) {
                taken;
            } else {
                val (allergen, ingredient) = remaining.first()
                val potentials = ingredient.filter { !taken.contains(it) }
                if(potentials.isEmpty()) {
                    null
                } else {
                    potentials.mapNotNull { iter(remaining.drop(1), taken + Pair(it, allergen)) }.firstOrNull()
                }
            }
        }
        val finalAllergens = iter(intersections) ?: throw IllegalStateException("Could not find any allergens paths");
        return finalAllergens.entries.sortedBy { it.value }.map { it.key }.joinToString( "," )
    }
}