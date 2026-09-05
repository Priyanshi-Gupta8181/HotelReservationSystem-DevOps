import java.io.*;
import java.util.ArrayList;

public class GradeManager {

    private ArrayList<Student> students;

    private final String FILE_NAME = "students.dat";

    public GradeManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public double calculateAveragePercentage() {
        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getPercentage();
        }

        return total / students.size();
    }

    public double calculateAverageCGPA() {
        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.getCGPA();
        }

        return total / students.size();
    }

    public double getHighestPercentage() {
        if (students.isEmpty()) {
            return 0;
        }

        double highest = students.get(0).getPercentage();

        for (Student student : students) {
            if (student.getPercentage() > highest) {
                highest = student.getPercentage();
            }
        }

        return highest;
    }

    public double getLowestPercentage() {
        if (students.isEmpty()) {
            return 0;
        }

        double lowest = students.get(0).getPercentage();

        for (Student student : students) {
            if (student.getPercentage() < lowest) {
                lowest = student.getPercentage();
            }
        }

        return lowest;
    }

    public double getHighestCGPA() {
        if (students.isEmpty()) {
            return 0;
        }

        double highest = students.get(0).getCGPA();

        for (Student student : students) {
            if (student.getCGPA() > highest) {
                highest = student.getCGPA();
            }
        }

        return highest;
    }

    public double getLowestCGPA() {
        if (students.isEmpty()) {
            return 0;
        }

        double lowest = students.get(0).getCGPA();

        for (Student student : students) {
            if (student.getCGPA() < lowest) {
                lowest = student.getCGPA();
            }
        }

        return lowest;
    }

    public void deleteStudent(int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            saveStudents();
        }
    }

    // Save student data to file
    private void saveStudents() {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            output.writeObject(students);

        } catch (IOException e) {

            JOptionPaneHelper.showError(
                    "Error saving student data: " + e.getMessage()
            );
        }
    }

    // Load student data from file
    @SuppressWarnings("unchecked")
    private void loadStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            students = (ArrayList<Student>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {

            students = new ArrayList<>();

            System.out.println("Could not load previous student data.");
        }
    }

    // Small helper to avoid GUI dependency in GradeManager
    private static class JOptionPaneHelper {

        public static void showError(String message) {
            System.err.println(message);
        }
    }
}