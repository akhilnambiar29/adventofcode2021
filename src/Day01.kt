fun main() {
    fun part1(input: List<String>): Int {

        return (0..input.size - 2)
            .count {
                input[it + 1].toInt() > input[it].toInt()
            }

    }

    fun part2(input: List<String>): Int {
        val intList = input.map { it.toInt() }
        return (0..input.size - 4).count {
            print(intList.subList(it, it + 3))
            println( intList.subList(it + 1, it + 4))
            intList.subList(it, it + 3).sum() < intList.subList(it + 1, it + 4).sum()
        }
    }

//    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    check(part2(testInput) == 5)
    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
