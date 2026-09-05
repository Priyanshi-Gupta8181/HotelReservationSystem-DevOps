import java.io.Serializable;

public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roomNumber;
    private String category;
    private double pricePerNight;
    private boolean available;

    public Room(String roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}