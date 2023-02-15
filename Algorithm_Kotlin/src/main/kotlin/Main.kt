import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var s:String? = br.readLine()
    while (s != null) {
        println(s)
        s = br.readLine()
    }
}