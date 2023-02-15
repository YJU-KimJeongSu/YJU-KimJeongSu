package Bronze

fun main() {
    val list = mutableListOf<Int>()
    repeat(30) {
        list.add(it + 1)
    }

    repeat(28) {
        list.remove(readln().toInt())
    }

    list.forEach {
        println(it)
    }
}