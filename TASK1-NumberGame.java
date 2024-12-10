import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 5;
            boolean guessedCorrectly = false;

            System.out.println("\nI have generated a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    score++;
                    guessedCorrectly = true;
                    break;
                } else if (userGuess > generatedNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Too low!");
                }

                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The number was " + generatedNumber);
            }

            System.out.println("Your current score: " + score);

            System.out.print("\nWould you like to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nThank you for playing! Your final score is: " + score);
        scanner.close();
    }
}
