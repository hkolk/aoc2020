package nl.hkolk.aoc2020

class Day22(val input: List<String>) {

    fun subGame(p1: List<Int>, p2: List<Int>, depth:Int = 1): Int {
        val prev = mutableSetOf<Pair<List<Int>, List<Int>>>()
        var player1 = p1
        var player2 = p2
        var round = 0
        //println("${" ".repeat(depth)}R0: P1: $player1, P2: $player2")
        while(player1.isNotEmpty() && player2.isNotEmpty()) {
            round++
            val p1Card = player1.first()
            val p2Card = player2.first()
            val winner = if(prev.contains(Pair(player1, player2))) {
                return 1
            } else if(player1.size > p1Card && player2.size > p2Card ) {
                subGame(player1.drop(1).take(p1Card.toInt()), player2.drop(1).take(p2Card.toInt()), depth+1, )
            } else if (p1Card > p2Card)  {
                1
            } else {
                2
            }
            prev.add(Pair(player1, player2))

            if(winner == 1) {
                player1 = player1.drop(1).plus(p1Card).plus(p2Card)
                player2 = player2.drop(1)
            } else {
                player1 = player1.drop(1)
                player2 = player2.drop(1).plus(p2Card).plus(p1Card)
            }
            if(round % 1000 == 0)
                println("${" ".repeat(depth)}R$round: P1: $player1, P2: $player2")

        }
        return if(player1.isNotEmpty()) 1 else 2
    }

    fun solvePart1(recurse:Boolean = false): Long {
        val prev = mutableSetOf<Pair<List<Int>, List<Int>>>()
        val decks = input.splitBy { it.isEmpty() }
        var player1 = decks[0].drop(1).map {it.toInt()}
        var player2 = decks[1].drop(1).map {it.toInt()}
        println("R0: P1: $player1, P2: $player2")
        var round = 0
        while(player1.isNotEmpty() && player2.isNotEmpty()) {
            round++
            val p1Card = player1.first()
            val p2Card = player2.first()
            val winner = if(prev.contains(Pair(player1, player2))) {
                println("already played")
                1
            } else if(recurse && (player1.size > p1Card && player2.size > p2Card)) {
                subGame(player1.drop(1).take(p1Card.toInt()), player2.drop(1).take(p2Card.toInt()) )
            } else if (p1Card > p2Card)  {
                1
            } else {
                2
            }
            //println("Winner: $winner")
            prev.add(Pair(player1, player2))

            if(winner == 1) {
                player1 = player1.drop(1).plus(p1Card).plus(p2Card)
                player2 = player2.drop(1)
            } else {
                player1 = player1.drop(1)
                player2 = player2.drop(1).plus(p2Card).plus(p1Card)
            }
            if(round % 1000 == 0)
                println("R$round: P1: $player1, P2: $player2")
        }
        val total = player1.plus(player2)
        return (total.size downTo 1).zip(total) { a, b -> a * b}.sum().toLong()
    }
    fun solvePart2(): Long = solvePart1(true)
}