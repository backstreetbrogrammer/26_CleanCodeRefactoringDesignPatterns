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

