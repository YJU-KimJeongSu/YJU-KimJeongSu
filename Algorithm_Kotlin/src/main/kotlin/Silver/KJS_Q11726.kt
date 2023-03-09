package Silver

import java.util.Stack

fun main() {
    val n = readln().toInt()
    val stack = Stack<Long>()
    stack.push(1) // n = 1
    stack.push(2) // n = 2

    repeat(n-2) {
        val t = stack.pop()
        val sum = stack.peek() + t
        stack.push(t)
        stack.push(sum % 10007)
    }

    when (n) {
        1 -> println(1)
        2 -> println(2)
        else -> println(stack.peek())
    }
}