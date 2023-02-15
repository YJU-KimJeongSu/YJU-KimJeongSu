package Bronze

import kotlin.math.abs

fun main() {
    val s = readln().split(" ")
    println(abs(s[0].toLong() - s[1].toLong()))
}