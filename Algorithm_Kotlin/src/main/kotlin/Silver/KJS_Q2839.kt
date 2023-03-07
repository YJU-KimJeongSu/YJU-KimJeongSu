package Silver

fun main() {
    val deliv = readln().toInt()
    var result = 0

    if (deliv % 5 == 0) { // 5 10 15 20 ...
        result = deliv / 5
    } else if (deliv % 5 == 1) { // 6 11 16 21 ...
        result = (deliv / 5) + 1
    } else if (deliv % 5 == 2) { // 7 12 17 22 ...
        result = if (deliv == 7) -1
        else (deliv / 5) + 2
    } else if (deliv % 5 == 3) { // 3 8 13 18 23 ...
        result = (deliv / 5) + 1
    } else if (deliv % 5 == 4) { // 4 9 14 19 24 ...
        result = if (deliv == 4) -1
        else (deliv / 5) + 2
    }

    println(result)
}