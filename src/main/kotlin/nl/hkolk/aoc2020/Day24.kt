package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

class Day24(val input: List<String>) {

    data class Point3D(val x:Int, val y:Int, val z:Int) {

        fun adjacent(): Sequence<Point3D> = sequence {
            Point3D.DIRECTIONS.map { yield(it(this@Point3D)) }
        }

        fun move(direction: Point3D, times: Int=1): Point3D = move( { it.moveBy(x=direction.x, y=direction.y, z=direction.z) }, times)

        fun move(direction: (Point3D) -> Point3D, times: Int=1): Point3D {
            return direction.repeated(times).fold(this) { acc, func -> func(acc) }
        }

        fun moveBy(x: Int = 0, y: Int = 0, z: Int = 0) : Point3D = Point3D(x = this.x + x, y = this.y+y, z = this.z + z)


        companion object {
            val NORTHWEST: (Point3D) -> Point3D =    { it.moveBy(x =  0,  y = +1, z = -1) }
            val NORTHEAST: (Point3D) -> Point3D =    { it.moveBy(x = +1,  y =  0, z = -1) }
            val EAST: (Point3D) -> Point3D =         { it.moveBy(x = +1,  y = -1, z =  0) }
            val SOUTHEAST: (Point3D) -> Point3D =    { it.moveBy(x =  0,  y = -1, z = +1) }
            val SOUTHWEST: (Point3D) -> Point3D =    { it.moveBy(x = -1,  y =  0, z = +1) }
            val WEST: (Point3D) -> Point3D =         { it.moveBy(x = -1,  y = +1, z =  0) }

            val DIRECTIONS: List<(Point3D) -> Point3D> = listOf(
                NORTHEAST, EAST, SOUTHEAST, SOUTHWEST, WEST, NORTHWEST
            )
            fun parseTile(input: String) : Point3D {
                var i = 0
                var ret = Point3D(0, 0, 0)
                while(i < input.length) {
                    val direction = when(input[i]) {
                        'e' -> EAST
                        'w' -> WEST
                        'n' -> {
                            i++
                            when(input[i]) {
                                'e' -> NORTHEAST
                                'w' -> NORTHWEST
                                else -> throw IllegalStateException()
                            }
                        }
                        's' -> {
                            i++
                            when(input[i]) {
                                'e' -> SOUTHEAST
                                'w' -> SOUTHWEST
                                else -> throw IllegalStateException()
                            }
                        }
                        else -> throw IllegalStateException()
                    }
                    ret = ret.move(direction)
                    //println("Direction: $direction, tile: $ret")
                    i++
                }
                return ret
            }
        }
    }
    val initialFloor = mapOf<Point3D, Boolean>().toMutableMap()
    init {
        for(tileToFlip in input) {
            val tile = Point3D.parseTile(tileToFlip)
            initialFloor[tile] = !(initialFloor[tile] ?: false)
        }
    }

    fun solvePart1(): Int = initialFloor.count { it.value }

    fun solvePart2(): Int {
        var floor = initialFloor
        for(day in 0 until 100) {
            val newFloor = mapOf<Point3D, Boolean>().toMutableMap()
            for(tile in floor.filter { it.value }.keys.flatMap{ it.adjacent() + it }) {
                val blackTiles = tile.adjacent().count { floor[it] ?: false }
                //println(blackTiles)
                val isBlack = if(floor.getOrDefault(tile, false) && (blackTiles == 0 || blackTiles > 2)) {
                    false
                } else if((!floor.getOrDefault(tile, false)) && blackTiles == 2) {
                    true
                } else {
                    floor.getOrDefault(tile, false)
                }
                //println("${tile} was ${floor.getOrDefault(tile, false)}, becomes $isBlack with $blackTiles surrounding)")
                newFloor[tile] = isBlack
            }
            floor = newFloor
            println("After day ${day+1}, black tiles: ${floor.count { it.value }}")
        }
        return floor.count { it.value }
    }
}