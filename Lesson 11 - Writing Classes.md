# Lesson 11 - Writing Classes

In Lesson 10 you used a class someone else wrote. Now you'll write one. Every class you write follows the same pattern: private attributes, a constructor that sets them, and public methods that let other code use the object safely.

This lesson rebuilds the `Enemy` class from scratch, with one change that fixes the `goblin.health = -9999` problem.

---

## 1. From UML to Code

Here is the `Enemy` class again. Compare it to Lesson 10: the attributes now start with `-` instead of `+`, and there are new methods to read and change them.

```
┌─────────────────────────────────────┐
│ Enemy                               │
├─────────────────────────────────────┤
│ - name : String                     │
│ - health : int                      │
│ - damage : int                      │
├─────────────────────────────────────┤
│ + Enemy(name : String,              │
│         health : int,               │
│         damage : int)               │
│ + getName() : String                │
│ + getHealth() : int                 │
│ + getDamage() : int                 │
│ + setName(name : String) : void     │
│ + setDamage(damage : int) : void    │
│ + attack() : int                    │
│ + takeDamage(amount : int) : void   │
│ + isDefeated() : boolean            │
└─────────────────────────────────────┘
```

`-` means **private**. Each line of the diagram becomes one piece of the class:

| UML | Java |
|---|---|
| `- health : int` | `private int health;` |
| `+ Enemy(name : String, ...)` | `public Enemy(String name, ...) { ... }` |
| `+ getHealth() : int` | `public int getHealth() { ... }` |
| `+ takeDamage(amount : int) : void` | `public void takeDamage(int amount) { ... }` |

Notice the order flips: UML writes `name : type`, Java writes `type name`.

---

## 2. Anatomy of a Class

Each class goes in its own file, named after the class (`Enemy` goes in `Enemy.java`). A class has three parts, in this order:

```java
public class Enemy {

    // 1. attributes
    private String name;
    private int health;
    private int damage;

    // 2. constructor
    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    // 3. methods
    public int getHealth() {
        return health;
    }

    public void takeDamage(int amount) {
        health = health - amount;
        if (health < 0) {
            health = 0;
        }
    }
}
```

Attributes are declared **inside the class but outside every method**. That's what makes them belong to the object. A variable declared inside a method is local, and disappears when the method ends.

Notice there's **no `static`** anywhere. Every method you've written so far was `static`, because it belonged to the program, not to an object. These methods belong to each `Enemy`, and they work on that enemy's own attributes.

---

## 3. Private Attributes

`private` means only code **inside this class** can use the attribute. Any other class gets a compile error:

```java
Enemy goblin = new Enemy("Goblin", 30, 4);
goblin.health = -9999;    // error: health has private access in Enemy
```

That's the point. The class controls its own data. The only way to change `health` is through `takeDamage`, which never lets it go below 0.

Remember this:: **attributes private, methods public** (unless only the class needs the method, more on that next lesson) .

---

## 4. The Constructor

The **constructor** runs once, when an object is created with `new`. Its job is to give every attribute a starting value.

- Same name as the class, and **no return type** (not even `void`).
- Its parameters usually have the same names as the attributes. `this.name` means "the `name` attribute that belongs to this specific object." `name` on its own means the parameter (the value given when the object is created).

```java
public Enemy(String name, int health, int damage) {
    this.name = name;        // attribute = parameter
    this.health = health;
    this.damage = damage;
}
```

An attribute that isn't a parameter still gets set in the constructor. A `Song` that keeps count of its plays would start with `this.plays = 0;`.

---

## 5. Getters and Setters

Private attributes need public methods so other code can still use them.

A **getter** returns an attribute's value. It's named `get` + the attribute name, takes no parameters, and returns the attribute's type:

```java
public int getHealth() {
    return health;
}
```

A **setter** replaces an attribute's value. It's named `set` + the attribute name, takes the new value, and is `void`:

```java
public void setName(String name) {
    this.name = name;
}
```

You don't need a getter and setter for every attribute - only if you want to directly "get" (access) or "set" (modify) it. We don't need to directly modify an `Enemy`'s health, so there's no `setHealth()` method.  However, the health can be modified through the `takeDamage()` method.  Whether you need a getter or setter for the attributes depends on what you're trying to do with the object.

 
A getter for a `boolean` usually starts with `is` instead: `isDefeated()`.

The AP exam calls getters **accessor methods** and setters **mutator methods**. They mean the same thing.

---

## 6. Other Methods

Most classes also have methods that do something more than get or set. They use the attributes to do their job:

```java
public int attack() {
    return damage;
}

public boolean isDefeated() {
    return health == 0;
}
```

A method can read and change any attribute of its own object directly, with no dot and no getter. `health` inside `takeDamage` means this enemy's health.

---

## 7. Common Class Bugs

**`name = name;` in a constructor or setter.** Without `this.`, the parameter is assigned to itself and the attribute never changes. Getters then return `null` or `0`.

**`static` on an attribute.** `private static int plays;` makes one `plays` shared by every object, so playing one song changes them all.

**`static` on a method that uses attributes.** Compile error: "non-static variable health cannot be referenced from a static context." Methods that use attributes must not be `static`.

**A return type on the constructor.** `public void Enemy(...)` isn't a constructor. It's a method that happens to have the class's name, so `new Enemy("Goblin", 30, 4)` doesn't compile.

**Declaring the attribute again inside the constructor.** `int health = health;` creates a new local variable, and the attribute stays `0`.

**Forgetting `return` in a getter.** Compile error: "missing return statement."

---

## Try It Yourself

Run the companion file from the `src` folder:

```bash
javac WritingClassesDemo.java
java WritingClassesDemo
```

`WritingClassesDemo` uses the `Enemy` class in `Enemy.java`. Open both files side by side.

Then work on the exercise in `Song.java`.

---

## Course Expectations

### ICS 4U

"I can write a class with private attributes, a constructor, getters, setters, and other methods, and use it from another class"
is a requirement of the course and will be expected in your programming project.

Now that you see how the class (template) is created, you can start to write a class (attributes and methods) with your data.

### AP Expectations

The following are expectations of the AP exam and will show up on the final exam:

- Data encapsulation hides a class's implementation details; `private` restricts access to the declaring class, and `public` allows access from other classes (Topic 3.3, 3.3.A.1)
- Classes and constructors are always `public` in this course (3.3.A.2, 3.3.A.3)
- Instance variables belong to the object, and each object has its own copy; they should be `private` unless the class specification says otherwise (3.3.A.4, 3.3.A.5)
- `public` methods can be used inside or outside the class; `private` methods only inside it (3.3.A.6)
- An object's state is its attributes and their values; the constructor sets the initial state and should give every instance variable a value (3.4.A.1, 3.4.A.2)
- With no constructor written, Java provides a default constructor and sets instance variables to default values: `0`, `0.0`, `false`, or `null` (3.4.A.4, 3.4.A.5)
- A `void` method returns nothing; a non-void method returns one value of its return type, and no code after a `return` runs (3.5.A.1 to 3.5.A.4)
- An accessor method (getter) is non-void and returns a copy of an instance variable's value; a mutator method (setter) changes instance variables and is often `void` (3.5.A.5, 3.5.A.6)
- A primitive argument is copied into the parameter, so changing the parameter doesn't change the argument (3.5.A.8)
- Parameters are local variables; when one has the same name as an instance variable, the name refers to the parameter (3.8.A.1, 3.8.A.2)
- Inside a constructor or instance method, `this` refers to the current object (3.9.A.1)
- Class (`static`) methods can't use instance variables or call instance methods without an object (3.7.A.1)
