package nl.hkolk.aoc2020

class Day2(input: List<String>) {

    data class PasswordLine(val first: Int, val second: Int, val char: Char, val password: String) {
        companion object {
            fun fromString(line: String) : PasswordLine {
                val parts = line.splitIgnoreEmpty(" ", ":", "-",)
                return PasswordLine(
                    first = parts[0].toInt(),
                    second = parts[1].toInt(),
                    char = parts[2][0],
                    password = parts[3].trim()
                )
            }
        }

        fun isValidPart1() : Boolean {
            val charCount = password.filter { it == char }.count()
            return charCount >= first && charCount <= second
        }
        fun isValidPart2() : Boolean {
            return (password[first-1] == char) xor (password[second-1] == char)
        }
    }
    val passwords = input.map { PasswordLine.fromString(it) }



    fun solvePart1(): Int = passwords.filter { it.isValidPart1() }.count()
    fun solvePart2(): Int = passwords.filter { it.isValidPart2() }.count()

}