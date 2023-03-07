package Silver

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.NumberFormatException
import java.util.HashMap

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val nm = readln().split(" ").map { it.toInt() }
    val map:MutableMap<Int, String> = HashMap<Int, String>()
    val map2:MutableMap<String, Int> = HashMap<String, Int>()

    repeat(nm[0]) {
        val input = br.readLine()
        map[it + 1] = input
        map2[input] = it +1
    }

    repeat(nm[1]) {
        val input = br.readLine()
        if (isNumber(input)) bw.write("${map[input.toInt()]}\n")
        else bw.write("${map2[input]}\n")
    }

    bw.close()
}

fun isNumber(input:String):Boolean {
    return try {
        input.toInt()
        true
    } catch (e:NumberFormatException) {
        false
    }
}