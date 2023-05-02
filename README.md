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
new BirthdayWishService().wish(c.getTime());
```

Clean code:

```
final LocalDate birthdayDate = LocalDate.of(2023, Month.JUNE, 26);
final LocalTime birthdayWishTime = LocalTime.of(0, 1, 0); // hh:mm:ss
new BirthdayWishService().wish(LocalDateTime.of(birthdayDate, birthdayWishTime));
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

For example, class CommonUtils contains util methods for "anything" (number, String, time, etc.), and it is too broad.

It breaks the SRP (Single Responsibility Principle) rule which is basics of SOLID design principles. It states that a
class should only have one responsibility and should only have one reason to change.

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


