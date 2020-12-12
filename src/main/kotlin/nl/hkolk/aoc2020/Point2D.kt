package nl.hkolk.aoc2020

data class Point2D(val x:Int, val y:Int) {
    fun adjacent(): Sequence<Point2D> = sequence {
        DIRECTIONMAPPERS.map { yield(it(this@Point2D)) }
    }
    companion object {
        val NORTH: (Point2D) -> Point2D = { Point2D(it.x + 1, it.y) }
        val NORTHEAST: (Point2D) -> Point2D = { Point2D(it.x + 1, it.y + 1) }
        val EAST: (Point2D) -> Point2D = { Point2D(it.x, it.y + 1) }
        val SOUTHEAST: (Point2D) -> Point2D = { Point2D(it.x - 1, it.y + 1) }
        val SOUTH: (Point2D) -> Point2D = { Point2D(it.x - 1, it.y) }
        val SOUTHWEST: (Point2D) -> Point2D = { Point2D(it.x - 1, it.y - 1) }
        val WEST: (Point2D) -> Point2D = { Point2D(it.x, it.y - 1) }
        val NORTHWEST: (Point2D) -> Point2D = { Point2D(it.x + 1, it.y - 1) }

        val DIRECTIONMAPPERS: List<(Point2D) -> Point2D> = listOf(
            NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST
        )
    }
}