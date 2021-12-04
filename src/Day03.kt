fun main() {
    fun part1(input: List<String>): Int {
        val a = input.flatMap { value ->
            value
                .mapIndexed { index, c ->
                    Pair(index, c)
                }
                .filter { it.second == '0' }
                .map { it.first }
        }.groupingBy { it }
            .eachCount()
            .toSortedMap()

        val b = a.values.map {
            if (it < input.size / 2) {
                '1'
            } else {
                '0'
            }
        }.joinToString("")

        val c = b.map {
            if (it == '1') {
                '0'
            } else {
                '1'
            }
        }.joinToString("")

        return Integer.parseInt(b, 2) * Integer.parseInt(c, 2)

    }

    fun part2(input: List<String>): Int {

        fun getDominantBit(value: List<String>, index: Int): Char {
            val countDominantBit = value.map {
                it[index]
            }.count { it == '0' }
            return if (countDominantBit > value.size / 2) '0' else '1'
        }

        fun getListWithDominantBit(value: List<String>, index: Int, dominantBit: Char): List<String> {
            return value.filter {
                it[index] == dominantBit
            }
        }

        var oxygenGeneratorIndex = 0
        var dominantBitListOxygenGenerator = input
        while (oxygenGeneratorIndex < input[0].length) {
            val dominantBit = getDominantBit(dominantBitListOxygenGenerator, oxygenGeneratorIndex)
            dominantBitListOxygenGenerator =
                getListWithDominantBit(dominantBitListOxygenGenerator, oxygenGeneratorIndex, dominantBit)
            if (dominantBitListOxygenGenerator.size == 1) {
                break
            }
            oxygenGeneratorIndex++
        }

        var co2ScrubberIndex = 0
        var dominantBitListCo2Scrubber = input
        while (co2ScrubberIndex < input[0].length) {
            val dominantBit = if (getDominantBit(dominantBitListCo2Scrubber, co2ScrubberIndex) == '1') '0' else '1'
            dominantBitListCo2Scrubber =
                getListWithDominantBit(dominantBitListCo2Scrubber, co2ScrubberIndex, dominantBit)
            if (dominantBitListCo2Scrubber.size == 1) {
                break
            }
            co2ScrubberIndex++
        }

        return Integer.parseInt(
            dominantBitListOxygenGenerator.first(),
            2
        ) * Integer.parseInt(dominantBitListCo2Scrubber.first(), 2)

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    //   check(part2(testInput) == 5)
    val input = readInput("Day03")
    //println(part1(input))
    println("Part2: --" + part2(input))
}
