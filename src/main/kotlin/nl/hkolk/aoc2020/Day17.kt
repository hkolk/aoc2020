package nl.hkolk.aoc2020

class Day17(input: List<String>) {

    data class Point3D(val x:Int, val y:Int, val z:Int) {
        fun adjacent(): Sequence<Point3D> = sequence {
            for(newX in x-1 .. x+1) {
                for(newY in y-1..y+1) {
                    for(newZ in z-1..z+1) {
                        if(newX != x || newY != y || newZ != z) {
                            yield(Point3D(newX, newY, newZ))
                        }
                    }
                }
            }
        }
    }

    val initialSpace = input.flatMapIndexed{ y, line ->
        line.mapIndexedNotNull { x, char ->
            when (char) {
                '#' -> Point3D(x, y, 0)
                else -> null
            }
        }
    }.toSet()

    fun Set<Point3D>.spaceToScan(): Set<Point3D> = this.flatMap { it.adjacent() }.toSet()

    fun solvePart1(): Int {
        var activeSpace = initialSpace
        for(step in 1 .. 6) {
            val newActiveSpace = mutableSetOf<Point3D>()
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
    fun solvePart2(): Int {
        TODO()
    }
}