package Bronze

fun main() {
    val s = readln().toCharArray().map {
        if (it > 'Z') it - 32
        else it + 32
    }

    s.forEach { print(it) }
}