# **02: Kotlin 2**

## Class

In **Kotlin**, classes & interfaces differ from **Java**, i.e., interfaces in **Kotlin** can contain property declarations. Unlike in **Java**, declarations are `final` & `public` by default. For constructors, the shorthand syntax works for most cases, but the full syntax that allows you to declare a constructor with non-trivial initialisation logic. It also applies to properties, where you can easily define your own implementation for an accessor.

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

`public` is the default visibility so you can omit it. By default, the string representation of an object looks like `class type@hash code` which is not useful. To change this, you need to override the `toString` method.

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

Much like **Java**, enums are not lists of values & you can declare properties & methods.

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

## Interface

Interfaces are similar to those in **Java**, where they can contain definitions of abstract methods as well as implementations of non-abstract methods, but can not contain any state.

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

`region` is neither a `var` nor `val`, rather a constructor argument, whose value is passed to the `region` property of the superclass.

```kotlin
class BengalTiger(region: String) : Tiger(region = region, sound = "roooaaar")

fun main() {
    val bengalTiger: Tiger = BengalTiger("Sundarbans")
    bengalTiger.sound() // A tiger from Sundarbans says roooaaar!
}
```
