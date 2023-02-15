package Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var s: String? = ""

    while (s != null) {
        s = br.readLine()
        var sum = 0
        if (s != null) {
            s.split(" ").map { sum += it.toInt() }
            println(sum)
        }
    }
}