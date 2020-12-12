package nl.hkolk.aoc2020

class Day11(input: List<String>) {
    enum class SpaceType {
        Occupied,
        Empty,
        OpenSpace,
    }
    data class Point2D(val x:Int, val y:Int) {
        fun adjacent(): Sequence<Point2D> = sequence {
            for (adjX in x - 1..x + 1) {
                for (adjY in y - 1..y + 1) {
                    if(adjY != y || adjX != x) yield(Point2D(adjX, adjY))
                }
            }
        }
    }

    private val initialMap: Map<Point2D, SpaceType> = input.flatMapIndexed{ y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    'L' -> Pair(Point2D(x, y), SpaceType.Empty)
                    '#' -> Pair(Point2D(x, y), SpaceType.Occupied)
                    '.' -> Pair(Point2D(x, y), SpaceType.OpenSpace)
                    else -> null
                }
            }
        }.toMap()


    fun Map<Point2D, SpaceType>.findOccupied(curLoc: Point2D, transform: (Point2D) -> Point2D): Boolean {
        val newLoc = transform(curLoc)
        when(get(newLoc)) {
            SpaceType.OpenSpace -> return findOccupied(newLoc, transform)
            SpaceType.Empty -> return false
            SpaceType.Occupied -> return true
        }
        return false
    }

    fun Map<Point2D, SpaceType>.findSightlinesOccupied(curLoc: Point2D): Int {
        val transformers: List<(Point2D) -> Point2D> = listOf(
            {Point2D(it.x-1, it.y + 1)},
            {Point2D(it.x-1, it.y) },
            {Point2D(it.x-1,  it.y - 1)},
            {Point2D(it.x, it.y + 1)},
            {Point2D(it.x,  it.y - 1)},
            {Point2D(it.x+1, it.y + 1)},
            {Point2D(it.x+1, it.y) },
            {Point2D(it.x+1,  it.y - 1)},
        )
        return transformers.map { findOccupied(curLoc, it) }.count { it }
    }

    fun Map<Point2D, SpaceType>.updateMapPart1(): Map<Point2D, SpaceType> {
        return mapNotNull{ entry ->
            if(entry.value == SpaceType.OpenSpace) {
                Pair(entry.key, entry.value)
            } else {
                val occupied = entry.key.adjacent().mapNotNull { this[it] }.count { it == SpaceType.Occupied }
                when (occupied) {
                    0 -> Pair(entry.key, SpaceType.Occupied)
                    in 4..Int.MAX_VALUE -> Pair(entry.key, SpaceType.Empty)
                    else -> Pair(entry.key, entry.value)
                }
            }
        }.toMap()
    }


    // sightlines, and tolerance of 5 seats
    fun Map<Point2D, SpaceType>.updateMapPart2(): Map<Point2D, SpaceType> {
        return mapNotNull{ entry ->
            if(entry.value == SpaceType.OpenSpace) {
                Pair(entry.key, entry.value)
            } else {
                val occupied = findSightlinesOccupied(entry.key)
                //if(entry.key == Point2D(2, 0)) println("Status: ${entry.value} Occupied: $occupied")
                when (occupied) {
                    0 -> Pair(entry.key, SpaceType.Occupied)
                    in 5..Int.MAX_VALUE -> Pair(entry.key, SpaceType.Empty)
                    else -> Pair(entry.key, entry.value)
                }
            }
        }.toMap()
    }



    fun solvePart1(): Int {
        var map = initialMap
        for(step in 0..1000) {
            val newmap = map.updateMapPart1()
            if(newmap.hashCode() == map.hashCode()) {
                return newmap.count { it.value == SpaceType.Occupied }
            }
            map = newmap
        }
        throw IllegalStateException("Ran out of loops")
    }
    fun solvePart2(): Int {
        var map = initialMap

        for(step in 0..1000) {
            val newmap = map.updateMapPart2()
            //println(newmap.count { it.value == SpaceType.Occupied })
            if(newmap.hashCode() == map.hashCode()) {
                return newmap.count { it.value == SpaceType.Occupied }
            }
            map = newmap
        }

        throw IllegalStateException("Ran out of loops")
    }
}