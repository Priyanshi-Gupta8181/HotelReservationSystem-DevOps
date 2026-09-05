import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class HotelReservationGUI extends JFrame {

    private HotelManager manager;

    // Room fields
    private JTextField roomNumberField;
    private JTextField priceField;

    private JComboBox<String> roomCategoryBox;
    private JComboBox<String> searchCategoryBox;

    private JTable roomTable;
    private JTable reservationTable;

    private DefaultTableModel roomTableModel;
    private DefaultTableModel reservationTableModel;

    // Reservation fields
    private JTextField guestNameField;
    private JTextField contactField;
    private JTextField nightsField;

    private JComboBox<String> paymentMethodBox;

    // ================= CONSTRUCTOR =================

    public HotelReservationGUI() {

        manager = new HotelManager();

        setTitle("Hotel Reservation System");

        setSize(1250, 800);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();

        refreshRoomTable(
                manager.getRooms()
        );

        refreshReservationTable();
    }

    // ================= CREATE GUI =================

    private void createGUI() {

        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        getContentPane()
                .setBackground(Color.WHITE);

        // ================= HEADER =================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                new Color(45, 62, 80)
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        10,
                        18,
                        10
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Hotel Reservation System",
                        JLabel.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.CENTER
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ================= TABS =================

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        tabs.addTab(
                "Rooms & Search",
                createRoomPanel()
        );

        tabs.addTab(
                "Make Reservation",
                createReservationPanel()
        );

        tabs.addTab(
                "Manage Bookings",
                createBookingPanel()
        );

        add(
                tabs,
                BorderLayout.CENTER
        );

        // ================= EXIT =================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                8
                        )
                );

        bottomPanel.setBackground(
                Color.WHITE
        );

        JButton exitButton =
                new JButton("Exit");

        exitButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        bottomPanel.add(exitButton);

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    // ================= ROOM PANEL =================

    private JPanel createRoomPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        // ================= SEARCH =================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Search Available Rooms"
                )
        );

        searchPanel.add(
                createBoldLabel(
                        "Room Category:"
                )
        );

        searchCategoryBox =
                new JComboBox<>(
                        new String[]{
                                "All",
                                "Standard",
                                "Deluxe",
                                "Suite"
                        }
                );

        searchCategoryBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JButton searchButton =
                new JButton(
                        "Search Available"
                );

        JButton showAllButton =
                new JButton(
                        "Show All Rooms"
                );

        searchButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        showAllButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        searchPanel.add(
                searchCategoryBox
        );

        searchPanel.add(
                searchButton
        );

        searchPanel.add(
                showAllButton
        );

        searchButton.addActionListener(
                e -> {

                    String category =
                            searchCategoryBox
                                    .getSelectedItem()
                                    .toString();

                    refreshRoomTable(
                            manager.searchAvailableRooms(
                                    category
                            )
                    );
                }
        );

        showAllButton.addActionListener(
                e ->
                        refreshRoomTable(
                                manager.getRooms()
                        )
        );

        panel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        // ================= ROOM TABLE =================

        String[] columns = {
                "Room No.",
                "Category",
                "Price / Night",
                "Status"
        };

        roomTableModel =
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

        roomTable =
                new JTable(
                        roomTableModel
                );

        roomTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        roomTable.setRowHeight(30);

        roomTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        JScrollPane tableScroll =
                new JScrollPane(
                        roomTable
                );

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                Color.WHITE
        );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Room Availability"
                )
        );

        tablePanel.add(
                tableScroll,
                BorderLayout.CENTER
        );

        panel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        // ================= ADD ROOM =================

        JPanel addRoomPanel =
                new JPanel(
                        new GridBagLayout()
                );

        addRoomPanel.setBackground(
                Color.WHITE
        );

        addRoomPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Add Room"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        8,
                        7,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Room Number

        gbc.gridx = 0;
        gbc.gridy = 0;

        addRoomPanel.add(
                createBoldLabel(
                        "Room Number:"
                ),
                gbc
        );

        gbc.gridx = 1;

        roomNumberField =
                new JTextField(12);

        addRoomPanel.add(
                roomNumberField,
                gbc
        );

        // Category

        gbc.gridx = 2;

        addRoomPanel.add(
                createBoldLabel(
                        "Category:"
                ),
                gbc
        );

        gbc.gridx = 3;

        roomCategoryBox =
                new JComboBox<>(
                        new String[]{
                                "Standard",
                                "Deluxe",
                                "Suite"
                        }
                );

        addRoomPanel.add(
                roomCategoryBox,
                gbc
        );

        // Price

        gbc.gridx = 4;

        addRoomPanel.add(
                createBoldLabel(
                        "Price / Night:"
                ),
                gbc
        );

        gbc.gridx = 5;

        priceField =
                new JTextField(12);

        addRoomPanel.add(
                priceField,
                gbc
        );

        // Button

        gbc.gridx = 6;

        JButton addRoomButton =
                new JButton(
                        "Add Room"
                );

        addRoomButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        addRoomPanel.add(
                addRoomButton,
                gbc
        );

        addRoomButton.addActionListener(
                e -> addRoom()
        );

        panel.add(
                addRoomPanel,
                BorderLayout.SOUTH
        );

        return panel;
    }

    // ================= RESERVATION PANEL =================

    private JPanel createReservationPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                Color.WHITE
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Guest & Booking Details"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Guest Name

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                createBoldLabel(
                        "Guest Name:"
                ),
                gbc
        );

        gbc.gridx = 1;

        guestNameField =
                new JTextField(25);

        formPanel.add(
                guestNameField,
                gbc
        );

        // Contact

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                createBoldLabel(
                        "Contact Number:"
                ),
                gbc
        );

        gbc.gridx = 1;

        contactField =
                new JTextField(25);

        formPanel.add(
                contactField,
                gbc
        );

        // Nights

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                createBoldLabel(
                        "Number of Nights:"
                ),
                gbc
        );

        gbc.gridx = 1;

        nightsField =
                new JTextField(25);

        formPanel.add(
                nightsField,
                gbc
        );

        // Payment

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                createBoldLabel(
                        "Payment Method:"
                ),
                gbc
        );

        gbc.gridx = 1;

        paymentMethodBox =
                new JComboBox<>(
                        new String[]{
                                "Cash",
                                "Card",
                                "UPI"
                        }
                );

        formPanel.add(
                paymentMethodBox,
                gbc
        );

        // Instruction

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        JLabel instruction =
                new JLabel(
                        "Select an available room from the Rooms & Search tab first."
                );

        instruction.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        13
                )
        );

        formPanel.add(
                instruction,
                gbc
        );

        // Book button

        gbc.gridy = 5;

        JButton bookButton =
                new JButton(
                        "Book Selected Room"
                );

        bookButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        formPanel.add(
                bookButton,
                gbc
        );

        bookButton.addActionListener(
                e -> bookSelectedRoom()
        );

        panel.add(
                formPanel,
                BorderLayout.NORTH
        );

        // ================= INSTRUCTIONS =================

        JTextArea infoArea =
                new JTextArea();

        infoArea.setEditable(false);

        infoArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        infoArea.setText(
                "Booking Process\n\n"
                        + "1. Go to Rooms & Search.\n"
                        + "2. Search for the required room category.\n"
                        + "3. Select an available room.\n"
                        + "4. Return to this tab.\n"
                        + "5. Enter guest details and number of nights.\n"
                        + "6. Select a payment method.\n"
                        + "7. Click Book Selected Room.\n\n"
                        + "Payment is simulated and no real transaction is made."
        );

        infoArea.setBorder(
                BorderFactory.createTitledBorder(
                        "Instructions"
                )
        );

        panel.add(
                infoArea,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ================= BOOKING PANEL =================

    private JPanel createBookingPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        String[] columns = {

                "Booking ID",
                "Guest Name",
                "Contact",
                "Room No.",
                "Category",
                "Nights",
                "Total Amount",
                "Payment",
                "Status"
        };

        reservationTableModel =
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

        reservationTable =
                new JTable(
                        reservationTableModel
                );

        reservationTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        reservationTable.setRowHeight(30);

        reservationTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        reservationTable
                );

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                Color.WHITE
        );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Current Reservations"
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        panel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        // ================= BUTTONS =================

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

        JButton detailsButton =
                new JButton(
                        "View Booking Details"
                );

        JButton cancelButton =
                new JButton(
                        "Cancel Booking"
                );

        JButton refreshButton =
                new JButton(
                        "Refresh"
                );

        Font buttonFont =
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                );

        detailsButton.setFont(
                buttonFont
        );

        cancelButton.setFont(
                buttonFont
        );

        refreshButton.setFont(
                buttonFont
        );

        buttonPanel.add(
                detailsButton
        );

        buttonPanel.add(
                cancelButton
        );

        buttonPanel.add(
                refreshButton
        );

        detailsButton.addActionListener(
                e -> viewBookingDetails()
        );

        cancelButton.addActionListener(
                e -> cancelBooking()
        );

        refreshButton.addActionListener(
                e -> refreshReservationTable()
        );

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        return panel;
    }

    // ================= LABEL =================

    private JLabel createBoldLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        return label;
    }

    // ================= ADD ROOM =================

    private void addRoom() {

        String roomNumber =
                roomNumberField
                        .getText()
                        .trim();

        String priceText =
                priceField
                        .getText()
                        .trim();

        String category =
                roomCategoryBox
                        .getSelectedItem()
                        .toString();

        if (
                roomNumber.isEmpty()
                        ||
                priceText.isEmpty()
        ) {

            showError(
                    "Please fill all room fields."
            );

            return;
        }

        if (
                manager.roomNumberExists(
                        roomNumber
                )
        ) {

            showError(
                    "Room number already exists."
            );

            return;
        }

        double price;

        try {

            price =
                    Double.parseDouble(
                            priceText
                    );

        } catch (
                NumberFormatException e
        ) {

            showError(
                    "Please enter a valid room price."
            );

            return;
        }

        if (price <= 0) {

            showError(
                    "Room price must be greater than 0."
            );

            return;
        }

        manager.addRoom(
                roomNumber,
                category,
                price
        );

        roomNumberField.setText("");
        priceField.setText("");

        refreshRoomTable(
                manager.getRooms()
        );

        JOptionPane.showMessageDialog(
                this,
                "Room added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= BOOK ROOM =================

    private void bookSelectedRoom() {

        int selectedRow =
                roomTable.getSelectedRow();

        if (selectedRow == -1) {

            showWarning(
                    "Please select an available room from the Rooms & Search tab."
            );

            return;
        }

        String roomNumber =
                roomTableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        Room room =
                manager.findRoom(
                        roomNumber
                );

        if (
                room == null
                        ||
                !room.isAvailable()
        ) {

            showError(
                    "The selected room is not available."
            );

            refreshRoomTable(
                    manager.getRooms()
            );

            return;
        }

        String guestName =
                guestNameField
                        .getText()
                        .trim();

        String contact =
                contactField
                        .getText()
                        .trim();

        String nightsText =
                nightsField
                        .getText()
                        .trim();

        if (
                guestName.isEmpty()
                        ||
                contact.isEmpty()
                        ||
                nightsText.isEmpty()
        ) {

            showError(
                    "Please fill all booking fields."
            );

            return;
        }

        int nights;

        try {

            nights =
                    Integer.parseInt(
                            nightsText
                    );

        } catch (
                NumberFormatException e
        ) {

            showError(
                    "Number of nights must be a valid whole number."
            );

            return;
        }

        if (nights <= 0) {

            showError(
                    "Number of nights must be greater than 0."
            );

            return;
        }

        double total =
                room.getPricePerNight()
                        * nights;

        String paymentMethod =
                paymentMethodBox
                        .getSelectedItem()
                        .toString();

        // ================= PAYMENT SIMULATION =================

        int paymentChoice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Room: "
                                + room.getRoomNumber()
                                + "\nCategory: "
                                + room.getCategory()
                                + "\nPrice/Night: ₹"
                                + String.format(
                                "%.2f",
                                room.getPricePerNight()
                        )
                                + "\nNights: "
                                + nights
                                + "\nTotal Amount: ₹"
                                + String.format(
                                "%.2f",
                                total
                        )
                                + "\nPayment Method: "
                                + paymentMethod
                                + "\n\nProceed with simulated payment?",

                        "Payment Simulation",

                        JOptionPane.YES_NO_OPTION
                );

        if (
                paymentChoice
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }

        String paymentMessage;

        if (
                paymentMethod.equals("Cash")
        ) {

            paymentMessage =
                    "Cash payment marked as simulated Paid.";

        } else if (
                paymentMethod.equals("Card")
        ) {

            paymentMessage =
                    "Card payment simulated successfully.";

        } else {

            paymentMessage =
                    "UPI payment simulated successfully.";
        }

        JOptionPane.showMessageDialog(
                this,
                paymentMessage,
                "Payment Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        // ================= CREATE BOOKING =================

        Reservation reservation =
                manager.createReservation(
                        guestName,
                        contact,
                        room,
                        nights,
                        paymentMethod
                );

        refreshRoomTable(
                manager.getRooms()
        );

        refreshReservationTable();

        clearBookingFields();

        JOptionPane.showMessageDialog(
                this,

                "Booking confirmed!\n\n"
                        + "Booking ID: "
                        + reservation.getBookingId()
                        + "\nRoom Number: "
                        + reservation.getRoomNumber()
                        + "\nGuest: "
                        + reservation.getGuestName()
                        + "\nTotal: ₹"
                        + String.format(
                        "%.2f",
                        reservation.getTotalAmount()
                ),

                "Reservation Confirmed",

                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= VIEW DETAILS =================

    private void viewBookingDetails() {

        int selectedRow =
                reservationTable
                        .getSelectedRow();

        if (selectedRow == -1) {

            showWarning(
                    "Please select a booking first."
            );

            return;
        }

        String bookingId =
                reservationTableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        Reservation reservation =
                manager.findReservation(
                        bookingId
                );

        if (reservation == null) {

            showError(
                    "Booking not found."
            );

            return;
        }

        String details =

                "====================================\n"
                        + "       BOOKING DETAILS\n"
                        + "====================================\n\n"

                        + "Booking ID     : "
                        + reservation.getBookingId()
                        + "\n"

                        + "Guest Name     : "
                        + reservation.getGuestName()
                        + "\n"

                        + "Contact        : "
                        + reservation.getContact()
                        + "\n"

                        + "Room Number    : "
                        + reservation.getRoomNumber()
                        + "\n"

                        + "Room Category  : "
                        + reservation.getRoomCategory()
                        + "\n"

                        + "Nights         : "
                        + reservation.getNights()
                        + "\n"

                        + "Total Amount   : ₹"
                        + String.format(
                        "%.2f",
                        reservation.getTotalAmount()
                )
                        + "\n"

                        + "Payment Method : "
                        + reservation.getPaymentMethod()
                        + "\n"

                        + "Payment Status : "
                        + reservation.getPaymentStatus()
                        + "\n";

        JTextArea area =
                new JTextArea(
                        details
                );

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JOptionPane.showMessageDialog(
                this,
                area,
                "Booking Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= CANCEL BOOKING =================

    private void cancelBooking() {

        int selectedRow =
                reservationTable
                        .getSelectedRow();

        if (selectedRow == -1) {

            showWarning(
                    "Please select a booking first."
            );

            return;
        }

        String bookingId =
                reservationTableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        Reservation reservation =
                manager.findReservation(
                        bookingId
                );

        if (reservation == null) {

            showError(
                    "Booking not found."
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(

                        this,

                        "Cancel booking "
                                + bookingId
                                + " for "
                                + reservation.getGuestName()
                                + "?",

                        "Confirm Cancellation",

                        JOptionPane.YES_NO_OPTION
                );

        if (
                choice
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }

        if (
                manager.cancelReservation(
                        bookingId
                )
        ) {

            refreshRoomTable(
                    manager.getRooms()
            );

            refreshReservationTable();

            JOptionPane.showMessageDialog(
                    this,

                    "Booking cancelled successfully.\n"
                            + "Room "
                            + reservation.getRoomNumber()
                            + " is now available.",

                    "Cancellation Successful",

                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            showError(
                    "Unable to cancel booking."
            );
        }
    }

    // ================= REFRESH ROOM TABLE =================

    private void refreshRoomTable(
            ArrayList<Room> rooms) {

        roomTableModel.setRowCount(0);

        for (Room room : rooms) {

            String status =
                    room.isAvailable()
                            ? "Available"
                            : "Booked";

            roomTableModel.addRow(
                    new Object[]{

                            room.getRoomNumber(),

                            room.getCategory(),

                            String.format(
                                    "₹%.2f",
                                    room.getPricePerNight()
                            ),

                            status
                    }
            );
        }
    }

    // ================= REFRESH RESERVATION TABLE =================

    private void refreshReservationTable() {

        reservationTableModel
                .setRowCount(0);

        for (
                Reservation reservation :
                manager.getReservations()
        ) {

            reservationTableModel.addRow(
                    new Object[]{

                            reservation.getBookingId(),

                            reservation.getGuestName(),

                            reservation.getContact(),

                            reservation.getRoomNumber(),

                            reservation.getRoomCategory(),

                            reservation.getNights(),

                            String.format(
                                    "₹%.2f",
                                    reservation.getTotalAmount()
                            ),

                            reservation.getPaymentMethod(),

                            reservation.getPaymentStatus()
                    }
            );
        }
    }

    // ================= CLEAR BOOKING FIELDS =================

    private void clearBookingFields() {

        guestNameField.setText("");

        contactField.setText("");

        nightsField.setText("");

        paymentMethodBox.setSelectedIndex(0);
    }

    // ================= ERROR MESSAGE =================

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // ================= WARNING MESSAGE =================

    private void showWarning(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // ================= MAIN =================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    HotelReservationGUI gui =
                            new HotelReservationGUI();

                    gui.setVisible(true);
                }
        );
    }
}