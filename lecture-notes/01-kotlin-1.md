# **Kotlin 1**

## Before We Begin
In the following code examples, I will be comparing **Java** code to its equivalent **Kotlin** code. If you are unfamiliar with **Java**, its syntax and semantics are similar to **C#**.

## Overview
**Kotlin** is a statically typed programming language designed & developed by **JetBrains**. **Kotlin** targets the **JVM (Java Virtual Machine)**, but can also compile to **JavaScript** & native code via the **LLVM (Low-Level Virtual Machine)**. **Kotlin** is designed to be concise, safe, pragmatic & interoperable. This means that **Kotlin** can be used almost everywhere **Java** is used. **Kotlin** works will all existing **Java** libraries/frameworks. 

## Static Typing
The type of every expression in a program is known at **compile time**. The compiler can validate what properties & methods you are trying to access exist on the objects you are using. **Kotlin** does not require you to explicitly specify every type in a program like in **Java**. In most cases, the type can be determined using **type inference**, allowing the type declaration to be omitted.

### Benefits
* Method calls are faster as you do not need to figure out which ones need to be called at **runtime**.
* The compiler verifies the correctness of a program resulting in fewer chances of crashing at **runtime**.
* Easier to with unfamiliar code as you know what kind of objects you are working with.

## Variables
In **Java**, a variable declaration starts with a type. In **Kotlin**, a variable declaration starts with a keyword & optionally a type after the variable name.

```kotlin
// Java
String someString = "Hello, World!";

// Kotlin
var someString: String = "Hello, World!" // or
var someString = "Hello, World!"; // Semi-colons are optional
```

### Keywords
There are two keywords used when declaring a variable:
* `var` - A mutable reference & can be reassigned after it has been initialised. It corresponds to a regular variable in **Java**.
* `val` - An immutable reference & can not be reassigned after it has been initialised. It corresponds to a `final` variable in **Java**.

```kotlin
fun main() {
    var a: String = "Hello, World!"
    println(a) // Hello, World!
    
    val b: Int = 1
    println(b) // 1
    
    val c = 3 // Ommitting the type
    println(c) // 3
    
    var d: Int 
    println(d) // What happens when you execute this code? 
}
```

**Kotlin** does not enforce immutability, but is recommended that `val` is used wherever possible. Also, we will discuss other keywords when we start **Android** development.

### Null Safety
**Kotlin** attempts to achieve a higher level of safety than in **Java**. Running on the **JVM** already provides a lot of safety, i.e., memory safety, preventing buffer overflows & other problems caused by dynamically allocated memory. **Kotlin** ensures the type safety of your programs meaning errors can be prevented by checks at compile time & not failing at runtime. The type system tracks nullable & non-nullable values, & forbids operations that can lead to a `NullPointerException` at runtime. Nullable values are marked with a `?` after the type.

```kotlin
var notNullable: String = "Hello, World!"
notNullable = null // What happens when you execute this code?

var nullable: String? = "Hello, World!"
nullable = null
```

## Functions
A declaration starts with the `fun` keyword followed by the name & list of optional parameters. Optionally, the list is followed by the return type, separated by a colon.

```kotlin
fun max(x: Int, y: Int): Int { // Returns an integer
    var result: Int

    if (x > y) {
        result = x
    } else {
        result = y
    }

    return result
}

fun printMessage(someString: String): Unit { // Unit is equivalent to void in Java
    println(someString)
}

fun main() {
    println(max(50, 10)) // 50
    printMessage("Hello, World!") // Hello, World!
}
```

## Control Structures

### Statements and Expressions
A statement is a top-level element in its enclosing block & does not have its own value. An expression has its own value which can be used as part of another expression. Most control structures except loops (for, while & do-while) can be expressions.

```kotlin
// if expression
fun max(x: Int, y: Int): Int = if (x > y) x else y 

fun main() {
    println(max(50, 10)) // 50
}
```

The ternary expression does not exist in **Kotlin**. However, the `?:` (elvis) operator does. 

### For Loop
The `for` loop is equivalent to a **Java** `for-each` loop. The most common application of this loop is iterating over an array or collection.

```kotlin
val countries: MutableList<String> = mutableListOf("Australia", "Brazil", "Canada")

for (country in countries) {
    println(country) // Australia
                     // Brazil
                     // Canada
}

// or 

for (country in countries) println(country)
```

### Ranges
In **Kotlin**, there is no regular **Java** `for` loop. Instead, **Kotlin** uses ranges which is an interval between two values representing a start & end. 

```kotlin
// Iterates over a range starting from 0 up to 10 (inclusive)
for (i in 0..10) print(i) 

// Iterates over a range starting from 0 up to 5 (exclusive)
for (i in 0 until 5) print(i) 

// Iterates over a range starting from 2 up to 10 (inclusive) with a custom increment step
for (i in 2..10 step 2) print(i) 

// Iterates over a range starting from 5 down to 0 (inclusive)
for (i in 5 downTo 0) print(i) 
```

### While/Do-While Loop
The `while` & `do-while` loop work exactly the same as in **Java**.

```kotlin
fun eatASteak() = println("Yum yum...eating a steak!")

fun cookASteak() = println("Sizzle sizzle...cooking a steak!")

var steaksEaten = 0
var steaksCooked = 0
    
while (steaksEaten < 5) { // The body is executed while the condition is true
    eatASteak()
    steaksEaten++
}
  
do { 
    cookASteak()
    steaksCooked++
} while (steaksCooked < steaksEaten) // The body is executed for the first time unconditionally. After 
                                     // that, it is executed while the condition is true

println(steaksEaten) // 5
println(steaksCooked) // 5
```

### When
The `when` construct can be thought of as the `switch` construct in **Java**.

```kotlin
// when expression
fun greeting(country: String) = 
    when (country) {
        "China" -> println("你好")
        "Italy" -> println("Ciao")
        "Japan" -> println("こんにちは")
        "Russia" -> println("Здравствуйте")
        else -> println("Unknown greeting")
    } 

fun main() {
    greeting("Italy") // Ciao
    greeting("Canada") // Unknown greeting
}
```

## Collections

### List
Lists can be either mutable or read-only. For list creation, use the standard library functions `mutableListOf()` & `listOf()`. To prevent unwanted modifications, obtain read-only views of mutable lists by casting them to `List`.

```kotlin
val progLangs: MutableList<String> = mutableListOf("C#", "Go", "Java")
val readOnlyProgLangs: List<String> = progLangs

fun addProgLang(progLang: String) {
    progLangs.add(progLang)
}

fun getProgLanguages(): List<String> {
    return readOnlyProgLangs
}

fun main() {
    addProgLang("MATLAB")
    println("Total programming languages: ${getProgLanguages().size}") // Total programming languages: 4
    for (progLang in getProgLanguages()) {
        println(progLang) // C#
                          // Go
                          // Java
                          // MATLAB
    }
}
```

### Map
Collection of key/value pairs where each key is unique & is used to retrieve the corresponding value. For map creation, use the standard library functions `mutableMapOf()` & `mapOf()`. To prevent unwanted modifications, obtain read-only views of mutable maps by casting them to `Map`.

```kotlin
const val transactionFee: Int = 15
val bankAccounts: MutableMap<String, Int> = mutableMapOf("YouMoney" to 100, "Personal OnCall" to 100, "Rapid Save" to 100)
val bankReport: Map<String, Int> = bankAccounts

fun addTransactionFee(accountName: String) {
    if (bankAccounts.containsKey(accountName)) {
        println("Updating $accountName...")
        bankAccounts[accountName] = bankAccounts.getValue(accountName) - transactionFee
    } else {
        println("Error: Trying to add transaction fee to a non-existing account - $accountName")
    } 
}

fun bankAccountReport() {
    println("Bank account report:")
    for ((k, v) in bankReport) {
        println("$k - $$v")
    }
}

fun main() {
    bankAccountReport() // Bank account report:
                        // YouMoney - $100
                        // Personal OnCall - $100
                        // Rapid Save - $100
    addTransactionFee("Rapid Save") // Updating Rapid Save...
    addTransactionFee("Rapid Save") // Updating Rapid Save...
    addTransactionFee("Term Deposit") // Error: Trying to add transaction fee to a non-existing account - Term Deposit
    bankAccountReport() // Bank account report:
                        // YouMoney - $10
                        // Personal OnCall - $100
                        // Rapid Save - $70
}
```

### Set
An unordered collection that does not support duplicates. For set creation, use the standard library functions `mutableSetOf()` & `setOf()`. To prevent unwanted modifications, obtain read-only views of mutable sets by casting them to `Set`.

```kotlin
val microsoftCEOs: MutableSet<String> = mutableSetOf("Bill Gates", "Steve Ballmer", "Satya Nadella")
val newCEO: String = "John Doe"
val existingCEO: String = "Steve Ballmer"

fun addCEO(uniqueCEODesc: String): Boolean {                                                       
    return microsoftCEOs.add(uniqueCEODesc)
}

fun getStatusLog(isAdded: Boolean): String {                                                       
    return if (isAdded) "was successfully added" else "already exists"
}

fun main() {  
    println("$newCEO ${getStatusLog(addCEO(newCEO))}") // John Doe was successfully added
    println("$existingCEO ${getStatusLog(addCEO(existingCEO))}") // Steve Ballmer already exists
}
```
