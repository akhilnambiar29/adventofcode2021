import kotlin.math.abs

fun main() {

    fun parseInput(input: List<String>): List<Pair<String, String>> {
        return input.map {
            val (a, b) = it.split(" -> ")
            Pair(a, b)
        }
    }

    fun getAllValuesFromBoundaryValues(boundary: Pair<String, String>): List<Pair<Int, Int>> {
        val (a, b) = boundary.first.split(",").map { it.toInt() }
        val (c, d) = boundary.second.split(",").map { it.toInt() }
        if (a == c) {
            return if (b > d) {
                (d..b).map { Pair(a, it) }
            } else {
                (b..d).map { Pair(a, it) }
            }
        } else if (b == d) {
            return if (a > c) {
                (c..a).map { Pair(it, b) }
            } else {
                (a..c).map { Pair(it, b) }
            }
        }
        return emptyList()
    }

    fun getAllValuesFromBoundaryValuesAlsoDiagonal(boundary: Pair<String, String>): List<Pair<Int, Int>> {
        val (a, b) = boundary.first.split(",").map { it.toInt() }
        val (c, d) = boundary.second.split(",").map { it.toInt() }
        if (a == d && b == c) {
            var i = 0
            val listOfPairs = mutableListOf<Pair<Int, Int>>()
            while (i <= abs(a - b)) {
                if (a < b) {
                    listOfPairs.add(Pair(a + i, b - i))
                } else {
                    listOfPairs.add(Pair(a - i, b + i))
                }
                i++
            }
            return listOfPairs
        } else if (a == b && c == d) {
            return if (a > c) {
                (c..a).map { Pair(it, it) }
            } else {
                (a..c).map { Pair(it, it) }
            }
        } else if (abs(a - c) == abs(b - d)) {
            if (a > c && b > d) {
                return (0..abs(a - c)).map { Pair(c + it, d + it) }
            } else if (a < c && b > d) {
                return (0..abs(a - c)).map { Pair(a + it, b - it) }
            } else if (a < c && b < d) {
                return (0..abs(a - c)).map { Pair(a + it, b + it) }
            } else if (a > c && b < d) {
                return (0..abs(a - c)).map { Pair(c + it, d - it) }
            }
        } else if (a == c) {
            return if (b > d) {
                (d..b).map { Pair(a, it) }
            } else {
                (b..d).map { Pair(a, it) }
            }
        } else if (b == d) {
            return if (a > c) {
                (c..a).map { Pair(it, b) }
            } else {
                (a..c).map { Pair(it, b) }
            }
        }
        return emptyList()
    }

    fun part1(input: List<String>): Int {
        val inputParsed = parseInput(input)

        val allValues = inputParsed.flatMap { getAllValuesFromBoundaryValues(it) }
        val size = allValues.flatMap { it.toList() }.maxOf { it } + 1

        val inputList: MutableList<MutableList<Int>> = MutableList(size) { MutableList(size) { 0 } }

        allValues.map { inputList[it.second][it.first] += 1 }

        return inputList.flatten().count { it >= 2 }

    }


    fun part2(input: List<String>): Int {

        val inputParsed = parseInput(input)

        val allValues = inputParsed.flatMap { getAllValuesFromBoundaryValuesAlsoDiagonal(it) }
        val size = allValues.flatMap { it.toList() }.maxOf { it } + 1

        val inputList: MutableList<MutableList<Int>> = MutableList(size) { MutableList(size) { 0 } }

        allValues.map { inputList[it.second][it.first] += 1 }

        return inputList.flatten().count { it >= 2 }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    //check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    //   check(part2(testInput) == 5)
    val input = readInput("Day05")
    //println(part1(input))
    println("Part2: --" + part2(input))
}

