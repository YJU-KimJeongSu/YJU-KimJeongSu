package Silver

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val deque = ArrayDeque<Int>()

    repeat (readln().toInt()) {
        val st = StringTokenizer(br.readLine())

        when (st.nextToken()) {
            "push_front" -> deque.addFirst(st.nextToken().toInt())
            "push_back" -> deque.addLast(st.nextToken().toInt())
            "pop_front" -> bw.write(if (deque.size == 0) "-1\n" else "${deque.removeFirst()}\n")
            "pop_back" -> bw.write(if (deque.size == 0) "-1\n" else "${deque.removeLast()}\n")
            "size" -> bw.write("${deque.size}\n")
            "empty" -> bw.write(if (deque.size == 0) "1\n" else "0\n")
            "front" -> bw.write(if (deque.size == 0) "-1\n" else "${deque.first()}\n")
            "back" -> bw.write(if (deque.size == 0) "-1\n" else "${deque.last()}\n")
        }
    }

    bw.close()
}