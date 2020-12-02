package nl.hkolk.aoc2020

class Day1(input: List<String>) {
    private val expenses: List<Int> = input.map { it.toInt() }


    private fun solveUniversal(elements: Int): Int {
        val combinations = expenses.combinations(elements)
        for( i in combinations ) {
            if(i.sum() == 2020) {
                return i.multiply()
            }
        }
        return 0
    }

    fun solvePart2(): Int = solveUniversal(3)
    fun solvePart1(): Int = solveUniversal(2)


    fun List<Int>.multiply(): Int {
        var multiple = 1
        for (element in this) {
            multiple *= element
        }
        return multiple
    }
}