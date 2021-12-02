fun main() {
    fun part1(input: List<String>): Int {

        val listOfPositionsAndValue = input.map {
            val (position, value) = it.split(" ")
            Pair(position, value.toInt())
        }

        val forwardMap = listOfPositionsAndValue.map { it }.groupBy { it.first == "forward" }
        val horizontalPosition = forwardMap[true]?.sumOf { it.second }!!
        val (up, down) = forwardMap[false]?.partition { it.first == "up" }!!
        return (down.sumOf { it.second } - up.sumOf { it.second }) * horizontalPosition
    }

    fun part2(input: List<String>): Int {

        val listOfPositionsAndValue = input.map {
            val (position, value) = it.split(" ")
            Pair(position, value.toInt())
        }

        var (horizontal, depth, aim) = arrayOf(0, 0, 0)
        for ((position, value) in listOfPositionsAndValue) {
            when (position) {
                "up" -> aim -= value
                "down" -> aim += value
                "forward" -> {
                    horizontal += value
                    depth += (aim * value)
                }
            }
        }

        return horizontal * depth

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    //check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    //   check(part2(testInput) == 5)
    val input = readInput("Day02")
    //println(part1(input))
    println(part2(input))
}
