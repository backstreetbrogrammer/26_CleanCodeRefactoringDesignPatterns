# Clean Code, Refactoring and Design Patterns

> This is a Java course focussing on how to write clean, maintainable and defensive code. It also covers
> best practices for refactoring, SOLID design principles and design patterns.

Tools used:

- JDK 11
- Maven
- JUnit 5, Mockito
- IntelliJ IDE

## Table of contents

1. Clean code
    - Good names
    - Good Constructors
2. Defensive coding
3. Refactoring
4. SOLID design principles
5. Design patterns

---

### Chapter 01. Clean Code

Bad code:

```
Calendar c = Calendar.getInstance();
c.set(123, 5, 26, 0, 0, 0);
c.add(13, 60); // adds one minute
new Service().wish(c.getTime()); // Fri Jun 26 00:01:00 CST 123
```

Clean code:

```
final LocalDate birthdayDate = LocalDate.of(2023, Month.JUNE, 26);
final LocalTime birthdayWishTime = LocalTime.of(0, 1, 0); // hh:mm:ss
new BirthdayWishService().wish(LocalDateTime.of(birthdayDate, birthdayWishTime)); // 2023-06-26T00:01:00
```

"The best programs are written so that computing machines can perform them quickly and so that human beings can
understand them clearly." ~ **Donald Knuth**

"Any fool can write code that a computer can understand. Good programmers write code that humans can understand."
~ **Martin Fowler**

"We are constantly reading old code as part of the effort to write new code... Therefore, making it easy to read makes
it easier to write." ~ **Robert Martin**

"Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live."
~ **John Woods**

**Dirty code consequences**

- More bugs: as the code is not testable
- Decreased productivity: as the code is not maintainable
- Lower job satisfaction: more time spent on reading or understanding the code and solving production issues rather than
  writing real good development projects

#### Good names

Following good naming conventions can go a long way in making our code readable and maintainable.

Bad naming example:

```java
class CommonService { // what does this service do?
    Map<Integer, String> data; // what kind of data?

    public Map<String, String> data() { // get data?
        //....
    }
}
```

**Class names**

Classes and objects should have **noun** or noun phrase names. A class name should NOT be a verb. For ex: Customer, Dog,
House, WikiPage, Account, AddressParser, OrderAlgo, EmailSender etc.

It should be **specific** as well.

For example, class `CommonUtils` contains util methods for "anything" (number, String, time, etc.), and it is too broad.

```java
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {

    // number utils
    public static double getSquareRootPlusOne(final double num) {
        return Math.sqrt(num) + 1D;
    }

    public static int generateRandomNumberBetween(int low, int high) {
        return ThreadLocalRandom.current().nextInt(high - low) + low;
    }

    // String util
    public static String encode(final String data) {
        final StringBuilder builder = new StringBuilder();
        for (final char ch : data.toCharArray()) {
            builder.append(Character.isLetter(ch) ? ch ^ ' ' : ch);
        }
        return builder.reverse().toString();
    }

    // time util
    public static void printCurrentLocalTimeInUTC() {
        final String now = Instant.now().atZone(ZoneId.of("UTC")).toString();
        System.out.println(now);
    }

}
```

It breaks the **Single Responsibility Principle** rule which is basics of SOLID design principles. It states that a
class should only have one responsibility and should only have one reason to change.

So we should break down this class into subclasses with concrete good names: `NumberUtils`, `StringUtils`, `TimeUtils`.

```java
import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {

    public static double getSquareRootPlusOne(final double num) {
        return Math.sqrt(num) + 1D;
    }

    public static int generateRandomNumberBetween(int low, int high) {
        return ThreadLocalRandom.current().nextInt(high - low) + low;
    }
}
```

```java
public class StringUtils {

    public static String encode(final String data) {
        final StringBuilder builder = new StringBuilder();
        for (final char ch : data.toCharArray()) {
            builder.append(Character.isLetter(ch) ? ch ^ ' ' : ch);
        }
        return builder.reverse().toString();
    }

}
```

```java
import java.time.Instant;
import java.time.ZoneId;

public class TimeUtils {

    public static void printCurrentLocalTimeInUTC() {
        final String now = Instant.now().atZone(ZoneId.of("UTC")).toString();
        System.out.println(now);
    }

}
```

To summarize, avoid ending class names as: `Coordinator`, `Manager`, `Processor`, `Data`, or `Info` and always be more
specific.

Some alternatives are:

- Builder
- Factory
- Container
- Writer
- Reader
- Handler

There are quite a few design patterns used for creating objects: `Builder`, `Factory`,`Singleton`, etc.

Thus, we can use these patterns to create class names as:

```java
// OK, provides Order objects
class OrderFactory {
}
```

```java
// OK, builds a client with method chaining
class HttpClientBuilder {
}
```

**Variable names**

Variables in Java capture the state of the object created from a class. The name of the variable should describe the
intent of the variable clearly.

Few guidelines to check:

- use camelCase (Java convention)
- always specific
- never a single letter, except in case of loops like `for` or `foreach`
- should be only 1-2 words, do NOT use very long variable names
- booleans should be prefixed with "is" or "has": **isActive**, **isInterrupted**, **hasCompleted**, etc.
- use ALL_CAPS with underscores for static constants

For ex:

```
Map<Integer, String> d = getStuff(); // single letter 'd' - what does it mean? => never use
Map<Integer, String> data = getStuff(); // what data?
Map<Integer, String> tradeDetails = getStuff(); // correct - tradeDetails clearly indicates that its about trades data 
```

**Method names**

Few guidelines to check:

- name should reveal intent
- anyone can fully understand functionality from the name

In other words, if we have to look inside the method to understand what it does – the name needs improvement.

Taking the same snippet of code as above, we can rename the `getStuff()` method:

```
Map<Integer, String> tradeDetails = getTradeDetails();
```

As methods are actions, we can follow these conventions:

| Verb (do what?) | Noun (to what?) | Method Name (Verb + Noun) |
|-----------------|-----------------|---------------------------|
| get             | TradeDetails    | getTradeDetails()         |
| convert         | Currency        | convertCurrency()         |
| create          | Order           | createOrder()             |
| generate        | RandomNumber    | generateRandomNumber()    |

Few more checks while defining a method and its name:

- avoid abbreviations => `getKms()`, `calculateKgs()`, `setLbs()` - do NOT use this
- avoid spelling errors, otherwise, it will be difficult to search in a large project
- similar to classes, a method should do only one thing and should only have one reason to change
- if a method does MORE than the name says or if it has the name which contains "and", "or", "if" => it should be split
  into smaller methods

For ex:

```
TradeDetails getTradeDetails() {
    // query DB
    // format data
    // precalculate
    // convertToLocalCurrency
}
```

Here, `getTradeDetails()` method is doing 3 or more independent things but the name only says to get trade details.

Thus, we can easily split this method into multiple smaller methods.

- only query DB should be put in the `getTradeDetails()`
- new methods for `formatTradeData()`, `precalculateTradeData()`, `convertToLocalCurrency()`

Do NOT use this: `getAndFormatAndPrecalculateAndConvertToLocalCurrencyTradeData()`

There are few exceptions for method names as we just discussed:

- Static factory methods
- Builder and Fluent design patterns
- Java Streams

```
orderList.stream()
        .map(func1.andThen(func2))
        .findAny()
        .orElseThrow(...);
```

To summarize, naming classes, methods, variables, is one of the most important factors for writing clean code.

|           |                                    |
|-----------|------------------------------------|
| Classes   | Single Responsibility and specific |
| Variables | Descriptive and concise            |
| Methods   | Reveal intent and no multi-tasking |

#### Good Constructors

The most common way to create an object in Java is using `new` operator and call the constructor. Java constructors have
the same name as Class name. Sometimes it may be difficult to remember which constructor should be called if there are
overloaded constructors.

**Static factory methods**

Thus, we can also have **static factory methods** instead of constructors with **meaning-full names** to return a new
object.

For ex: the following static factory method will return an unmodifiable collection object as the name says which is
clear and clean.

```
Collections.unmodifiableCollection(...);
```

Second advantage of static factory methods is that unlike constructors, they are NOT required to create a new object
each time they are invoked. This allows immutable classes to use pre-constructed instances or to cache instances as they
are constructed, and dispense then repeatedly to avoid creating unnecessary duplicates improving performance.

For ex: `Boolean.valueOf(boolean)` method never creates an object but just returns the pre-constructed
instances `Boolean.TRUE` or `Boolean.FALSE` instances.

```
    public static Boolean valueOf(boolean b) {
        return (b ? Boolean.TRUE : Boolean.FALSE);
    }
```

There are various static factory methods available in Java API, like:

```
Collections.unmodifiableCollection(...);
Collections.synchronizedCollection(...);
Path.of("src","test","resources","tmpFile");
Optional.empty();
String.valueOf(true);
LocalDate.of(2023, Month.JULY, 01);
```

**Constructor Chaining**

Constructor chaining is the process of calling a sequence of constructors. We can do it in two ways:

- by using `this()` keyword for chaining constructors in the same class
- by using `super()` keyword for chaining constructors from the parent class

For ex: lets take an `Order` class.

```java
public class Order {
    protected final Long orderId;
    protected final String symbol;
    protected final Integer quantity;
    protected final String side;
    protected final String orderType;
    protected Double price;

    //getters, equals and hashcode
}
```

If `orderType` is **market** order, then `price` field is not needed - or, in other words, `price` field is
**optional**. Other fields are mandatory.

Thus, we can create 2 constructors:

```
public Order(final Long orderId, final String symbol, final Integer quantity, final String side, 
             final String orderType) {
    this(orderId, symbol, quantity, side, orderType, null); // orderType must be market order
}
```

```
public Order(final Long orderId, final String symbol, final Integer quantity, final String side, 
             final String orderType, Double price) {
    if("MKT_ORDER".equals(orderType) && price!=null) {
        throw new IllegalStateException("Market order can not have price");
    }
    // construct the object
}
```

Using `this()`, we are not duplicating the code for order type check and all checking is done at one place in the second
constructor => this is called **DRY** (**Don't Repeat Yourself**) principle.

Now let's create a child class of `Order`, which contains an extra field `msgType`.

```java
public class NewOrder extends Order {
    private final String msgType; // MsgType = 'D' for new order

    //getters, equals and hashcode
}
```

Let's create 2 constructors in a similar way as in the `Order` class:

```
public NewOrder(final Long orderId, final String symbol, final Integer quantity, final String side, 
                final String orderType, final String msgType) {
    this(orderId, symbol, quantity, side, orderType, null, msgType); // orderType must be market order
}
```

```
public NewOrder(final Long orderId, final String symbol, final Integer quantity, final String side, 
                final String orderType, Double price, final String msgType) {
    super(orderId, symbol, quantity, side, orderType, price);
    this.msgType = msgType;
    // construct the object
}
```

Chaining constructors makes code more readable. We don't have to repeat attribute assignments throughout all
constructors. Instead, we do this in one place.

**Constructor Telescoping**

