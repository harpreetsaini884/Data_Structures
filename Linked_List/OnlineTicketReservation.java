import java.util.Scanner;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;
    private int ticketCount = 0;

    
    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            newTicket.next = head; 
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        ticketCount++;
        System.out.println("Ticket booked successfully! Ticket ID: " + ticketID);
    }

    
    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head, prev = tail;
        boolean found = false;

        do {
            if (current.ticketID == ticketID) {
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Ticket ID not found.");
            return;
        }

        if (current == head && current == tail) { 
            head = null;
            tail = null;
        } else if (current == head) { 
            head = head.next;
            tail.next = head;
        } else if (current == tail) { 
            prev.next = head;
            tail = prev;
        } else { 
            prev.next = current.next;
        }

        ticketCount--;
        System.out.println("Ticket removed successfully.");
    }

    
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        System.out.println("\nBooked Tickets:");
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName +
                    ", Movie: " + current.movieName + ", Seat: " + current.seatNumber +
                    ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    
    public void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Ticket current = head;
        boolean found = false;
        System.out.println("\nSearch Results:");
        do {
            if (current.customerName.equalsIgnoreCase(keyword) || current.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName +
                        ", Movie: " + current.movieName + ", Seat: " + current.seatNumber +
                        ", Booking Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No matching tickets found.");
        }
    }

    
    public void getTotalTickets() {
        System.out.println("Total booked tickets: " + ticketCount);
    }
}

public class OnlineTicketReservation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        
        while (true) {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket by Customer/Movie");
            System.out.println("5. Total Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movieName = scanner.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seatNumber = scanner.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String bookingTime = scanner.nextLine();
                    system.addTicket(ticketID, customerName, movieName, seatNumber, bookingTime);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to cancel: ");
                    int removeID = scanner.nextInt();
                    system.removeTicket(removeID);
                    break;

                case 3:
                    system.displayTickets();
                    break;

                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String searchKeyword = scanner.nextLine();
                    system.searchTicket(searchKeyword);
                    break;

                case 5:
                    system.getTotalTickets();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
