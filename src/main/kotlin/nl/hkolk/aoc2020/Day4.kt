package nl.hkolk.aoc2020

class Day4(val input: List<String>) {

    data class Passport(
        var byr: String? = null,
        var iyr: String? = null,
        var eyr: String? = null,
        var hgt: String? = null,
        var hcl: String? = null,
        var ecl: String? = null,
        var pid: String? = null,
        var cid: String? = null,
    ) {
        companion object {
            fun fromString(input: String) : Passport {
                val newPassport = Passport()
                input.splitIgnoreEmpty("\n", " ").map{ it.trim() }.forEach {
                    val keyval = it.splitIgnoreEmpty(":").map { it.trim() }
                    when(keyval[0]) {
                        "byr" -> newPassport.byr = keyval[1]
                        "iyr" -> newPassport.iyr = keyval[1]
                        "eyr" -> newPassport.eyr = keyval[1]
                        "hgt" -> newPassport.hgt = keyval[1]
                        "hcl" -> newPassport.hcl = keyval[1]
                        "ecl" -> newPassport.ecl = keyval[1]
                        "pid" -> newPassport.pid = keyval[1]
                        "cid" -> newPassport.cid = keyval[1]
                        else ->  {
                            throw IllegalArgumentException("Unknow field: $it")
                        }
                    }
                }
                return newPassport
            }
        }

        fun isValid(): Boolean {
            return  byr != null &&
                    iyr != null &&
                    eyr != null &&
                    hgt != null &&
                    hcl != null &&
                    ecl != null &&
                    pid != null
        }

        fun isExtendedValid() : Boolean {
            if(byr == null ||
                    iyr == null ||
                    eyr == null ||
                    hgt == null ||
                    hcl == null ||
                    ecl == null ||
                    pid == null
            ) {
                return false
            } else {
                if (byr!!.toInt() !in 1920..2002) return false
                if (iyr!!.toInt() !in 2010..2020) return false
                if (eyr!!.toInt() !in 2020..2030) return false
                when (hgt!!.takeLast(2)) {
                    "in" -> {
                        if (hgt!!.takeWhile { it in '0'..'9' }.toInt() !in 59..76) return false
                    }
                    "cm" -> {
                        if (hgt!!.takeWhile { it in '0'..'9' }.toInt() !in 150..193) return false
                    }
                    else -> {
                        return false;
                    }
                }
            }
            if(!hcl!!.matches(Regex("#[0-9a-z]{6}"))) return false

            val validColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            if(ecl!! !in validColors) return false;

            if(!pid!!.matches(Regex("[0-9]{9}"))) return false

            return true;
        }
    }
    val passports = mutableListOf<Passport>()
    init {
        parseInput()
    }

    fun parseInput() {
        var accu = ""
        for(line in input) {
            if(line.isEmpty()) {
                passports.add(Passport.fromString(accu))
                accu = ""

            } else {
                accu += "$line "
            }
        }
        if(accu.isNotEmpty()) {
            passports.add(Passport.fromString(accu))
        }
    }
    fun solvePart1(): Int = passports.filter {it.isValid()}.count()
    fun solvePart2(): Int = passports.filter {it.isExtendedValid()}.count()
}