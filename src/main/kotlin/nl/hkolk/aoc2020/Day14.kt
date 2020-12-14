package nl.hkolk.aoc2020

class Day14(val input: List<String>) {

    fun applyBitmask(mask: String, input: Long): Long {
        // apply 1
        val oneMask = mask.replace('X', '0').toLong(2)
        val oneVal = input.or(oneMask)
        //println("1: Mask: $oneMask, val: $oneVal")


        // apply 0
        val zeroMask = mask.map { char ->
            when(char) {
                '0' -> '1'
                '1' -> '0'
                'X' -> '0'
                else -> IllegalArgumentException("I do not support $char in bitmask")
            } }.joinToString("").toLong(2)
        val zeroVal = input.and(zeroMask)
        //println("1: Mask: $zeroMask, val: $zeroVal")

        return oneVal - zeroVal
    }

    fun solvePart1(): Long {
        var mask = ""
        val memory = mutableMapOf<Int, Long> ()
        for(line in input) {
            val parts = line.split("=").map { it.trim() }
            if(parts[0] == "mask") {
                mask = parts[1]
            } else if(parts[0].startsWith("mem")) {
                val register = parts[0].filter { it in '0'..'9' }.toInt()
                memory[register] = applyBitmask(mask, parts[1].toLong())
                //println("Register $register now contains ${memory[register]}")
            } else {
                throw IllegalStateException("Cannot parse line $line")
            }
        }
        return memory.values.sum()
    }
    fun allMutations(remainingMask: String, accu: String=""): List<Long> {
        return if(remainingMask.isNullOrEmpty()) {
            listOf(accu.toLong(2))
        } else {
            when(remainingMask[0]) {
                '1', '0' -> {
                    allMutations(remainingMask.drop(1), accu + remainingMask[0])
                }
                'X' -> {
                    allMutations(remainingMask.drop(1), accu + '0') + allMutations(remainingMask.drop(1), accu + '1')
                }
                else -> throw IllegalStateException("Found mutation bit that I don't know: ${remainingMask[0]}")
            }
        }
    }

    fun solvePart2(): Long {
        var mask = ""
        val memory = mutableMapOf<Long, Long> ()
        for(line in input) {
            val parts = line.split("=").map { it.trim() }
            if(parts[0] == "mask") {
                mask = parts[1]
            } else if(parts[0].startsWith("mem")) {
                val register = parts[0].filter { it in '0'..'9' }.toLong()

                //println("Int:  ${register.toString(2).padStart(36, '0')}")
                //println("Mask: $mask")
                val registerMask = register.toString(2).padStart(36, '0').zip(mask) { reg, mask ->
                    if(mask == '0') {
                        reg
                    } else {
                        mask
                    }
                }.joinToString("")
                //println("Res:  $registerMask")
                allMutations(registerMask).forEach{memory[it] = parts[1].toLong()}
                //memory.forEach { key, value -> println("Key: $key, value: $value")}

            } else {
                throw IllegalStateException("Cannot parse line $line")
            }
        }
        return memory.values.sum()
    }


}