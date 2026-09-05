import java.io.Serializable;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bookingId;
    private String guestName;
    private String contact;
    private String roomNumber;
    private String roomCategory;
    private int nights;
    private double totalAmount;
    private String paymentMethod;
    private String paymentStatus;

    public Reservation(
            String bookingId,
            String guestName,
            String contact,
            String roomNumber,
            String roomCategory,
            int nights,
            double totalAmount,
            String paymentMethod,
            String paymentStatus) {

        this.bookingId = bookingId;
        this.guestName = guestName;
        this.contact = contact;
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.nights = nights;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getContact() {
        return contact;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}