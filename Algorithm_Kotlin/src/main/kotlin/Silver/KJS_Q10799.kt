package Silver

import java.util.Stack

fun main() {
    val pipe = readln().replace("()", "R")
    val stack = Stack<Int>()
    var count = 0

    pipe.toCharArray().forEach {
        if (it == '(') stack.push(0)
        else if (it == 'R') count += stack.size
        else if (it == ')') {
            count++
            stack.pop()
        }
    }

    println(count)
}