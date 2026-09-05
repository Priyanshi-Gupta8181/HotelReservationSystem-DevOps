import java.io.*;
import java.util.ArrayList;

public class HotelManager {

    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    private static final String DATA_FILE = "hotel_data.dat";

    public HotelManager() {

        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        loadData();

        // Add default rooms only for first run
        if (rooms.isEmpty()) {
            addDefaultRooms();
            saveData();
        }
    }

    // ================= DEFAULT ROOMS =================

    private void addDefaultRooms() {

        rooms.add(new Room("101", "Standard", 2000));
        rooms.add(new Room("102", "Standard", 2000));

        rooms.add(new Room("201", "Deluxe", 3500));
        rooms.add(new Room("202", "Deluxe", 3500));

        rooms.add(new Room("301", "Suite", 5500));
        rooms.add(new Room("302", "Suite", 5500));
    }

    // ================= GET ROOMS =================

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // ================= GET RESERVATIONS =================

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    // ================= CHECK ROOM NUMBER =================

    public boolean roomNumberExists(String roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber()
                    .equalsIgnoreCase(roomNumber)) {

                return true;
            }
        }

        return false;
    }

    // ================= ADD ROOM =================

    public void addRoom(
            String roomNumber,
            String category,
            double price) {

        rooms.add(
                new Room(
                        roomNumber,
                        category,
                        price
                )
        );

        saveData();
    }

    // ================= SEARCH ROOMS =================

    public ArrayList<Room> searchAvailableRooms(
            String category) {

        ArrayList<Room> result =
                new ArrayList<>();

        for (Room room : rooms) {

            if (
                    room.isAvailable()
                            &&
                    (
                            category.equals("All")
                                    ||
                            room.getCategory()
                                    .equalsIgnoreCase(category)
                    )
            ) {

                result.add(room);
            }
        }

        return result;
    }

    // ================= FIND ROOM =================

    public Room findRoom(String roomNumber) {

        for (Room room : rooms) {

            if (
                    room.getRoomNumber()
                            .equalsIgnoreCase(roomNumber)
            ) {

                return room;
            }
        }

        return null;
    }

    // ================= CREATE RESERVATION =================

    public Reservation createReservation(
            String guestName,
            String contact,
            Room room,
            int nights,
            String paymentMethod) {

        double total =
                room.getPricePerNight()
                        * nights;

        Reservation reservation =
                new Reservation(
                        generateUniqueBookingId(),
                        guestName,
                        contact,
                        room.getRoomNumber(),
                        room.getCategory(),
                        nights,
                        total,
                        paymentMethod,
                        "Paid"
                );

        reservations.add(reservation);

        // Room becomes unavailable
        room.setAvailable(false);

        saveData();

        return reservation;
    }

    // ================= BOOKING ID =================

    private String generateUniqueBookingId() {

        int number = 1;

        while (true) {

            String id =
                    String.format(
                            "BK%04d",
                            number
                    );

            boolean exists = false;

            for (
                    Reservation reservation :
                    reservations
            ) {

                if (
                        reservation.getBookingId()
                                .equals(id)
                ) {

                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return id;
            }

            number++;
        }
    }

    // ================= CANCEL RESERVATION =================

    public boolean cancelReservation(
            String bookingId) {

        Reservation found = null;

        for (
                Reservation reservation :
                reservations
        ) {

            if (
                    reservation.getBookingId()
                            .equalsIgnoreCase(bookingId)
            ) {

                found = reservation;
                break;
            }
        }

        if (found == null) {
            return false;
        }

        // Make room available again
        Room room =
                findRoom(
                        found.getRoomNumber()
                );

        if (room != null) {
            room.setAvailable(true);
        }

        reservations.remove(found);

        saveData();

        return true;
    }

    // ================= FIND RESERVATION =================

    public Reservation findReservation(
            String bookingId) {

        for (
                Reservation reservation :
                reservations
        ) {

            if (
                    reservation.getBookingId()
                            .equalsIgnoreCase(bookingId)
            ) {

                return reservation;
            }
        }

        return null;
    }

    // ================= SAVE DATA =================

    private void saveData() {

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(DATA_FILE)
                        )
        ) {

            output.writeObject(rooms);
            output.writeObject(reservations);

        } catch (IOException e) {

            System.err.println(
                    "Error saving hotel data: "
                            + e.getMessage()
            );
        }
    }

    // ================= LOAD DATA =================

    @SuppressWarnings("unchecked")
    private void loadData() {

        File file =
                new File(DATA_FILE);

        if (!file.exists()) {
            return;
        }

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(DATA_FILE)
                        )
        ) {

            rooms =
                    (ArrayList<Room>)
                            input.readObject();

            reservations =
                    (ArrayList<Reservation>)
                            input.readObject();

        } catch (
                IOException
                        |
                ClassNotFoundException e
        ) {

            rooms =
                    new ArrayList<>();

            reservations =
                    new ArrayList<>();

            System.err.println(
                    "Could not load previous hotel data."
            );
        }
    }
}