fun max(x: Int, y: Int): Int {
    var result: Int
    if (x > y) {
        result = x
    } else {
        result = y
    }
    return result
}

fun printMessage(message: String): Unit { // Unit is equivalent to void in Java
    println(message)
}

fun main() {
    println(max(50, 10)) // 50
}
