import java.util.HashMap;

/**
 * CLASS - RoomInventory
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class acts as the single source of truth
 * for room availability in the hotel.
 *
 * Room pricing and characteristics are obtained
 * from Room objects, not duplicated here.
 *
 * This avoids multiple sources of truth and keeps
 * responsibilities clearly separated.
 *
 * @version 3.1
 */
class RoomInventory {

    /** Stores room availability */
    private HashMap<String, Integer> roomAvailability;

    /** Constructor initializes the inventory map */
    public RoomInventory() {
        roomAvailability = new HashMap<>();
    }

    /** Returns availability of a room type */
    public int getRoomAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    /** Updates availability count */
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}


/**
 * MAIN CLASS - UseCase3InventorySetup
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class demonstrates how room availability
 * is managed using a centralized inventory.
 *
 * No booking or search logic is introduced here.
 *
 * @version 3.1
 */
public class BookMyStayApp {

    /**
     * Application entry point.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Setting room availability
        inventory.updateAvailability("SingleRoom", 5);
        inventory.updateAvailability("DoubleRoom", 3);
        inventory.updateAvailability("SuiteRoom", 2);

        System.out.println("Hotel Room Inventory Status");
        System.out.println("-----------------------------");

        System.out.println("Single Room Available: "
                + inventory.getRoomAvailability("SingleRoom"));

        System.out.println("Double Room Available: "
                + inventory.getRoomAvailability("DoubleRoom"));

        System.out.println("Suite Room Available: "
                + inventory.getRoomAvailability("SuiteRoom"));
    }
}