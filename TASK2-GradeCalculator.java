import java.util.Scanner;

public class MultiStudentMarksCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        String[] studentNames = new String[numStudents];
        int[][] marks = new int[numStudents][numSubjects];

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEntering data for Student " + (i + 1) + ":");
            System.out.print("Enter the name of the student: ");
            studentNames[i] = scanner.next();

            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Enter marks obtained in subject " + (j + 1) + " (out of 100): ");
                marks[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\n--- Results ---");

        for (int i = 0; i < numStudents; i++) {
            int totalMarks = 0;
            for (int j = 0; j < numSubjects; j++) {
                totalMarks += marks[i][j];
            }

            double averagePercentage = (double) totalMarks / numSubjects;
            String grade;

            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            System.out.println("\nStudent: " + studentNames[i]);
            System.out.println("Total Marks: " + totalMarks);
            System.out.println("Average Percentage: " + averagePercentage + "%");
            System.out.println("Grade: " + grade);
        }

        scanner.close();
    }
}
