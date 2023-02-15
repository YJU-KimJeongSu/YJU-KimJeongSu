package Bronze

fun main() {
    val count:Int = readln().toInt()
    for (i in 1 .. count) {
        var sum:Int = 0
        readln().split(" ").forEach { sum += it.toInt() }
        println(sum)
    }
}