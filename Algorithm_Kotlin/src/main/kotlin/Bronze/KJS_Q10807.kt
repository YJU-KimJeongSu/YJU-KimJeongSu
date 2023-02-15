package Bronze

fun main() {
    val n = readln().toInt()
    val input = readln().split(" ").map { it.toInt() }
    val v = readln().toInt()
    var count = 0

    input.forEach {
        if (it == v) count++
    }

    println(count)
}