import java.util.LinkedList;
import java.util.Queue;

/**
 * CLASS - Reservation
 *
 * Represents a booking request made by a guest.
 * This reservation only captures the intention to book
 * before confirmation and room allocation.
 *
 * @version 5.0
 */
class Reservation {

    /** Name of the guest making the booking */
    private String guestName;

    /** Requested room type */
    private String roomType;

    /**
     * Constructor to create a booking request
     * @param guestName name of the guest
     * @param roomType requested room type
     */
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    /** @return guest name */
    public String getGuestName() {
        return guestName;
    }

    /** @return requested room type */
    public String getRoomType() {
        return roomType;
    }
}


/**
 * CLASS - BookingRequestQueue
 *
 * Use Case 5: Booking Request Queue (FIFO)
 *
 * Manages booking requests using a queue to ensure
 * fair allocation of rooms.
 *
 * Requests are processed strictly in the order
 * they are received.
 *
 * @version 5.0
 */
class BookingRequestQueue {

    /** Queue storing booking requests */
    private Queue<Reservation> requestQueue;

    /** Constructor initializes the booking queue */
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /** Adds a booking request to the queue */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    /** Retrieves and removes the next booking request */
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    /** Checks whether queue is not empty */
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/**
 * MAIN CLASS - UseCase5BookingRequestQueue
 *
 * Demonstrates how booking requests are accepted
 * and queued in a First-Come-First-Served order.
 *
 * No room allocation or inventory update
 * is performed here.
 *
 * @version 5.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Request Queue");
        System.out.println("----------------------");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {

            Reservation current = bookingQueue.getNextRequest();

            System.out.println("Guest: " + current.getGuestName());
            System.out.println("Requested Room: " + current.getRoomType());
            System.out.println("----------------------");
        }
    }
}