enum class State {
    GREEN, RED, AMBER
}

enum class Color(val rgb: Int) { // Each value must pass an argument 
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    fun containsGreen() = (rgb and 0x00FF00 != 0)
}

fun main() {
    val state = State.GREEN
    val trafficLights = when (state) {
        State.GREEN -> "Go"
        State.RED -> "Stop"
        State.AMBER -> "Slow down"
    }
    println(trafficLights)
    
    val green = Color.GREEN
    println(green)
    println(green.containsGreen())
    println(Color.BLUE.containsGreen())
}
