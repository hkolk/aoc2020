package nl.hkolk.aoc2020

import kotlin.math.sqrt

class Day20(val input: List<String>) {

    val nessie = setOf(
        Point2D(0,0),
        Point2D(1, 1),
        Point2D(0, 1),
        Point2D(-1, 1),
        Point2D(-1, 1),
        Point2D(-6, 1),
        Point2D(-7, 1),
        Point2D(-7, 1),
        Point2D(-12, 1),
        Point2D(-13, 1),
        Point2D(-18, 1),
        Point2D(-2, 2),
        Point2D(-5, 2),
        Point2D(-5, 2),
        Point2D(-8, 2),
        Point2D(-11, 2),
        Point2D(-14, 2),
        Point2D(-17, 2),
    )
    val nessies = listOf(
        nessie,
        nessie.map{it.rotate(90)},
        nessie.map{it.rotate(180)},
        nessie.map{it.rotate(270)},
        nessie.map{it.flip()},
        nessie.map{it.flip().rotate(90)},
        nessie.map{it.flip().rotate(180)},
        nessie.map{it.flip().rotate(270)}
    )

    fun relativeNessie(nessie: Collection<Point2D>, head: Point2D) = nessie.map { it.move(head) }

    data class Tile(val id: Long, val sides: List<String>, val inner: List<String>) {
        fun sideWithId(): List<Pair<Long, String>> = sides.map { Pair(id, it) }

        fun rotate() = Tile(id, listOf(sides[3], sides[0], sides[1], sides[2]), inner.rotate())

        fun flip(): Tile {
            val newSides = sides.map{it.reversed()}
            return Tile(id, listOf(newSides[0], newSides[3], newSides[2], newSides[1]) , inner.map{it.reversed()})
        }

        fun makeFit(sideId: Int, toMatch: String): Tile {
            var temp = this
            (0..3).forEach {
                temp = temp.rotate()
                if(temp.sides[sideId].reversed() == toMatch) {
                    return temp;
                }
            }
            temp = temp.flip()
            (0..3).forEach {
                temp = temp.rotate()
                if(temp.sides[sideId].reversed() == toMatch) {
                    return temp;
                }
            }
            throw IllegalStateException("Flipped and rotated but no match")
        }

        companion object {
            fun from(input: List<String>): Tile {
                val id = input[0].filter { it in '0'..'9' }.toLong()
                val sides = listOf(
                    input[1],
                    (1..10).map{ input[it][9] }.joinToString(""),
                    input[10].reversed(),
                    (10 downTo 1).map{ input[it][0] }.joinToString("")
                )
                val inner = (2..9).map{input[it].substring(1,9)}
                return Tile(id, sides, inner)
            }
        }
    }

    fun solvePart1(): Long {
        val tiles = input.splitBy { it.isEmpty() }.map{ Tile.from(it) }

        val possibleSides = tiles.flatMap { it.sideWithId() + it.flip().sideWithId() }
        val groupedSides = possibleSides.groupBy { it.second }
        val corners = mutableListOf<Tile>()
        for(tile in tiles) {
            val matches = tile.sides.count { groupedSides.containsKey(it) && groupedSides[it]!!.size > 1 }
            val matches2 = tile.flip().sides.count { groupedSides.containsKey(it) && groupedSides[it]!!.size > 1 }
            println("${tile.id} has $matches and $matches2")
            if(matches <= 2 && matches2 <= 2) {
                corners.add(tile)
            }
        }
        println(corners.size)
        if(corners.size == 4) {
            return corners.map { it.id }.multiply()
        }
        //println(tiles)

        TODO()
    }
    fun solvePart2(): Long {
        val tiles = input.splitBy { it.isEmpty() }.map{ Tile.from(it) }

        val possibleSides = tiles.flatMap { it.sideWithId() + it.flip().sideWithId() }
        val groupedSides = possibleSides.groupBy { it.second }
        val corners = mutableListOf<Tile>()
        for(tile in tiles) {
            val matches = tile.sides.count { groupedSides.containsKey(it) && groupedSides[it]!!.size > 1 }
            val matches2 = tile.flip().sides.count { groupedSides.containsKey(it) && groupedSides[it]!!.size > 1 }
            if(matches <= 2 && matches2 <= 2) {
                corners.add(tile)
            }
        }
        val gridsize = sqrt(tiles.size.toDouble()).toInt()
        val grid = mutableMapOf<Point2D, Tile>()

        var topleft = corners[0]
        println("${topleft.sides[0]} ${topleft.sides[3]}")
        topleft = topleft.flip()
        println("${topleft.sides[0]} ${topleft.sides[3]}")
        while(groupedSides[topleft.sides[0]]!!.count() != 1 || groupedSides[topleft.sides[3]]!!.count() != 1) {
            topleft = topleft.rotate()
            println("Rotated")
            println("${topleft.sides[0]} ${topleft.sides[3]}")
        }
        println(groupedSides["#...##.#.."])
        println(groupedSides["##.#..#..#"])
        grid[Point2D(0, 0)] = topleft
        println("0,0 = ${topleft.id}")

        for(x in 1 until gridsize) {
            val toMatch = grid[Point2D(x-1, 0)]!!.sideWithId()[1]
            val connectedTileId = groupedSides[toMatch.second]!!.filter { it.first != toMatch.first }.first().first
            var connectedTile = tiles.find { it.id == connectedTileId }!!.makeFit(3, toMatch.second)
            println("0,$x = ${connectedTile.id}")
            grid[Point2D(x, 0)] = connectedTile
        }

        for(y in 1 until gridsize) {
            for (x in 0 until gridsize) {
                println("x: $x, y: $y")
                val toMatch = grid[Point2D(x, y-1)]!!.sideWithId()[2]
                println(groupedSides[toMatch.second])

                val connectedTileId = groupedSides[toMatch.second]!!.filter { it.first != toMatch.first }.last().first
                var connectedTile = tiles.find { it.id == connectedTileId }!!.makeFit(0, toMatch.second)
                println("$y,$x = ${connectedTile.id}")
                grid[Point2D(x, y)] = connectedTile
            }
        }
        val image = mutableMapOf<Point2D, Char>()
        for(y in 0 until gridsize) {
            for(x in 0 until gridsize) {
                val tile = grid[Point2D(x, y)]!!
                print("${tile.id} ")
                for((subY, line) in tile.inner.withIndex()) {
                    for ((subX, char) in line.withIndex()) {
                        image[Point2D(x*8+subX, y*8+subY)] = char
                    }
                }
            }
            println()
        }

        for(y in 0 until (gridsize*8)) {
            for(x in 0 until (gridsize*8)){
                print(image[Point2D(x, y)])
            }
            println()
        }

        for(y in 0..2) {
            for(x in -18..2) {
                print(if(nessie.contains(Point2D(x, y))) { "#" } else { " " } )
            }
            println()
        }
        for(y in -2..0) {
            for(x in -2..18) {
                print(if(nessies[2].contains(Point2D(x, y))) { "#" } else { " " } )
            }
            println()
        }
        for(y in 0..2) {
            for(x in -2..18) {
                print(if(relativeNessie(nessies[2], Point2D(0, 2)).contains(Point2D(x, y))) { "#" } else { " " } )
            }
            println()
        }

        for(possibleHead in image.filter { it.value == '#'}.map{ it.key}) {
            for(nessie in nessies.map{relativeNessie(it, possibleHead)}) {
                // everything # ?
                if(nessie.filter { image[it] =='#' }.count() == nessie.count()) {
                    println("Found nessie with head at $possibleHead")
                    nessie.forEach { image[it] = 'o' }
                }
            }
        }

        for(y in 0 until (gridsize*8)) {
            for(x in 0 until (gridsize*8)){
                print(image[Point2D(x, y)])
            }
            println()
        }
        return image.count { it.value == '#'}.toLong()
    }
}