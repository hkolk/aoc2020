package nl.hkolk.aoc2020

class Day8(val input: List<String>) {

    class Computer(input: List<String>) {

        enum class Status {
            HALT,
            RUNNING,
            EXIT,
            LOOP,
            SEGFAULT
        }
        enum class Operator {
            NOP,
            ACC,
            JMP
        }
        val program = input.map {
                val parts = it.split(" ")
                Pair(Operator.valueOf(parts[0].toUpperCase()), parts[1].trim().toInt())
            }.toMutableList()

        var accu: Long = 0
        var instPtr: Int = 0
        val seen = mutableSetOf<Int>()
        var step = 0

        fun run() : Status {
            for(i in 0 until 10000) {
                step++
                // if we re-run an instruction, we are looping
                if(seen.contains(instPtr)) {
                    return Status.LOOP
                }
                seen.add(instPtr)
                // if we execute 1 outside of the program size, it's a normal exit
                if(instPtr == program.size) return Status.EXIT

                // if we cannot fetch the instruction, it's a segfault
                val instruction = program.getOrNull(instPtr) ?: return Status.SEGFAULT
                when(instruction.first) {
                    Operator.NOP -> {
                        instPtr++
                    }
                    Operator.ACC -> {
                        accu += instruction.second
                        instPtr++
                    }
                    Operator.JMP -> {
                        instPtr += instruction.second
                    }
                }
            }
            return Status.RUNNING
        }

        fun print() {
            println("Accumulator: $accu")
            println("Instruction Pointer: $instPtr")
            println("Step: $step")
            for((i, inst) in program.withIndex()) {
                println("[$i] ${inst.first} ${inst.second}")
            }
        }

        fun mutate(i: Int) {
            program[i] = when(program[i].first) {
                Operator.JMP -> Pair(Operator.NOP, program[i].second)
                Operator.NOP -> Pair(Operator.JMP, program[i].second)
                Operator.ACC -> Pair(Operator.ACC, program[i].second)
            }

        }
    }

    fun solvePart1(): Long {
        val computer = Computer(input)
        when(computer.run()) {
            Computer.Status.LOOP -> return computer.accu
        }
        TODO()
    }
    fun solvePart2(): Long {
        for(i in input.indices) {
            val computer = Computer(input)
            computer.mutate(i)
            when(computer.run()) {
                Computer.Status.EXIT -> return computer.accu
            }
        }
        TODO()
    }
}