import java.util.*;

/**
 * CLASS - Reservation
 * Represents a booking request from a guest.
 * @version 6.0
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * CLASS - RoomInventory
 * Centralized inventory for room availability.
 * @version 6.0
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public int getRoomAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }
}


/**
 * CLASS - BookingRequestQueue
 * Manages booking requests in FIFO order.
 * @version 6.0
 */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/**
 * CLASS - RoomAllocationService
 *
 * Responsible for confirming reservations
 * and assigning unique room IDs.
 *
 * Ensures:
 * - Each room ID is unique
 * - Inventory is updated immediately
 * - No room is double-booked
 *
 * @version 6.0
 */
class RoomAllocationService {

    /** Stores allocated room IDs */
    private Set<String> allocatedRoomIds;

    /** Tracks assigned rooms by type */
    private Map<String, List<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /**
     * Confirms booking and assigns room
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        int available = inventory.getRoomAvailability(roomType);

        if (available <= 0) {
            System.out.println("No available rooms for " + reservation.getGuestName());
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new ArrayList<>())
                .add(roomId);

        inventory.updateAvailability(roomType, available - 1);

        System.out.println("Reservation Confirmed");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + roomType);
        System.out.println("Assigned Room ID: " + roomId);
        System.out.println("---------------------------");
    }

    /**
     * Generates unique room ID
     */
    private String generateRoomId(String roomType) {

        int number = allocatedRoomIds.size() + 1;
        return roomType.substring(0, 1).toUpperCase() + number;
    }
}


/**
 * MAIN CLASS - UseCase6RoomAllocation
 *
 * Demonstrates confirmation of booking requests
 * and safe room allocation.
 *
 * @version 6.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation System");
        System.out.println("----------------------");

        RoomInventory inventory = new RoomInventory();

        inventory.updateAvailability("Single", 2);
        inventory.updateAvailability("Double", 1);
        inventory.updateAvailability("Suite", 1);

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));
        queue.addRequest(new Reservation("Kumar", "Single"));

        RoomAllocationService allocator = new RoomAllocationService();

        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            allocator.allocateRoom(request, inventory);
        }
    }
}