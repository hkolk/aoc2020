package nl.hkolk.aoc2020

class Day3(val input: List<String>) {

    enum class Tile(val char: Char) {
        Tree('#'),
        Space('.');

        companion object {
            private val map = Tile.values().associateBy(Tile::char)
            fun from(type: Char):Tile? = map[type]
        }
    }

    class AocMap(input: List<String>) {
        val map = mutableMapOf<Point2D, Tile>()
        var maxX = 0;
        var maxY = 0;

        init {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, tileChar ->
                    map[Point2D(x, y)] = Tile.from(tileChar) ?: throw IllegalArgumentException("invalid tile: $tileChar")
                    if (x > maxX) maxX = x
                }
                if (y > maxY) maxY = y
            }
        }

        fun print() {
            for(y in 0..maxY) {
                for(x in 0..maxX) {
                    when(map[Point2D(x, y)]) {
                        Tile.Tree -> print("#")
                        Tile.Space -> print(".")
                    }
                }
                println()
            }
        }
    }

    private val aocMap = AocMap(input)

    fun howManyTrees(right: Int, down: Int) : Int {
        var x = 0
        var trees = 0
        for( y in 0..aocMap.maxY step down ) {
            val tile = aocMap.map[Point2D(x % (aocMap.maxX+1), y)]
            if(tile == Tile.Tree) trees += 1
            x += right
        }
        return trees
    }

    fun solvePart1(): Long = howManyTrees(3, 1).toLong()
    fun solvePart2(): Long {
        val r1d1 = howManyTrees(1, 1).toLong()
        val r3d1 = howManyTrees(3, 1).toLong()
        val r5d1 = howManyTrees(5, 1).toLong()
        val r7d1 = howManyTrees(7, 1).toLong()
        val r1d2 = howManyTrees(1, 2).toLong()
        println("$r1d1, $r3d1, $r5d1, $r7d1, $r1d2")
        return r1d1 * r3d1 * r5d1 * r7d1 * r1d2
    }
}