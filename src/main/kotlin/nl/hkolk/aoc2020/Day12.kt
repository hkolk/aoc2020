package nl.hkolk.aoc2020

import kotlin.math.absoluteValue

class Day12(val input: List<String>) {

    val directions = input.map { Pair(it[0], it.filter { it in '0'..'9' }.toInt()) }

    fun Point2D.newLocation(id: Char, amount: Int): Point2D {
        return when (id) {
            'E' -> Point2D(this.x + amount, this.y)
            'W' -> Point2D(this.x - amount, this.y)
            'N' -> Point2D(this.x, this.y + amount)
            'S' -> Point2D(this.x, this.y - amount)
            else -> throw IllegalArgumentException("$id could not be mapped")
        }
    }

    fun Point2D.rotate(degrees:Int): Point2D {
        return when(degrees) {
            90  -> Point2D(y, -x)
            270 -> Point2D(-y, x)
            180 -> Point2D(-x, -y)
            else -> Point2D(x, y)
        }
    }

    fun solvePart1(): Int {
        var loc = Point2D(0,0)
        var facing = 90
        //println("Location: $loc, facing: $facing")
        for(dir in directions) {
            //println("Direction: $dir")
            when(dir.first) {
                'N', 'S', 'E', 'W' -> loc = loc.newLocation(dir.first, dir.second)
                'R' -> facing = (facing + dir.second) % 360
                'L' -> facing = (facing - dir.second + 360) % 360
                'F' -> when (facing) {
                    0   -> loc = loc.newLocation('N', dir.second)
                    90  -> loc = loc.newLocation('E', dir.second)
                    180 -> loc = loc.newLocation('S', dir.second)
                    270 -> loc = loc.newLocation('W', dir.second)

                }
            }
            //println("Location: $loc, facing: $facing")
        }
        return loc.manhattan()
    }
    fun solvePart2(): Int {
        var loc = Point2D(0,0)
        var waypoint = Point2D(10, 1)

        //println("Location: $loc, waypoint: $waypoint")
        for(dir in directions) {
            //println("Direction: $dir")
            when(dir.first) {
                'N', 'S', 'E', 'W' -> waypoint = waypoint.newLocation(dir.first, dir.second)
                'R' -> waypoint = waypoint.rotate(dir.second)
                'L' -> waypoint = waypoint.rotate(360 - dir.second)
                'F' -> loc = Point2D(loc.x + (waypoint.x * dir.second), loc.y + (waypoint.y * dir.second))
            }
            //println("Location: $loc, waypoint: $waypoint")
        }
        return loc.manhattan()
    }
}