import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RealTimeEventSystem {

    // Queue to hold pending events
    private Queue<String> eventQueue = new LinkedList<>();

    // Add a new event
    public void addEvent(String event) {
        eventQueue.offer(event);
        System.out.println("Event added: " + event);
    }

    // Process the next (oldest) event
    public void processNextEvent() {
        if (eventQueue.isEmpty()) {
            System.out.println("No events to process!");
            return;
        }
        String processedEvent = eventQueue.poll();
        System.out.println("Processing event: " + processedEvent);
    }

    // Display all pending events
    public void displayPendingEvents() {
        if (eventQueue.isEmpty()) {
            System.out.println("No pending events in the queue.");
            return;
        }

        System.out.println("\n--- Pending Events ---");
        int count = 1;
        for (String event : eventQueue) {
            System.out.println(count++ + ". " + event);
        }
    }

    // Cancel an event (if it hasn’t been processed yet)
    public void cancelEvent(String event) {
        if (eventQueue.remove(event)) {
            System.out.println("Event canceled: " + event);
        } else {
            System.out.println("Event not found or already processed!");
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RealTimeEventSystem system = new RealTimeEventSystem();

        while (true) {
            System.out.println("\n--- REAL-TIME EVENT SYSTEM ---");
            System.out.println("1. Add an Event");
            System.out.println("2. Process Next Event");
            System.out.println("3. Display Pending Events");
            System.out.println("4. Cancel an Event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String newEvent = sc.nextLine();
                    system.addEvent(newEvent);
                    break;

                case 2:
                    system.processNextEvent();
                    break;

                case 3:
                    system.displayPendingEvents();
                    break;

                case 4:
                    System.out.print("Enter event name to cancel: ");
                    String cancelEvent = sc.nextLine();
                    system.cancelEvent(cancelEvent);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
