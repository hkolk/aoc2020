package nl.hkolk.aoc2020

class Day13(input: List<String>) {
    val startTime = input[0].toInt()
    val busses = input[1].split(",").mapNotNull { it.toIntOrNull() }

    data class Bus(val id: Long, val index: Long)
    val indexedBusses = input[1].split(",").mapIndexedNotNull { index, s ->
        val busId = s.toLongOrNull()
        if(busId != null) {
            Bus(busId, index.toLong())
        } else {
            null
        }
    }

    fun solvePart1(): Long {
        println("$startTime, $busses")
        val sortedArrivals = indexedBusses.map { bus ->
            val firstTime = ((startTime / bus.id) + 1) * bus.id
            println("${bus.id} - $firstTime")
            Pair(bus.id, firstTime)
        }.sortedBy { it.second }
        return sortedArrivals[0].first * (sortedArrivals[0].second - startTime)
    }




    fun solvePart3(): Long {
        for(timestamp in 0 until Long.MAX_VALUE step indexedBusses[0].id) {
            val outOfSyncBusses = indexedBusses.filter { bus ->
                (timestamp + bus.index) % bus.id != 0L
            }.count()
            if(outOfSyncBusses == 0){
                return timestamp
            }
        }
        TODO()
    }

    fun solvePart4(): Long {
        val slowestBus = indexedBusses.reduce{prev, cur -> if(cur.id > prev.id) { cur } else { prev } }
        for(timestamp in -slowestBus.index until Long.MAX_VALUE step slowestBus.id) {
            val outOfSyncBusses = indexedBusses.filter { bus ->
                (timestamp + bus.index) % bus.id != 0L
            }.count()
            if(outOfSyncBusses == 0){
                return timestamp
            }
        }
        TODO()
    }

    fun solvePart2(): Long {
        var step = 1L
        var matchingTimestamp = 1L
        busloop@ for(bus in indexedBusses) {
            for(i in matchingTimestamp until Long.MAX_VALUE step step) {
                println("Bus: $bus, i: $i, step: $step")
                if((i + bus.index) % bus.id == 0L) {
                    step *= bus.id
                    matchingTimestamp = i
                    println("Bus: $bus, matchingTimestamp: $matchingTimestamp, step: $step")
                    continue@busloop
                }
            }
        }
        return matchingTimestamp
    }
}