fun main() {

    fun validateIfMarked(
        pairs: List<List<Pair<Int, Boolean>>>,
        j: Int,
        k: Int,
        lowerRange: Int,
        higherRange: Int
    ): Boolean {
        val checkRow = pairs[j].all { it.second }
        val checkColumn = pairs
            .filterIndexed { index, _ -> index in (lowerRange..higherRange) }
            .map { it[k] }
            .all { it.second }
        return listOf(checkRow, checkColumn).any { it }
    }

    fun calculateUnmarkedBoardValues(
        mutableLists: List<List<Pair<Int, Boolean>>>, lowerRange: Int, higherRange: Int
    ): Int {
        return (lowerRange..higherRange)
            .sumOf { value ->
                mutableLists[value].filter { !it.second }.sumOf { it.first }
            }
    }

    fun part1(input: List<String>): Int {

        val numbersDrawn = input[0].split(",").map { it.toInt() }

        val row3d = read3dRow(input)

        for (numberDrawn in numbersDrawn) {
            for ((j, jValue) in row3d.withIndex()) {
                for ((k, kValue) in jValue.withIndex()) {
                    if (kValue.first == numberDrawn) {
                        println("Number Drawn: $numberDrawn $j $k")
                        row3d[j][k] = Pair(kValue.first, true)
                        val lowerRange = j / 5 * 5
                        val higherRange = lowerRange + 4
                        if (validateIfMarked(row3d, j, k, lowerRange, higherRange)) {
                            return calculateUnmarkedBoardValues(row3d, lowerRange, higherRange) * numberDrawn
                        }
                    }
                }
            }
        }

        return 0
    }

    fun getRemainingValueOfI(blackList: MutableList<Int>, size: Int): Int {
        return (0 until size).first { it !in blackList }
    }

    fun part2(input: List<String>): Int {

        val numbersDrawn = input[0].split(",").map { it.toInt() }
        val row3d = read3dRow(input)

        val blackList = mutableListOf<Int>()
        val numberDrawnList = mutableListOf<Int>()

        for (numberDrawn in numbersDrawn) {
            for ((j, jValue) in row3d.withIndex()) {
                for ((k, kValue) in jValue.withIndex()) {
                    if (j !in blackList && kValue.first == numberDrawn) {
                        row3d[j][k] = Pair(kValue.first, true)
                        val lowerRange = j / 5 * 5
                        val higherRange = lowerRange + 4
                        if (validateIfMarked(row3d, j, k, lowerRange, higherRange)) {
                            blackList.addAll(lowerRange..higherRange)
                            if (blackList.size == row3d.size){
                                return calculateUnmarkedBoardValues(row3d, lowerRange, higherRange) * numberDrawn
                            }

                        }
                    }
                }
            }
        }
        return 0
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    //check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    //   check(part2(testInput) == 5)
    val input = readInput("Day04")
    //println(part1(input))
    println("Part2: --" + part2(input))
}

fun read3dRow(input: List<String>): MutableList<MutableList<Pair<Int, Boolean>>> {
    val row2d: MutableList<MutableList<Pair<Int, Boolean>>> = mutableListOf()
    for (i in 2..input.size) {
        if ((i - 1) % 6 != 0) {
            val row = input[i].split(" ").filter { it.isNotEmpty() }.map { it.toInt() to false }.toMutableList()
            row2d.add(row)
        }
    }
    return row2d
}
