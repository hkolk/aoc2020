package nl.hkolk.aoc2020

import kotlin.math.absoluteValue

class Day12(val input: List<String>) {

    val directions = input.map { Pair(it[0], it.filter { it in '0'..'9' }.toInt()) }

    fun Point2D.newLocation(id: Char, amount: Int): Point2D {
        return when (id) {
            'E' -> move(Point2D.EAST, amount)
            'W' -> move(Point2D.WEST, amount)
            'N' -> move(Point2D.NORTH, amount)
            'S' -> move(Point2D.SOUTH, amount)
            else -> throw IllegalArgumentException("$id could not be mapped")
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
                'F' -> loc = loc.move({Point2D(it.x + waypoint.x, it.y+waypoint.y)}, dir.second)
            }
            //println("Location: $loc, waypoint: $waypoint")
        }
        return loc.manhattan()
    }
}