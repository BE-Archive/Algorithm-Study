fun main() {
    loop@ while (true) {
        val line = readLine()!!.trim()
        if (line == "*") break

        for (d in 1 until line.length) {
            val seenPairs = HashSet<String>()
            for (i in 0 until line.length - d) {
                val pair = "${line[i]}${line[i + d]}" 
                if (!seenPairs.add(pair)) {
                    println("$line is NOT surprising.")
                    continue@loop
                }
            }
        }

        println("$line is surprising.")
    }
}
