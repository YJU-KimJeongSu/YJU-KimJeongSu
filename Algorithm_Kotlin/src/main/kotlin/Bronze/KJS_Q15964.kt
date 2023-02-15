package Bronze

fun main() {
    val input = readln().split(" ").map { it.toLong() }
    println((input[0] + input[1]) * (input[0] - input[1]))
}