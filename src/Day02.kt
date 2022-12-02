fun part1(input: List<String>): Int = inputToTokens(input).sum()
fun part2(input: List<String>): Int = inputToOutcomes(input).sum()

sealed class Token(private val tokenValue: Int) {
    abstract fun matchPoints(otherToken: Token): Int
    abstract fun generateCorrespondingToken(character: String): Token
    fun addPoints(otherToken: Token): Int = tokenValue + matchPoints(otherToken)
    companion object {
        fun createToken(character: String): Token = when(character) {
            "A", "X" -> Rock()
            "B", "Y" -> Paper()
            "C", "Z" -> Scissors()
            else -> throw IllegalArgumentException("Invalid option")
        }
    }
}

class Rock : Token(1) {
    override fun matchPoints(otherToken: Token): Int = when(otherToken) {
        is Rock -> 3
        is Paper -> 0
        is Scissors -> 6
    }

    override fun generateCorrespondingToken(character: String): Token = when(character) {
        "X" -> Scissors()
        "Y" -> Rock()
        "Z" -> Paper()
        else -> throw IllegalArgumentException("Invalid option")
    }
}

class Paper : Token(2) {
    override fun matchPoints(otherToken: Token): Int = when(otherToken) {
        is Rock -> 6
        is Paper -> 3
        is Scissors -> 0
    }

    override fun generateCorrespondingToken(character: String): Token = when(character) {
        "X" -> Rock()
        "Y" -> Paper()
        "Z" -> Scissors()
        else -> throw IllegalArgumentException("Invalid option")
    }
}

class Scissors : Token(3) {
    override fun matchPoints(otherToken: Token): Int = when(otherToken) {
        is Rock -> 0
        is Paper -> 6
        is Scissors -> 3
    }

    override fun generateCorrespondingToken(character: String): Token = when(character) {
        "X" -> Paper()
        "Y" -> Scissors()
        "Z" -> Rock()
        else -> throw IllegalArgumentException("Invalid option")
    }
}

fun inputToTokens(input: List<String>): List<Int> = input.map { it.split(" ") }
    .map { Token.createToken(it[0]) to Token.createToken(it[1]) }
    .map { it.second.addPoints(it.first) }

fun inputToOutcomes(input: List<String>): List<Int> = input.map { it.split(" ") }
    .map { Token.createToken(it[0]) to it[1] }
    .map { it.first to it.first.generateCorrespondingToken(it.second) }
    .map { it.second.addPoints(it.first) }

fun main() {
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
