package Bronze

fun main() {
    var sum:Long = 0
    readln().split(" ").map { sum += it.toLong() }
    println(sum)
}