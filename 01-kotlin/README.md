# **Kotlin**

The following code snippets are available [here](https://github.com/otago-polytechnic-bit-courses/IN721-mobile-app-dev/tree/master/01-kotlin/kotlin-samples).

### What is **Kotlin**?
**Kotlin** is a statically typed programming language designed & developed by JetBrains. **Kotlin** targets the JVM (Java Virtual Machine), but can also compile to **Java**Script & native code via the LLVM (Low-Level Virtual Machine). **Kotlin** is designed to be concise, safe, pragmatic & interoperable. **Kotlin** can be used almost everywhere **Java** is used, i.e., server-side development, Android mobile development, etc. **Kotlin** works seamlessly with all existing **Java** libraries/frameworks & performs at the same level as **Java**.

### What is static type checking?
Static type checking means the type of every expression in a program is known at compile time. Also, the compiler can validate that the properties & methods you are trying to access exist on the objects you are using. In contrast to **Java**, **Kotlin** does not require you to explicitly specify every type in a program. In most cases, the type can be determined using type inference, allowing the type declaration to be omitted.

### What are some benefits of static typing?
* Performance - method calls are faster as there is no need to figure out which ones need to be called at runtime.
* Reliability - the compiler verifies the correctness of a program meaning there are fewer chances of crashes at runtime.
* Maintainability - easier to work with unfamiliar code as you know what kind of objects you are working with.

## Variables
In **Java**, a variable declaration starts with a type. In **Kotlin**, a variable declaration starts with a keyword & optionally a type after the variable name.

```kotlin
// Java
String someString = "Hello, World!";

// Kotlin
var someString: String = "Hello, World!" // or
var someString = "Hello, World!"
```

### What is var?
In **Kotlin**, there are two keywords used when declaring a variable:
* var - a mutable reference & can be reassigned after it has been initialised. It corresponds to a regular variable in **Java**.
* val - an immutable reference & can not be reassigned after it has been initialised. It corresponds to a `final` variable in **Java**.

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

**Kotlin** does not enforce immutability, but is recommended that `val` is used wherever possible.

### Null Safety
**Kotlin** has attempted to achieve a higher level of safety than in **Java**. Running on the JVM already provides a lot of safety, i.e., memory safety, preventing buffer overflows & other problems caused by dynamically allocated memory. Also, **Kotlin** ensures the type safety of your programs meaning errors can be prevented by checks at compile time & not failing at runtime. The **Kotlin** type system tracks nullable & non-nullable values & forbids operations that can lead to a `NullPointerException` at runtime. Nullable values are marked with a `?` after the type.

```kotlin
var notNullable: String = "Hello, World!"
notNullable = null // What happens when you execute this code?

var nullable: String? = "Hello, World!"
nullable = null
```

### Practice Coding Problem ✏️
One a piece of A4 paper, write a program which declares & assigns the following values to their appropriate data types, i.e., `var age: Int = 25`:

* "I <3 this course"
* 13.37F
* 3.14159265359
* false
* 'z'

## Functions
A function declaration starts with the `fun` keyword, followed by the function name, followed by the parameter list in parentheses. Optionally, the parameter list is followed by the return type, separated by a colon. 

```kotlin
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
```

## Control Structures

### What is the difference between a statement & expression?
The difference is that a statement is a top-level element in its enclosing block & does not have its own value, whereas an expression has its own value which can be used as part of another expression. Most control structures in **Kotlin**, except for loops (for, while & do-while) are expressions.

```kotlin
// if expression
fun max(x: Int, y: Int): Int = if (x > y) x else y 

fun main() {
    println(max(50, 10)) // 50
}
```
**Note:** The ternary operator does not exist in **Kotlin**.

### For Loop
The `for` loop is equivalent to a **Java** `for-each` loop. The most common application of this loop is iterating over an array or collection.

```kotlin
val countries = mutableListOf<String>("Australia", "Brazil", "Canada")
for (country in countries) {
    println(country) // Australia
                     // Brazil
                     // Canada
}

// or

for (country in countries) println(country)
```

### Ranges
In **Kotlin**, there is no regular **Java** `for` loop. Instead, **Kotlin** uses ranges which is an interval between two values representing a start & end. The two values are separated by two full stops.

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
The `when` construct can be thought of as a replacement for the `switch` construct in **Java**.

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
    greeting("Russia") // Здравствуйте
}
```

### Practice Coding Problem ✏️ 
On a piece of paper, write a program which loops from 1 to 25. If the loop variable's value is less than 16, print out "I am legally not allowed to drive". If the loop variable's value is ≥ 16, print out "I am legally allowed to drive".

## Collections

### List
In **Kotlin**, lists can be either mutable or read-only. For list creation, use the standard library functions `mutableListOf()` & `listOf()`. To prevent unwanted modifications, obtain read-only views of mutable lists by casting them to `List`.

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
    for (i in getProgLanguages()) {
        println(i) // C#
                   // Go
                   // Java
                   // MATLAB
    }
}
```

### Map
Collection of key/value pairs where each key is unique & is used to retrieve the corresponding value. For map creation, use the standard library functions `mutableMapOf()` & `mapOf()`. To prevent unwanted modifications, obtain read-only views of mutable maps by casting them to `Map`.

```kotlin
const val TRANS_FEE: Int = 15
val bankAccounts: MutableMap<String, Int> = mutableMapOf("YouMoney" to 100, "Personal OnCall" to 100, "Rapid Save" to 100)
val bankReport: Map<String, Int> = bankAccounts

fun addTransactionFee(accountName: String) {
    if (bankAccounts.containsKey(accountName)) {
        println("Updating $accountName...")
        bankAccounts[accountName] = bankAccounts.getValue(accountName) - TRANS_FEE
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

## Class
Classes & interfaces in **Kotlin** differ from **Java**, i.e., interfaces in **Kotlin** can contain property declarations. Unlike in **Java**, declarations are `final` & `public` by default. For constructors, the shorthand syntax works for most cases, but the full syntax that allows you to declare a constructor with non-trivial initialisation logic. It also applies to properties, where you can easily define your own implementation for an accessor.

```java
public class Person {
    private final Int id;
    private String firstName;
    private String lastName;

    public Person(Int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Int getId() {
        return id;
    } 

    public String getFirstName() {
        return name;
    } 

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return name;
    } 

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }  
}
```

In **Java**, the constructor body contains code which assigns the parameters to the fields with the corresponding names. In **Kotlin**, this can be expressed concisely without the large amount of boilerplate.

```kotlin
class Person(
    val id: Int, // Read-only property. Generates a field & getter
    var firstName: String, // Writable property. Generates a field, getter & setter
    var lastName: String
)

// or

class Person constructor(_id: Int, _firstName: String, _lastName: String) { // Not recommended
    val id: Int
    var firstName: String
    var lastName: String

    init {
        id = _id
        firstName = _firstName
        lastName = _lastName
    } 
}

fun main() {
    val person = Person(1, "John", "Doe")
    person.firstName = "Joe"
    println(person.firstName) // Joe
}
```

`public` is the default visibility so you can omit it. By default, the string representation of an object looks like `class type@hash code` which is not very useful. To change this, you need to override the `toString` method.

```kotlin
class Person(val id: Int, var firstName: String, var lastName: String) {
    override fun toString() = "Person(id=$id, firstName=$firstName, lastName=$lastName)"
}

fun main() {
    val person = Person(1, "John", "Doe")
    println(person) // Person(id=1, firstName=John, lastName=Doe)
}
```

You can define a custom implementation of a property accessor.

```kotlin
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
    get() {
        return height == width
    }
}

fun main() {
    val rectangle = Rectangle(10, 20)
    val square = Rectangle(20, 20)
    println(rectangle.isSquare) // false
    println(square.isSquare) // true
}
```

The property `isSquare` does not need a field to store its value. It only needs a custom getter.

## Data Class
If you want your class to be a useful holder for your data, you need to override the `toString`, `equals` & `hashCode` methods. In **Kotlin**, you can add the `data` modifier before a class so that these methods are automatically generated for you. 

```kotlin
data class Person(val id: Int, var firstName: String, var lastName: String)
```

## Enum Class
Much like **Java**, enums are not lists of values - you can declare properties & methods. 

```kotlin
enum class State {
    GREEN, RED, AMBER
}

fun main() {
    val state = State.GREEN
    val trafficLights = when (state) {
        State.GREEN -> "Go"
        State.RED -> "Stop"
        State.AMBER -> "Slow down"
    }
    println(trafficLights) // Go
}
```

Enum constants use the same constructor & property declaration syntax as regular classes.

```kotlin
enum class Color(val rgb: Int) { // Each value must pass an argument 
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    fun containsGreen() = (rgb and 0x00FF00 != 0)
}

fun main() {
    val green = Color.GREEN
    println(green) // GREEN
    println(green.containsGreen()) // true
    println(Color.BLUE.containsGreen()) // false
}
```

### Practice Coding Problem ✏️ 
On a piece of paper, write a program with a regular class called `MobilePhone` & three properties called `osName`, `brand` & `model`. Override its `toString` using the same format as the `Person` class example.

In the `main` function, create two `MobilePhone` objects. For the first `MobilePhone` object, print the `osName` & for the second `MobilePhone` object, print its `toString` method. 

## Interface
Interfaces are similar to those in **Java**, where they can contain definitions of abstract methods as well as implementations of non-abstract methods, but can not contain any state.

Here is an example:

```kotlin
interface BodyParts {
    fun fingerCount(): Int = 10
    fun legCount(): Int
}

class Person : BodyParts {
    override fun legCount() = 2 
}

fun main() {
    val person = Person()
    println(person.fingerCount()) // 10
    println(person.legCount()) // 2
}
```

In **Kotlin**, a colon is used after the class name to replace both the `extends` & `implemented` keywords used in **Java**. A class can implement as many interfaces as it wishes, but can only extend from one class. The `override` modifier is similar to the `@Override` annotation in **Java** & is used to mark properties & methods that override those from the superclass or interface. An interface method can have a default implementation.

## Inheritance

If you want class inheritance, mark the class with the `open` modifier.

```kotlin
open class Dog {
    open fun sound() {
        println("bow wow wow!")
    }
}
```

The empty parentheses indicate an invocation of the superclass default constructor. 

```kotlin
class Bulldog : Dog() {
    override fun sound() { 
        println("woof woof woof!")
    }
}

fun main() {
    val dog: Dog = Bulldog()
    dog.sound() // woof woof woof!
}
```

If you want to use a parameterised constructor of the superclass when creating a subclass, provide the constructor arguments in the subclass declaration.

```kotlin
open class Tiger(val region: String, val sound: String) {
    fun sound() {
        println("A tiger from $region says $sound!")
    }
}

class SiberianTiger : Tiger("Sikhote-Alin", "roooaaar")  

fun main() {
    val siberianTiger: Tiger = SiberianTiger()
    siberianTiger.sound() // A tiger from Sikhote-Alin says roooaaar!
}
```

`region` in the declaration is neither a `var` nor `val` - it is a constructor argument, whose value is passed to the `region` property of the superclass.

```kotlin
class BengalTiger(region: String) : Tiger(region = region, sound = "roooaaar")

fun main() {
    val bengalTiger: Tiger = BengalTiger("Sundarbans")
    bengalTiger.sound() // A tiger from Sundarbans says roooaaar!
}
```

## Generics
In **Kotlin**, you can declare a generic class or interface by putting angle brackets after the class name & the type parameters in the angle brackets. You can use the type parameters in the body of the class just like any other types.

```kotlin
class Stack<T>(vararg items: T) { 
    private val els = items.toMutableList()
    fun push(el: T) = els.add(el)
    fun peek(): T = els.last()
    fun pop(): T = els.removeAt(els.size - 1)
    fun isEmpty() = els.isEmpty()
    fun size() = els.size
    override fun toString() = "Stack[${els.joinToString()}]"
}
```

A generic function has its own type parameters. These must be replaced with the specific type arguments on each function invocation.

```kotlin
fun <T> stackOf(vararg els: T) = Stack(*els)

fun main() {
    val stack = stackOf(2, 4, 6, 8)

    stack.push(10)

    println(stack) // Stack[2, 4, 6, 8, 10]
    println("peek(): ${stack.peek()}") // 10
    println(stack) // Stack[2, 4, 6, 8, 10]

    for (i in 1..stack.size()) {
        println("pop(): ${stack.pop()}")
        println(stack)
    }
}
```

## Practical
The practical for this topic is available [here]().