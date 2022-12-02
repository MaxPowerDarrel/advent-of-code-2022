fun main() {
    fun part1(input: List<String>): Int {
        val inventory: List<List<Int>> = partitionInput(input)
        return inventory.maxOf { list -> list.reduce { acc, i -> acc + i } }
    }

    fun part2(input: List<String>): Int {
        val inventory: List<List<Int>> = partitionInput(input)
        return inventory.sortedByDescending { list -> list.reduce { acc, i -> acc + i } }
            .take(3)
            .sumOf { list -> list.sumOf { it } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun partitionInput(input: List<String>): List<List<Int>> {
    val inventory: MutableList<MutableList<Int>> = mutableListOf()
    var newList = true
    input.forEachIndexed { index, value ->
        if (newList) {
            inventory.add(mutableListOf())
            newList = false
        }
        if (input[index].isBlank()) {
            newList = true
        } else {
            inventory[inventory.size - 1].add(value.toInt())
        }
    }
    return inventory
}
