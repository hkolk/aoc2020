package nl.hkolk.aoc2020

fun CharSequence.splitIgnoreEmpty(vararg delimiters: String): List<String> {
    return this.split(*delimiters).filter {
        it.isNotEmpty()
    }
}

fun List<String>.splitBy(func: (String) -> Boolean): List<List<String>> {
    val ret = mutableListOf<List<String>>()
    var collect = mutableListOf<String>()
    for(line in this) {
        if(func(line)) {
            ret.add(collect)
            collect = mutableListOf()
        } else {
            collect.add(line)
        }
    }
    if(collect.isNotEmpty()) ret.add(collect)
    return ret
}