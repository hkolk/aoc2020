package nl.hkolk.aoc2020

class Day17(val input: List<String>) {

    data class HyperPoint(val coords: List<Int>) {
        fun adjacent(): List<HyperPoint> {
            val mutators = explore(coords.size, -1..1, listOf())
            val neighbours = mutableListOf<HyperPoint>()

            for(mutator in mutators) {
                neighbours.add(HyperPoint(coords.zip(mutator) { it, other -> it + other }))
            }
            //neighbours.forEachIndexed{ index, coords -> println("NeighbourId: $index, coords: $coords")}
            return neighbours
        }

        private fun explore(depth: Int, values: IntRange, accu: List<Int>): List<List<Int>> {
            return if(depth == 0) {
                if(!accu.none{it != 0}) {
                    listOf(accu)
                } else {
                    listOf()
                }
            } else {
                values.flatMap { explore(depth-1, values, accu.plus(it)) }
            }
        }
    }

    fun initSpace(dimensions: Int):Set<HyperPoint> {
        return  input.flatMapIndexed{ y, line ->
            line.mapIndexedNotNull { x, char ->
                val coords = mutableListOf<Int>(x, y)
                (0 until dimensions-2).forEach{coords.add(0)}
                when (char) {
                    '#' -> HyperPoint(coords)
                    else -> null
                }
            }
        }.toSet()
    }

    fun Set<HyperPoint>.spaceToScan(): Set<HyperPoint> = this.flatMap { it.adjacent() }.toSet()


    fun solve(dimensions: Int): Int {
        var activeSpace = initSpace(dimensions)
        for(step in 1 .. 6) {
            val newActiveSpace = mutableSetOf<HyperPoint>()
            for(point in activeSpace.spaceToScan()) {
                val occ = point.adjacent().count { activeSpace.contains(it) }
                if(activeSpace.contains(point)) {
                    if(occ in 2..3) {
                        newActiveSpace.add(point)
                    }
                } else {
                    if(occ == 3) {
                        newActiveSpace.add(point)
                    }
                }
            }
            println("After step $step: ${newActiveSpace.size}")
            activeSpace = newActiveSpace
        }
        return activeSpace.size
    }

    fun solvePart2() = solve(4)
    fun solvePart1() = solve(3)

}