package Bronze

fun main() {
    val s1 = readln().split(" ").map { it.toInt() }
    val s2 = readln().split(" ").map { it.toInt() }
    repeat(s1[0]) {
        if (s2[it] < s1[1]) print("${s2[it]} ")
    }
}