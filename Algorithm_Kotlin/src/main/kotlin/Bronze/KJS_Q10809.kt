package Bronze

fun main() {
    val input = readln().toCharArray()
    val arr = CharArray(26, { 'a' + it })
    val list = mutableListOf<Int>()

    arr.forEach { list.add(input.indexOf(it)) }
    list.forEach { print("$it ") }
}