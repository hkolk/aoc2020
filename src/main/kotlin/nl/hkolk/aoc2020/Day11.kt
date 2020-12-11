package nl.hkolk.aoc2020

class Day11(input: List<String>) {
    data class Point2D(val x:Int, val y:Int) {
        fun adjacent(): Sequence<Point2D> = sequence {
            for (adjX in x - 1..x + 1) {
                for (adjY in y - 1..y + 1) {
                    if(adjY != y || adjX != x) yield(Point2D(adjX, adjY))
                }
            }
        }
    }

    private val initialMap: Map<Point2D, Boolean> = input.flatMapIndexed{ y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    'L' -> Pair(Point2D(x, y), false)
                    '#' -> Pair(Point2D(x, y), true)
                    else -> null
                }
            }
        }.toMap()

    fun Map<Point2D, Boolean>.findSeat(curLoc: Point2D, transform: (Point2D) -> Point2D): Point2D {
        val newLoc = transform(curLoc)
        when(get(newLoc)) {
            true :
        }

        findSeat()
    }
    fun Map<Point2D, Boolean>.updateMapPart1(): Map<Point2D, Boolean> {
        return mapNotNull{ entry ->
            val occupied = entry.key.adjacent().mapNotNull{ this[it] }.count { it }
            when(occupied) {
                0 -> Pair(entry.key, true)
                in 4..Int.MAX_VALUE -> Pair(entry.key, false)
                else -> Pair(entry.key, entry.value)
            }
        }.toMap()
    }

    // sightlines, and tolerance of 5 seats
    fun Map<Point2D, Boolean>.updateMapPart2(): Map<Point2D, Boolean> {
        return mapNotNull{ entry ->
            val occupied = entry.key.adjacent().mapNotNull{ this[it] }.count { it }
            when(occupied) {
                0 -> Pair(entry.key, true)
                in 5..Int.MAX_VALUE -> Pair(entry.key, false)
                else -> Pair(entry.key, entry.value)
            }
        }.toMap()
    }


    fun solvePart1(): Int {
        var map = initialMap
        for(step in 0..1000) {
            val newmap = map.updateMapPart1()
            if(newmap.hashCode() == map.hashCode()) {
                return newmap.count { it.value }
            }
            map = newmap
        }
        throw IllegalStateException("Ran out of loops")
    }
    fun solvePart2(): Int {
        var map = initialMap
        for(step in 0..1000) {
            val newmap = map.updateMapPart2()
            if(newmap.hashCode() == map.hashCode()) {
                return newmap.count { it.value }
            }
            map = newmap
        }
        throw IllegalStateException("Ran out of loops")
    }
}