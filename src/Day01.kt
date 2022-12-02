fun main() {
    fun part1(input: List<String>): Int = partitionInput(input)[0]
    fun part2(input: List<String>): Int = partitionInput(input)
        .take(3)
        .sumOf { it }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

/**
 * returns a list of ints sorted descending.  The ints are added together until a blank line is found, that sum is then inserted into the list
 * and a new tally is begun.
 */
private fun partitionInput(input: List<String>): List<Int> {
    tailrec fun accumulator(index: Int, currentTotal: Int, listOfTotals: List<Int>): List<Int> =
        if (index == input.size) listOfTotals + listOf(currentTotal)
        else if (input[index].isBlank()) accumulator(index + 1, 0, listOfTotals + currentTotal)
        else accumulator(index + 1, currentTotal + input[index].toInt(), listOfTotals)
    return accumulator(0, 0, emptyList()).sortedByDescending { it }
}
