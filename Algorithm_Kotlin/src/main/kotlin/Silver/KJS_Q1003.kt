package Silver

import java.util.Deque
import java.util.LinkedList

//fun Pair<Int, Int>.sum(t: Pair<Int, Int>):Pair<Int, Int> {
//    first += t.first
//}
// Pair의 first, second가 val로 선언되어 있어서 변경

fun sumPair(a:Pair<Int, Int>, b:Pair<Int, Int>):Pair<Int, Int> = Pair<Int, Int>(a.first + b.first, a.second + b.second)

fun main() {
    var deque:Deque<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
    val testCase = readln().toInt()

    repeat(testCase) {
        val n = readln().toInt()

        repeat(n+1) {
            if (it == 0) deque.addFirst(Pair(1, 0))
            else if (it == 1) deque.addFirst(Pair(0, 1))
            else {
                var temp:Pair<Int, Int> = Pair<Int, Int>(0, 0)
                deque.forEach { it1 ->
                    temp = sumPair(temp, it1)
                }
                deque.removeLast()
                deque.addFirst(temp)
            }
        }

        println("${deque.peekFirst().first} ${deque.peekFirst().second}")
        deque = LinkedList<Pair<Int, Int>>() // 비우기 대신
    }
}