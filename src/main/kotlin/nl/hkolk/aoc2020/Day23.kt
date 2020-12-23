package nl.hkolk.aoc2020

import com.ginsberg.cirkle.circular
import java.lang.IllegalStateException

class Day23(val input: List<String>) {
    fun solvePart1(): String {
        var deck = input[0].map { char -> char.toString().toInt() }

        for(round in 1 .. 100) {
            println("Round $round start: $deck")
            val newDeck = deck.toMutableList()
            val cur = newDeck.removeAt(0)
            //println(" cur: $cur")
            val aside = listOf(newDeck.removeAt(0), newDeck.removeAt(0), newDeck.removeAt(0))
            //println(" aside: $aside")
            //println(" deck after aside: $newDeck")
            val droppoint = (cur-1 downTo 1).plus(9 downTo cur).fold(-1 ) {acc, value ->
                if(acc > -1) acc else newDeck.indexOf(value)
            }
            //println(" droppoint: $droppoint (val: ${newDeck[droppoint]})")
            newDeck.addAll(droppoint+1, aside)
            newDeck.add(cur)
            //println(" deck afer: $newDeck")
            deck = newDeck
        }
        val idx = deck.indexOf(1)
        return (idx+1 until deck.size).plus(0 until idx).map { deck[it] }.joinToString("")
        TODO()
    }

    fun solvePart2(): String {
        var deck = input[0].map { char -> char.toString().toInt() }
        deck = deck + (10..1_000_000)

        for(round in 1 .. 1_000_000) {
            if(round % 1000 == 0)
            println("Round $round start: ${deck.size}")
            val newDeck = deck.toMutableList()
            val cur = newDeck.removeAt(0)
            //println(" cur: $cur")
            val aside = listOf(newDeck.removeAt(0), newDeck.removeAt(0), newDeck.removeAt(0))
            //println(" aside: $aside")
            //println(" deck after aside: $newDeck")
            val droppoint = (cur-1 downTo 1).plus(9 downTo cur).fold(-1 ) {acc, value ->
                if(acc > -1) acc else newDeck.indexOf(value)
            }
            //println(" droppoint: $droppoint (val: ${newDeck[droppoint]})")
            newDeck.addAll(droppoint+1, aside)
            newDeck.add(cur)
            //println(" deck afer: $newDeck")
            deck = newDeck
        }
        val idx = deck.indexOf(1)
        TODO()
        //return (idx+1 until deck.size).plus(0 until idx).map { deck[it] }.joinToString("")
    }
}