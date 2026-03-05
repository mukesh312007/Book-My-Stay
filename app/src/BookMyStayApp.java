import java.util.HashMap;
import java.util.Map;

/**
 * ABSTRACT CLASS - Room
 * Represents a generic hotel room.
 * @version 4.0
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Room Size: " + squareFeet + " sq ft");
        System.out.println("Price per Night: ₹" + pricePerNight);
    }
}

/** Single Room definition */
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/** Double Room definition */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/** Suite Room definition */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}


/**
 * CLASS - RoomInventory
 * Centralized inventory storage.
 * @version 4.0
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}


/**
 * CLASS - RoomSearchService
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Provides search functionality for guests
 * to view available rooms.
 *
 * Only reads inventory data.
 *
 * @version 4.0
 */
class RoomSearchService {

    /**
     * Displays available rooms along with their details and pricing.
     */
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check and display Single Room
        if (availability.get("Single") > 0) {
            System.out.println("\nSingle Room Available");
            singleRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + availability.get("Single"));
        }

        // Check and display Double Room
        if (availability.get("Double") > 0) {
            System.out.println("\nDouble Room Available");
            doubleRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + availability.get("Double"));
        }

        // Check and display Suite Room
        if (availability.get("Suite") > 0) {
            System.out.println("\nSuite Room Available");
            suiteRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + availability.get("Suite"));
        }
    }
}


/**
 * MAIN CLASS - UseCase4RoomSearch
 *
 * Demonstrates how guests can view available rooms
 * without modifying inventory data.
 *
 * @version 4.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Setup inventory
        inventory.updateAvailability("Single", 5);
        inventory.updateAvailability("Double", 3);
        inventory.updateAvailability("Suite", 2);

        // Room definitions
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        System.out.println("Hotel Room Search Result");
        System.out.println("-------------------------");

        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}