class Stack<T>(vararg items: T) {
    private val els = items.toMutableList()
    fun push(el: T) = els.add(el)
    fun peek(): T = els.last()
    fun pop(): T = els.removeAt(els.size - 1)
    fun isEmpty() = els.isEmpty()
    fun size() = els.size
    override fun toString() = "Stack[${els.joinToString()}]"
}

fun <T> stackOf(vararg els: T) = Stack(*els)

fun main() {
    val stack = stackOf(2, 4, 6, 8)

    stack.push(10)
    
    println(stack)
    println("peek(): ${stack.peek()}")
    println(stack)
    
    for (i in 1..stack.size()) {
        println("pop(): ${stack.pop()}")
        println(stack)
    }
}
