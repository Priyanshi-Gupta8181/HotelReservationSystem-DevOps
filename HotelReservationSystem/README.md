# Hotel Reservation System

A GUI-based Java application for searching, booking, and managing hotel rooms. The application is developed using Java Swing and uses Object-Oriented Programming (OOP) and file I/O for storing room, booking, and availability data.

## Features

- Search available hotel rooms
- Add and manage hotel rooms
- Room categories: Standard, Deluxe, and Suite
- Book available rooms
- Store guest and booking details
- Simulate payment during booking
- View booking details
- Cancel reservations
- Automatically update room availability
- Automatically save room and booking data
- Restore previous data after restarting the application
- User-friendly GUI interface

## Technologies Used

- Java
- Java Swing
- ArrayList
- File I/O
- Serialization
- JTable
- JComboBox
- Object-Oriented Programming (OOP)
- Event Handling

## Project Structure

HotelReservationSystem/

│

├── README.md

│

└── src/

    ├── Room.java

    ├── Reservation.java

    ├── HotelManager.java

    └── HotelReservationGUI.java

## File Description

### Room.java

This class represents a hotel room and stores:

- Room Number
- Room Category
- Price Per Night
- Availability

### Reservation.java

This class represents a hotel reservation and stores:

- Booking ID
- Guest Name
- Contact Number
- Room Number
- Room Category
- Number of Nights
- Total Amount
- Payment Method
- Payment Status

### HotelManager.java

This class manages rooms and reservations using ArrayList.

It performs:

- Adding rooms
- Searching available rooms
- Creating reservations
- Cancelling reservations
- Updating room availability
- Generating booking IDs
- Saving data to file
- Loading previous data

### HotelReservationGUI.java

This is the main GUI class of the project.

It provides:

- Room search
- Room category selection
- Add room
- Booking form
- Payment simulation
- Room availability table
- Reservation table
- Booking details view
- Cancel booking
- Data persistence

## Requirements

- Java JDK 8 or later
- Visual Studio Code or any Java IDE
- Java Extension Pack for VS Code

No external libraries are required.

## How to Run

### Using VS Code

Open and run `HotelReservationGUI.java`.

## How to Use

1. Select a room category and click Search Available.
2. To add a room, enter the room number, select a category, enter the price, and click Add Room.
3. Select an available room from the Rooms table.
4. Open the Make Reservation tab.
5. Enter the guest name, contact number, and number of nights.
6. Select a payment method.
7. Click Book Selected Room.
8. Confirm the simulated payment.
9. View the booking in the Manage Bookings tab.
10. Select a booking and click View Booking Details.
11. Select a booking and click Cancel Booking to cancel it.
12. The cancelled room becomes available again.
13. Room and booking data is saved automatically and restored when the application is run again.

## Payment Simulation

The project uses a simulated payment system.

Payment is not connected to any real bank, card, UPI, or payment gateway.

Available payment methods:

- Cash
- Card
- UPI

## Concepts Used

- Classes and Objects
- Encapsulation
- Constructors
- Methods
- ArrayList
- File I/O
- Serialization
- Loops
- Conditional Statements
- Exception Handling
- Java Swing
- Event Handling
- JTable
- JComboBox
- Input Validation
- Object-Oriented Programming

## Project Objective

The main objective of this project is to develop a GUI-based Hotel Reservation System that allows users to search available rooms, manage different room categories, make reservations, simulate payments, view booking details, and manage room availability using file-based data storage.

## Future Enhancements

- Add database connectivity
- Add customer login
- Add check-in and check-out functionality
- Export booking receipts to PDF
- Add date-based room availability
- Add graphical reports
- Connect to a real payment gateway

## Author

Priyanshi Gupta