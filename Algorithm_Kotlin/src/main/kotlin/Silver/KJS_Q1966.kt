package Silver

import java.util.LinkedList
import java.util.Queue

data class Document(var initIndex:Int, var priority:Int)

fun main() {
    val testCase = readln().toInt()

    repeat(testCase) {
        val input = readln().split(" ").map { it.toInt() } // [0] : 문서 수, [1] : 필요한 문서의 index
        val queue:Queue<Document> = LinkedList() // 문서 출력 큐
        val priority = LinkedList<Int>() // 문서 우선순위 확인용. 출력할때마다 큐 돌면 비효율적이니 우선순위만 담는 큐 새로 생성

        var i = 0
        readln().split(" ").forEach { queue.add(Document(i++, it.toInt())) }
        queue.forEach { priority.add(it.priority) }
        priority.sort()
        priority.reverse()
        // priority의 가장 앞 값이랑 현재 출력할 문서의 우선순위가 같으면 가장 높은 우선순위
        // 이 위로 정상 작동 확인 완료

        // 큐 돌면서 인쇄 후 내가 원하는 출력물인지 확인
        var j = 1 // 반복 횟수
        while (true) {
            val t = queue.poll()

            // 우선순위가 가장 높은 문서면 출력
            if (priority[0] == t.priority) {
                // 출력하면 내가 원하던 문서가 맞나 확인
                if (t.initIndex == input[1]) { // 맞으면 반복 횟수 출력하고 테스트 케이스 종료
                    println(j)
                    break
                } else { // 아니면 다음 문서 출력
                    j++
                    priority.remove()
                }
            } else { // 우선순위가 가장 높은 문서가 아니면 큐의 맨 뒤로
                queue.add(t)
            }
        }

    }
}