import java.util.Scanner;

class Book {
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(int bookId, String title, String author, String genre, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.next = this.prev = null;
    }
}

class Library {
    private Book head = null, tail = null;
    private int totalBooks = 0;

    public void addBookAtBeginning(int bookId, String title, String author, String genre, boolean isAvailable) {
        Book newBook = new Book(bookId, title, author, genre, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        totalBooks++;
    }

    
    public void addBookAtEnd(int bookId, String title, String author, String genre, boolean isAvailable) {
        Book newBook = new Book(bookId, title, author, genre, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        totalBooks++;
    }

    
    public void removeBookById(int bookId) {
        if (head == null) {
            System.out.println("No books available.");
            return;
        }

        Book temp = head;
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book ID not found.");
            return;
        }

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        totalBooks--;
        System.out.println("Book ID " + bookId + " removed.");
    }

    
    public void searchBook(String key) {
        boolean found = false;
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(key) || temp.author.equalsIgnoreCase(key)) {
                System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Checked Out"));
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    
    public void updateAvailability(int bookId, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                System.out.println("Availability of Book ID " + bookId + " updated to " + (isAvailable ? "Available" : "Checked Out"));
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }

    
    public void displayBooksForward() {
        if (head == null) {
            System.out.println("No books available.");
            return;
        }
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Checked Out"));
            temp = temp.next;
        }
    }

    
    public void displayBooksReverse() {
        if (tail == null) {
            System.out.println("No books available.");
            return;
        }
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.bookId + " | " + temp.title + " | " + temp.author + " | " + temp.genre + " | " + (temp.isAvailable ? "Available" : "Checked Out"));
            temp = temp.prev;
        }
    }

    
    public void countTotalBooks() {
        System.out.println("Total Books in Library: " + totalBooks);
    }
}

public class LibraryManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Remove Book by ID");
            System.out.println("4. Search Book by Title or Author");
            System.out.println("5. Update Book Availability");
            System.out.println("6. Display Books (Forward)");
            System.out.println("7. Display Books (Reverse)");
            System.out.println("8. Count Total Books");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1, 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Is Available? (true/false): ");
                    boolean isAvailable = scanner.nextBoolean();
                    if (choice == 1) library.addBookAtBeginning(bookId, title, author, genre, isAvailable);
                    else library.addBookAtEnd(bookId, title, author, genre, isAvailable);
                    break;
                case 3:
                    System.out.print("Enter Book ID to Remove: ");
                    library.removeBookById(scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Enter Book Title or Author to Search: ");
                    library.searchBook(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Book ID to Update Availability: ");
                    int id = scanner.nextInt();
                    System.out.print("Is Available? (true/false): ");
                    library.updateAvailability(id, scanner.nextBoolean());
                    break;
                case 6:
                    System.out.println("Books in Forward Order:");
                    library.displayBooksForward();
                    break;
                case 7:
                    System.out.println("Books in Reverse Order:");
                    library.displayBooksReverse();
                    break;
                case 8:
                    library.countTotalBooks();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
