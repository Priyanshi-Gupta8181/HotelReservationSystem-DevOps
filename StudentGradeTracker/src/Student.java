import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String rollNumber;
    private double score;
    private String assessmentType;

    public Student(String name, String rollNumber, double score, String assessmentType) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.score = score;
        this.assessmentType = assessmentType;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public double getScore() {
        return score;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public double getPercentage() {
        if (assessmentType.equals("Percentage")) {
            return score;
        }

        return score * 9.5;
    }

    public double getCGPA() {
        if (assessmentType.equals("CGPA")) {
            return score;
        }

        return score / 9.5;
    }

    public String getGradeLetter() {
        double percentage = getPercentage();

        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}