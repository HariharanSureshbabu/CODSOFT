import java.util.*;
import java.util.concurrent.*;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }
    
    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class QuizApplication {
    private List<QuizQuestion> questions;
    private int score;
    private List<Boolean> results;

    public QuizApplication() {
        questions = new ArrayList<>();
        score = 0;
        results = new ArrayList<>();
    }

    public void addQuestion(String question, String[] options, int correctOption) {
        questions.add(new QuizQuestion(question, options, correctOption));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestion());

            String[] options = currentQuestion.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            Callable<Integer> task = () -> {
                System.out.print("Enter your choice (1-4): ");
                return scanner.nextInt();
            };

            Future<Integer> future = executor.submit(task);
            int userAnswer = 0;

            try {
                userAnswer = future.get(10, TimeUnit.SECONDS); // 10 seconds timer
            } catch (TimeoutException e) {
                System.out.println("\nTime's up! Moving to the next question.");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("An error occurred. Moving to the next question.");
            }

            if (userAnswer == currentQuestion.getCorrectOption()) {
                score++;
                results.add(true);
                System.out.println("Correct!");
            } else {
                results.add(false);
                System.out.println("Wrong answer.");
            }
        }

        executor.shutdown();
        displayResults();
    }

    private void displayResults() {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Your Score: " + score + "/" + questions.size());

        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions.get(i).getQuestion());
            System.out.println("Your Answer: " + (results.get(i) ? "Correct" : "Wrong"));
        }

        System.out.println("\nThank you for playing!");
    }

    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();

        quizApp.addQuestion("Which country has two national anthems ?", new String[]{"New Zealand", "England", "India", "Hungary"}, 1);
        quizApp.addQuestion("What is the Surface Area of Cylinder ?", new String[]{"π*r^2", "2*π*r*h", "0.5*l*b", "2*π*r^2"}, 2);
        quizApp.addQuestion("What is the chemical symbol for salt?", new String[]{"H2O", "CO2", "O2", "NaCl"}, 4);
        quizApp.addQuestion("Which colour has highest wavelength ?", new String[]{"Red", "Violet", "Blue", "Green"}, 1);

        
        quizApp.startQuiz();
    }
}
