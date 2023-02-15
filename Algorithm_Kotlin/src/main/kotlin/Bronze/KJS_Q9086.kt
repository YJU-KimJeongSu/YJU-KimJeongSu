package Bronze

fun main() {
    repeat(readln().toInt()) {
        val s = readln()
        println("${s.first()}${s.last()}")
    }
}