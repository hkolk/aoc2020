package nl.hkolk.aoc2020

import com.ginsberg.cirkle.circular
import java.lang.IllegalStateException

class Day23(val input: List<String>) {
    fun solvePart1_old(): String {
        var deck = input[0].map { char -> char.toString().toInt() }

        for(round in 1 .. 100) {
            println("Round $round start: $deck")
            val newDeck = deck.toMutableList()
            val cur = newDeck.removeAt(0)
            //println(" cur: $cur")
            val aside = listOf(newDeck.removeAt(0), newDeck.removeAt(0), newDeck.removeAt(0))
            //println(" aside: $aside")
            //println(" deck after aside: $newDeck")

            var droppoint = -1
            for( i in (cur-1 downTo 1).plus(9 downTo cur)) {
                val idx = newDeck.indexOf(i)
                if(idx != -1) {
                    droppoint = idx
                    break
                }
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

    fun solve(initial: String, decksize: Int = initial.length,  loops:Int = 100) : IntArray {
        val deck = IntArray(decksize + 1)
        val initialDeck = input[0].map { char -> char.toString().toInt() }.plus( initial.length+1 .. decksize )

        var prev = 0
        for(num in initialDeck) {
            //println("$prev => $num")
            deck[prev] = num
            prev = num
        }
        // tie end back to start
        deck[prev] = deck[0]

        fun rangeFrom(from: Int, rangeSize: Int) = sequence {
            for(i in from-1 downTo 1) {
                yield(i)
            }
            for(i in rangeSize downTo from+1) {
                yield(i)
            }
        }

        var cur = deck[0]
        for(round in 1 .. loops) {
            val take = listOf(deck[cur], deck[deck[cur]], deck[deck[deck[cur]]])
            val next = deck[deck[deck[deck[cur]]]]
            val drop = rangeFrom(cur, decksize).filter { !take.contains(it) }.first()
            //println("cur: $cur, taken: $take, $drop")
            deck[take[2]] = deck[drop]
            deck[drop] = take[0]
            deck[cur] = next
            cur = next
        }
        return deck
    }

    fun solvePart1(): String {
        val deck = solve(input[0])

        var cur = deck[1]
        var acc = ""
        while(cur != 1) {
            acc += cur.toString()
            cur = deck[cur]
        }
        return acc
    }
    fun solvePart2(): Long {
        val deck = solve(input[0], 1_000_000, 10_000_000)
        return deck[1].toLong() * deck[deck[1]].toLong()
    }

    fun solvePart_2(): String {
        var deck = input[0].map { char -> char.toString().toInt() }
        deck = deck + (10..1_000_000)
        val seen = mapOf(deck.hashCode() to 0).toMutableMap()

        for(round in 1 .. 10000) {

            if(round % 1000 == 0) {
                //println("Round $round start: ${deck.take(10)}, end: ${deck.takeLast(10)}")
                println("Round $round, after 1: ${deck.subList(deck.indexOf(1), deck.indexOf(1)+10)}")

            }
            val newDeck = deck.toMutableList()
            val cur = newDeck.removeAt(0)
            //println(" cur: $cur")
            val aside = listOf(newDeck.removeAt(0), newDeck.removeAt(0), newDeck.removeAt(0))
            //println(" aside: $aside")
            //println(" deck after aside: $newDeck")
            var droppoint = -1
            for( i in (cur-1 downTo 1).plus(1_000_000 downTo cur)) {
                val idx = newDeck.indexOf(i)
                if(idx != -1) {
                    droppoint = idx
                    break
                }
            }
            //println(" droppoint: $droppoint (val: ${newDeck[droppoint]})")
            newDeck.addAll(droppoint+1, aside)
            newDeck.add(cur)
            //println(" deck afer: $newDeck")
            deck = newDeck
            if(seen.containsKey(deck.hashCode())) {
                println("Round $round produced the same result as ${seen[deck.hashCode()]}")
            } else {
                seen[deck.hashCode()] = round
            }
        }
        val idx = deck.indexOf(1)
        TODO()
        //return (idx+1 until deck.size).plus(0 until idx).map { deck[it] }.joinToString("")
    }
}