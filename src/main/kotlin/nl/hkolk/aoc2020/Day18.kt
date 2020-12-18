package nl.hkolk.aoc2020

import java.lang.IllegalArgumentException

class Day18(val input: List<String>) {

    lateinit var precedence: Map<String, Int>

    fun solveExpression(input: String): Long {
        val tokens = input.replace("(", " ( ").replace(")", " ) ").split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        println(tokens)
        val rpn = mutableListOf<String>()
        val operatorStack = ArrayDeque<String>()

        for(token in tokens) {
            when {
                token.toLongOrNull() != null -> rpn.add(token)
                "+-*/".contains(token) -> {
                    while(operatorStack.isNotEmpty()) {
                        if(operatorStack.last() != "(" && precedence[operatorStack.last()]!! >= precedence[token]!!) {
                            rpn.add(operatorStack.removeLast())
                        } else {
                            break
                        }
                    }
                    operatorStack.add(token)
                }
                "(" == token -> {
                    operatorStack.add(token)
                }
                ")" == token -> {
                    while(operatorStack.last() != "(") {
                        rpn.add(operatorStack.removeLast())
                    }
                    if(operatorStack.last() == "(") {
                        operatorStack.removeLast()
                    }
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

    fun solvePart1():Long {
        precedence = mapOf("+" to 0, "-" to 0, "*" to 0, "/" to 0)
        return input.map { solveExpression(it) }.sum()
    }
    fun solvePart2():Long {
        precedence = mapOf("+" to 1, "-" to 0, "*" to 0, "/" to 0)
        return input.map { solveExpression(it) }.sum()
    }
}