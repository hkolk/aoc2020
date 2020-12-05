package nl.hkolk.aoc2020

class Day5(input: List<String>) {

    data class BoardingPass(val row: Int, val column: Int, val seatid: Int)
    val passes = input.map { parseBoardingPass(it)}

    fun parseBoardingPass(line: String):BoardingPass {
        val row = line.substring(0, 7).replace("F", "0").replace("B", "1").toInt(2)
        val column = line.substring(7, 10).replace("L", "0").replace("R", "1").toInt(2)
        val seatId = row * 8 + column;

        //println("Row: $row, Column: $column, seat ID: $seatId")
        return BoardingPass(row, column, seatId)
    }

    fun solvePart1() : Int = passes.map { it.seatid }.maxOrNull()!!
    fun solvePart2() : Int {
        val unfilledRow = passes.groupBy { it.row }.filter { it.value.size == 7 }.values.first()
        val unfilledColumn = (0..7).filter { !unfilledRow.map { it.column }.contains(it) }.first()
        return unfilledRow.first().row * 8 + unfilledColumn;
    }
}