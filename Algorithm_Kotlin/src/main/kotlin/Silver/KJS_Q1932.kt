package Silver

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val n = readln().toInt()
    val arr = Array(n) { IntArray(n) }

    repeat(n) {
        arr[it] = bf.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 1 until n) {
        repeat(arr[i].size) {
            if (it == 0) arr[i][it] = arr[i-1][it] + arr[i][it]
            else if (it == arr[i].size - 1) arr[i][it] = arr[i-1][it-1] + arr[i][it]
            else {
                arr[i][it] = max(arr[i-1][it-1], arr[i-1][it]) + arr[i][it]
            }
        }
    }

    println(arr[n-1].max())
}