package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException

class Day18(val input: List<String>) {

    lateinit var precedence: Map<String, Int>

    fun solveExpression(input: String): Long {
        val tokens = input.split(" ")
        var operator = "+"
        var result = 0L
        for(token in tokens) {
            when(token) {
                "+", "-", "*", "/" -> operator = token
                else -> when(operator) {
                    "+" -> result += token.toLong()
                    "-" -> result -= token.toLong()
                    "*" -> result *= token.toLong()
                    "/" -> result /= token.toLong()
                }
            }
        }
        //println("expression $input became $result")
        return result
    }

    fun solveExpression2(input: String): Long {
        val tokens = input.split(" ")
        val rpn = mutableListOf<String>()
        val operatorStack = ArrayDeque<String>()

        for(token in tokens) {
            when {
                token.toLongOrNull() != null -> rpn.add(token)
                "+-*/".contains(token) -> {
                    while(operatorStack.isNotEmpty()) {
                        if(precedence[operatorStack.last()]!! >= precedence[token]!!) {
                            rpn.add(operatorStack.removeLast())
                        } else {
                            break
                        }
                    }
                    operatorStack.add(token)
                }
                else -> throw IllegalArgumentException("Unknown token: $token")
            }
        }
        while(operatorStack.isNotEmpty()) {
            rpn.add(operatorStack.removeLast())
        }
        // result is now RPN
        val evalStack = ArrayDeque<Long>()
        for(token in rpn) {
            when {
                token.toLongOrNull() != null -> evalStack.add(token.toLong())
                else -> when(token) {
                    "+" -> evalStack.add(evalStack.removeLast() + evalStack.removeLast())
                    "-" -> evalStack.add(evalStack.removeLast() - evalStack.removeLast())
                    "*" -> evalStack.add(evalStack.removeLast() * evalStack.removeLast())
                    "/" -> evalStack.add(evalStack.removeLast() / evalStack.removeLast())
                }
            }
        }
        //println("input $input, RPN: $rpn, result: $evalStack")
        return evalStack.first()

    }

    fun solve(input: String): Long {
        val regex = Regex("""\(([^\(\)]*)\)""")
        var simple = false
        var simpleExpression = input
        while(!simple) {
            val result = regex.find(simpleExpression)
            if (result != null) {
                //println(result.value)
                //println(result.groupValues)
                simpleExpression = simpleExpression.replace(result.value, solveExpression2(result.groupValues[1]).toString())
            } else {
                simple = true
            }
        }
        println("$input became $simpleExpression")
        return solveExpression2(simpleExpression)
    }

    fun solvePart1():Long {
        precedence = mapOf("+" to 0, "-" to 0, "*" to 0, "/" to 0)
        return input.map { solve(it) }.sum()
    }
    fun solvePart2():Long {
        precedence = mapOf("+" to 1, "-" to 0, "*" to 0, "/" to 0)
        return input.map { solve(it) }.sum()
    }
}