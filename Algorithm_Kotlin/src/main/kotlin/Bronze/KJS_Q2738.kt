package Bronze

fun main() {
    val size = readln().split(" ").map { it.toInt() }
    val list1 = mutableListOf<MutableList<Int>>()
    val list2 = mutableListOf<MutableList<Int>>()

    repeat(size[0]) {
        val n = it
        list1.add(mutableListOf())
        readln().split(" ").forEach {
            list1[n].add(it.toInt())
        }
    }

    repeat(size[0]) {
        val n = it
        list2.add(mutableListOf())
        readln().split(" ").forEach {
            list2[n].add(it.toInt())
        }
    }

    repeat(list1.size) {
        val n = it
        repeat(list1[it].size) {
            print("${list1[n][it] + list2[n][it]} ")
        }
        println()
    }
}