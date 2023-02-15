package Bronze

fun main() {
    println(factorial(readln().toInt()))
}

fun factorial(n: Int): Int {
    return if (n > 1) {
        n * factorial(n-1)
    } else {
        1
    }
}