fun max(x: Int, y: Int): Int = if (x > y) x else y

fun eatASteak() = println("Yum yum...eating a steak!")

fun cookASteak() = println("Sizzle sizzle...cooking a steak!")

fun greeting(country: String) =
    when (country) {
        "China" -> println("你好")
        "Italy" -> println("Ciao")
        "Japan" -> println("こんにちは")
        "Russia" -> println("Здравствуйте")
        else -> println("Unknown greeting")
    } 

fun main() {
    println(max(50, 10))
    
    val countries = mutableListOf<String>("Australia", "Brazil", "Canada")
    for (country in countries) println(country)
    
    for (i in 0..10) print(i) 
    for (i in 0 until 5) print(i) 
    for (i in 2..10 step 2) print(i) 
    for (i in 5 downTo 0) print(i) 
    
    var steaksEaten = 0
    var steaksCooked = 0
      
    while (steaksEaten < 5) {
        eatASteak()
        steaksEaten++
    }

    do { 
        cookASteak()
        steaksCooked++
    } while (steaksCooked < steaksEaten)

    println(steaksEaten)
    println(steaksCooked)
    
    greeting("Italy")
    greeting("Russia")
}
