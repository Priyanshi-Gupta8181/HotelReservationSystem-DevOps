import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField nameField;
    private JTextField rollField;
    private JTextField scoreField;

    private JComboBox<String> assessmentBox;

    private JLabel scoreLabel;
    private JLabel rangeLabel;

    private JLabel totalLabel;
    private JLabel averagePercentageLabel;
    private JLabel averageCGPALabel;
    private JLabel highestLabel;
    private JLabel lowestLabel;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private GradeManager manager;

    public StudentGradeTrackerGUI() {

        manager = new GradeManager();

        setTitle("Student Grade Tracker");
        setSize(1200, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();

        // Load previously saved students into the GUI
        updateTable();
        updateSummary();
    }

    private void createGUI() {

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(new Color(45, 62, 80));

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        );

        JLabel titleLabel = new JLabel(
                "Student Grade Tracker",
                JLabel.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(
                titleLabel,
                BorderLayout.CENTER
        );

        add(headerPanel, BorderLayout.NORTH);


        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBackground(Color.WHITE);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );


        // ================= INPUT PANEL =================

        JPanel inputPanel = new JPanel(
                new GridBagLayout()
        );

        inputPanel.setBackground(Color.WHITE);

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                new Color(160, 180, 200)
                        ),
                        "Enter Student Details"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 10, 8, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // Student Name

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel nameLabel =
                new JLabel("Student Name:");

        nameLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        inputPanel.add(
                nameLabel,
                gbc
        );


        gbc.gridx = 1;

        nameField =
                new JTextField(18);

        nameField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        inputPanel.add(
                nameField,
                gbc
        );


        // Roll Number

        gbc.gridx = 2;

        JLabel rollLabel =
                new JLabel("Roll Number:");

        rollLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        inputPanel.add(
                rollLabel,
                gbc
        );


        gbc.gridx = 3;

        rollField =
                new JTextField(18);

        rollField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        inputPanel.add(
                rollField,
                gbc
        );


        // Assessment Type

        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel typeLabel =
                new JLabel("Assessment Type:");

        typeLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        inputPanel.add(
                typeLabel,
                gbc
        );


        gbc.gridx = 1;

        assessmentBox =
                new JComboBox<>(
                        new String[]{
                                "Percentage",
                                "CGPA"
                        }
                );

        assessmentBox.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        inputPanel.add(
                assessmentBox,
                gbc
        );


        // Score Label

        gbc.gridx = 2;

        scoreLabel =
                new JLabel("Percentage:");

        scoreLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        inputPanel.add(
                scoreLabel,
                gbc
        );


        gbc.gridx = 3;

        scoreField =
                new JTextField(18);

        scoreField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        inputPanel.add(
                scoreField,
                gbc
        );


        // Range Label

        gbc.gridx = 0;
        gbc.gridy = 2;

        rangeLabel =
                new JLabel(
                        "Enter Percentage in range 0 to 100"
                );

        rangeLabel.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        13
                )
        );

        inputPanel.add(
                rangeLabel,
                gbc
        );


        // Add Student Button

        gbc.gridx = 2;

        JButton addButton =
                new JButton("Add Student");

        addButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        inputPanel.add(
                addButton,
                gbc
        );


        // Clear Button

        gbc.gridx = 3;

        JButton clearButton =
                new JButton("Clear Fields");

        clearButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        inputPanel.add(
                clearButton,
                gbc
        );


        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );


        // ================= TABLE =================

        String[] columns = {

                "No.",
                "Student Name",
                "Roll Number",
                "Type",
                "Entered Value",
                "Percentage",
                "CGPA",
                "Grade"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };


        studentTable =
                new JTable(tableModel);

        studentTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        studentTable.setRowHeight(30);

        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        studentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(studentTable);


        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                Color.WHITE
        );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                new Color(160, 180, 200)
                        ),
                        "Student Records"
                )
        );


        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        mainPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );


        // ================= SUMMARY =================

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                10,
                                10
                        )
                );

        summaryPanel.setBackground(
                Color.WHITE
        );

        summaryPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                new Color(160, 180, 200)
                        ),
                        "Grade Summary"
                )
        );


        totalLabel =
                createSummaryLabel(
                        "Total Students: 0"
                );


        averagePercentageLabel =
                createSummaryLabel(
                        "Average Percentage: 0.00%"
                );


        averageCGPALabel =
                createSummaryLabel(
                        "Average CGPA: 0.00"
                );


        highestLabel =
                createSummaryLabel(
                        "Highest: 0.00%"
                );


        lowestLabel =
                createSummaryLabel(
                        "Lowest: 0.00%"
                );


        summaryPanel.add(totalLabel);
        summaryPanel.add(averagePercentageLabel);
        summaryPanel.add(averageCGPALabel);
        summaryPanel.add(highestLabel);
        summaryPanel.add(lowestLabel);


        mainPanel.add(
                summaryPanel,
                BorderLayout.SOUTH
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );


        // ================= BOTTOM BUTTONS =================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                10
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );


        JButton summaryButton =
                new JButton(
                        "Calculate Summary"
                );


        JButton reportButton =
                new JButton(
                        "View Report"
                );


        JButton deleteButton =
                new JButton(
                        "Delete Selected"
                );


        JButton exitButton =
                new JButton(
                        "Exit"
                );


        Font buttonFont =
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                );


        summaryButton.setFont(buttonFont);
        reportButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);


        buttonPanel.add(summaryButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);


        add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // ================= ACTION LISTENERS =================

        addButton.addActionListener(
                e -> addStudent()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        summaryButton.addActionListener(
                e -> updateSummary()
        );


        reportButton.addActionListener(
                e -> showReport()
        );


        deleteButton.addActionListener(
                e -> deleteSelectedStudent()
        );


        exitButton.addActionListener(
                e -> System.exit(0)
        );


        assessmentBox.addActionListener(
                e -> updateInputLabels()
        );
    }


    // ================= SUMMARY LABEL =================

    private JLabel createSummaryLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text,
                        JLabel.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        5,
                        10,
                        5
                )
        );

        return label;
    }


    // ================= UPDATE INPUT LABELS =================

    private void updateInputLabels() {

        String type =
                assessmentBox
                        .getSelectedItem()
                        .toString();


        if (type.equals("Percentage")) {

            scoreLabel.setText(
                    "Percentage:"
            );

            rangeLabel.setText(
                    "Enter Percentage in range 0 to 100"
            );

        } else {

            scoreLabel.setText(
                    "CGPA:"
            );

            rangeLabel.setText(
                    "Enter CGPA in range 0 to 10"
            );
        }


        scoreField.setText("");

        scoreField.requestFocus();
    }


    // ================= ADD STUDENT =================

    private void addStudent() {

        String name =
                nameField.getText().trim();


        String roll =
                rollField.getText().trim();


        String scoreText =
                scoreField.getText().trim();


        String type =
                assessmentBox
                        .getSelectedItem()
                        .toString();


        if (
                name.isEmpty()
                        ||
                roll.isEmpty()
                        ||
                scoreText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        double score;


        try {

            score =
                    Double.parseDouble(
                            scoreText
                    );

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Percentage validation

        if (
                type.equals("Percentage")
                        &&
                (score < 0 || score > 100)
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Percentage must be in the range 0 to 100.",
                    "Invalid Percentage",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // CGPA validation

        if (
                type.equals("CGPA")
                        &&
                (score < 0 || score > 10)
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "CGPA must be in the range 0 to 10.",
                    "Invalid CGPA",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        Student student =
                new Student(
                        name,
                        roll,
                        score,
                        type
                );


        // GradeManager automatically saves data

        manager.addStudent(student);


        updateTable();

        updateSummary();

        clearFields();


        JOptionPane.showMessageDialog(
                this,
                "Student added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // ================= UPDATE TABLE =================

    private void updateTable() {

        tableModel.setRowCount(0);


        int number = 1;


        for (
                Student student :
                manager.getStudents()
        ) {

            Object[] row = {

                    number++,

                    student.getName(),

                    student.getRollNumber(),

                    student.getAssessmentType(),

                    String.format(
                            "%.2f",
                            student.getScore()
                    ),

                    String.format(
                            "%.2f%%",
                            student.getPercentage()
                    ),

                    String.format(
                            "%.2f",
                            student.getCGPA()
                    ),

                    student.getGradeLetter()
            };


            tableModel.addRow(row);
        }
    }


    // ================= UPDATE SUMMARY =================

    private void updateSummary() {

        int total =
                manager.getStudents().size();


        if (total == 0) {

            totalLabel.setText(
                    "Total Students: 0"
            );

            averagePercentageLabel.setText(
                    "Average Percentage: 0.00%"
            );

            averageCGPALabel.setText(
                    "Average CGPA: 0.00"
            );

            highestLabel.setText(
                    "Highest: 0.00%"
            );

            lowestLabel.setText(
                    "Lowest: 0.00%"
            );

            return;
        }


        double averagePercentage =
                manager.calculateAveragePercentage();


        double averageCGPA =
                manager.calculateAverageCGPA();


        double highestPercentage =
                manager.getHighestPercentage();


        double lowestPercentage =
                manager.getLowestPercentage();


        totalLabel.setText(
                "Total Students: " + total
        );


        averagePercentageLabel.setText(
                String.format(
                        "Average Percentage: %.2f%%",
                        averagePercentage
                )
        );


        averageCGPALabel.setText(
                String.format(
                        "Average CGPA: %.2f",
                        averageCGPA
                )
        );


        highestLabel.setText(
                String.format(
                        "Highest: %.2f%%",
                        highestPercentage
                )
        );


        lowestLabel.setText(
                String.format(
                        "Lowest: %.2f%%",
                        lowestPercentage
                )
        );
    }


    // ================= DELETE STUDENT =================

    private void deleteSelectedStudent() {

        int selectedRow =
                studentTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student from the table first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete the selected student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                choice
                        ==
                JOptionPane.YES_OPTION
        ) {

            manager.deleteStudent(
                    selectedRow
            );

            updateTable();

            updateSummary();
        }
    }


    // ================= CLEAR FIELDS =================

    private void clearFields() {

        nameField.setText("");

        rollField.setText("");

        scoreField.setText("");

        nameField.requestFocus();
    }


    // ================= SHOW REPORT =================

    private void showReport() {

        if (
                manager.getStudents().isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No student records available.",
                    "Student Report",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }


        StringBuilder report =
                new StringBuilder();


        report.append(
                "============================================================\n"
        );

        report.append(
                "                 STUDENT GRADE REPORT\n"
        );

        report.append(
                "============================================================\n\n"
        );


        int number = 1;


        for (
                Student student :
                manager.getStudents()
        ) {

            report.append(
                    "Student No. : "
            ).append(
                    number++
            ).append("\n");


            report.append(
                    "Name        : "
            ).append(
                    student.getName()
            ).append("\n");


            report.append(
                    "Roll Number : "
            ).append(
                    student.getRollNumber()
            ).append("\n");


            report.append(
                    "Type        : "
            ).append(
                    student.getAssessmentType()
            ).append("\n");


            report.append(
                    "Entered     : "
            ).append(
                    String.format(
                            "%.2f",
                            student.getScore()
                    )
            ).append("\n");


            report.append(
                    "Percentage  : "
            ).append(
                    String.format(
                            "%.2f%%",
                            student.getPercentage()
                    )
            ).append("\n");


            report.append(
                    "CGPA        : "
            ).append(
                    String.format(
                            "%.2f",
                            student.getCGPA()
                    )
            ).append("\n");


            report.append(
                    "Grade       : "
            ).append(
                    student.getGradeLetter()
            ).append("\n");


            report.append(
                    "------------------------------------------------------------\n"
            );
        }


        report.append("\n");


        report.append(
                "SUMMARY\n"
        );


        report.append(
                "------------------------------------------------------------\n"
        );


        report.append(
                "Total Students       : "
        ).append(
                manager.getStudents().size()
        ).append("\n");


        report.append(
                "Average Percentage   : "
        ).append(
                String.format(
                        "%.2f%%",
                        manager.calculateAveragePercentage()
                )
        ).append("\n");


        report.append(
                "Average CGPA         : "
        ).append(
                String.format(
                        "%.2f",
                        manager.calculateAverageCGPA()
                )
        ).append("\n");


        report.append(
                "Highest Percentage   : "
        ).append(
                String.format(
                        "%.2f%%",
                        manager.getHighestPercentage()
                )
        ).append("\n");


        report.append(
                "Lowest Percentage    : "
        ).append(
                String.format(
                        "%.2f%%",
                        manager.getLowestPercentage()
                )
        ).append("\n");


        report.append(
                "Highest CGPA         : "
        ).append(
                String.format(
                        "%.2f",
                        manager.getHighestCGPA()
                )
        ).append("\n");


        report.append(
                "Lowest CGPA          : "
        ).append(
                String.format(
                        "%.2f",
                        manager.getLowestCGPA()
                )
        ).append("\n");


        report.append(
                "============================================================"
        );


        JTextArea textArea =
                new JTextArea(
                        report.toString()
                );


        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );


        textArea.setEditable(false);


        JScrollPane scrollPane =
                new JScrollPane(textArea);


        scrollPane.setPreferredSize(
                new Dimension(
                        700,
                        500
                )
        );


        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Student Grade Report",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    StudentGradeTrackerGUI gui =
                            new StudentGradeTrackerGUI();

                    gui.setVisible(true);
                }
        );
    }
}