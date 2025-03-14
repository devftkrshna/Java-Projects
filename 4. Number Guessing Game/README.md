# Number Guessing Game -Detailed Explanation

### Concepts:
Loops, Random Numbers, Conditionals
### Features:
User guesses a number, feedback on too high/low

---
## Overview

The Number Guessing Game is a simple Java program where the computer randomly selects a number between 1 and 100, and the player has to guess it. The program provides hints if the guess is too high or too low, and it keeps track of the number of attempts. The user can choose to play again after each round.

## Concepts Used

### 1. **Loops**

- The game runs in a loop, allowing multiple rounds of guessing.
- A `while` loop ensures the user keeps guessing until they find the correct number.
- A `do-while` loop is used to allow replaying the game.    

### 2. **Random Numbers**

- The program uses the `Random` class to generate a random number between 1 and 100.

### 3. **Conditionals**

- `if-else` statements provide feedback on whether the guess is too high, too low, or correct.

### 4. **User Input Handling**

- `Scanner` is used to take user input.
- Input validation ensures the user enters only numbers.

---

## **Code Implementation and Line-by-Line Explanation**

```java
import java.util.Random;
import java.util.Scanner;
```

- `import java.util.Random;` → Imports the `Random` class to generate random numbers.
- `import java.util.Scanner;` → Imports `Scanner` for user input.

```java
public class NumberGuessingGame {
    public static void main(String[] args) {
```

- Defines the `NumberGuessingGame` class.
- `main` method is the entry point of the program.

```java
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
```

- Creates an instance of `Scanner` to read user input.
- Creates an instance of `Random` to generate a random number.

```java
        boolean playAgain;
```

- A boolean flag to determine if the user wants to play again.

```java
        System.out.println("🎯 Welcome to the Number Guessing Game! 🎯");
```

- Displays a welcome message.

```java
        do {
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int userGuess = 0;
```

- `random.nextInt(100) + 1` generates a number between **1 and 100**.
- Initializes `attempts` to count user guesses.
- Initializes `userGuess` to store user input.

```java
            System.out.println("\nI have chosen a number between 1 and 100. Try to guess it!");
```

- Informs the user about the game objective.

```java
            while (userGuess != targetNumber) {
                System.out.print("Enter your guess: ");
```

- The loop continues until the user guesses correctly.

```java
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next();
                }
```

- **Input validation:** Ensures that only valid integers are accepted.
- If the input is invalid, an error message is displayed, and the input is cleared.

```java
                userGuess = scanner.nextInt();
                attempts++;
```

- Reads and stores the user’s guess.
- Increments the attempt counter.

```java
                if (userGuess > targetNumber) {
                    System.out.println("Too high! Try again.");
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts!");
                }
            }
```

- Compares `userGuess` with `targetNumber` and provides feedback.
- If the guess is too high or too low, a hint is given.
- If correct, the user is congratulated with the number of attempts.

```java
            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
```

- Asks the user if they want to play again.
- Converts the response to lowercase to handle variations like `Yes` or `YES`.
- If the user types "yes", `playAgain` is set to `true`, and the game restarts.

```java
        } while (playAgain);
```

- The game continues as long as `playAgain` is `true`.

```java
        System.out.println("\nThanks for playing! Goodbye. 👋");
        scanner.close();
    }
}
```

- Displays a goodbye message.
- Closes the `Scanner` to prevent resource leaks.

---

## **Key Takeaways**

1. `**Random**` **Class:**
    
    - Used for generating random numbers.
    - Example:
    
```java
        Random random = new Random();
        int number = random.nextInt(100) + 1; // Generates a number between 1 and 100
```
        
2. **Loops:**

    - `while` loop is used to keep asking for guesses until the user finds the correct number.
    - `do-while` loop ensures at least one round is played.

3. **User Input Handling:**
    
    - `scanner.hasNextInt()` prevents invalid inputs like letters from crashing the program.

4. **Boolean Flag (**`**playAgain**`**)**
    
    - Allows the user to play multiple rounds without restarting the program manually.

5. **String Comparison (**`**equals**` **vs** `**==**`**)**

    - `equals("yes")` is used instead of `==` because `==` compares object references, while `equals` checks the actual content.

---

## **Possible Enhancements**

- **Set difficulty levels (Easy, Medium, Hard)** to adjust the range of numbers.
- **Store high scores** (best attempt counts) using file handling.
- **Add a GUI version** using `Swing` or `JavaFX`.

This project helps strengthen **loops, conditionals, user input handling, and random number generation**, which are crucial for Java development. 🚀

## Author
This project is created by [Vaibhav Sharma](https://www.linkedin.com/in/vaibhavsharma445).
