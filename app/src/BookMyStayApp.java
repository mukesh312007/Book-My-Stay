/**
 * ABSTRACT CLASS - Room
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This abstract class represents a generic hotel room.
 * It models attributes that are intrinsic to a room type
 * and remain constant regardless of availability.
 *
 * Inventory-related concerns are intentionally excluded.
 *
 * @version 2.1
 */

abstract class Room {

    /** Number of beds available in the room. */
    protected int numberofBeds;

    /** Total size of the room in square feet. */
    protected int squareFeet;

    /** Price charged per night for this room type. */
    protected double pricePerNight;

    /**
     * Constructor used by child classes to
     * initialize common room attributes.
     *
     * @param numberofBeds number of beds in the room
     * @param squareFeet total room size
     * @param pricePerNight cost per night
     */
    public Room(int numberofBeds, int squareFeet, double pricePerNight) {
        this.numberofBeds = numberofBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details */
    public void displayRoomDetails() {
        System.out.println("Number of Beds: " + numberofBeds);
        System.out.println("Room Size: " + squareFeet + " sq ft");
        System.out.println("Price per Night: ₹" + pricePerNight);
    }
}

/**
 * CLASS - SingleRoom
 * Represents a single room in the hotel.
 * @version 2.1
 */
class SingleRoom extends Room {

    /**
     * Initializes a SingleRoom with predefined attributes.
     */
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * CLASS - DoubleRoom
 * Represents a double room in the hotel.
 * @version 2.1
 */
class DoubleRoom extends Room {

    /**
     * Initializes a DoubleRoom with predefined attributes.
     */
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * CLASS - SuiteRoom
 * Represents a suite room in the hotel.
 * @version 2.1
 */
class SuiteRoom extends Room {

    /**
     * Initializes a SuiteRoom with predefined attributes.
     */
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/**
 * MAIN CLASS - UseCase2RoomInitialization
 *
 * Demonstrates room initialization using domain models
 * before introducing centralized inventory management.
 *
 * @version 2.1
 */
public class BookMyStayApp {

    /**
     * Application entry point.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        SingleRoom singleRoom = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        SuiteRoom suiteRoom = new SuiteRoom();

        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        System.out.println("----- Single Room Details -----");
        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleRoomAvailable);

        System.out.println("\n----- Double Room Details -----");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailable);

        System.out.println("\n----- Suite Room Details -----");
        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailable);
    }
}