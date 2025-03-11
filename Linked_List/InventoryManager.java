import java.util.Scanner;

class Item {
    String itemName;
    int itemId, quantity;
    double price;
    Item next;

    public Item(int itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head = null;

    // Add item at the beginning
    public void addItemAtBeginning(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Add item at the end
    public void addItemAtEnd(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    // Remove item by Item ID
    public void removeItemById(int itemId) {
        if (head == null) {
            System.out.println("No items in inventory.");
            return;
        }

        if (head.itemId == itemId) {
            head = head.next;
            System.out.println("Item ID " + itemId + " removed.");
            return;
        }

        Item temp = head, prev = null;
        while (temp != null && temp.itemId != itemId) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Item ID not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Item ID " + itemId + " removed.");
    }

    // Update quantity of an item by Item ID
    public void updateQuantity(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                System.out.println("Updated quantity of Item ID " + itemId + " to " + newQuantity);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item ID not found.");
    }

    // Search item by ID or Name
    public void searchItem(String key) {
        boolean found = false;
        Item temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(key) || String.valueOf(temp.itemId).equals(key)) {
                System.out.println(temp.itemId + " | " + temp.itemName + " | " + temp.quantity + " | $" + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found.");
    }

    // Calculate total inventory value
    public void calculateTotalValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: $" + totalValue);
    }

    // Sort inventory by Item Name (Ascending)
    public void sortByName() {
        if (head == null || head.next == null) return;

        for (Item i = head; i.next != null; i = i.next) {
            for (Item j = head; j.next != null; j = j.next) {
                if (j.itemName.compareToIgnoreCase(j.next.itemName) > 0) {
                    swap(j, j.next);
                }
            }
        }
        System.out.println("Inventory sorted by Name.");
    }

    // Sort inventory by Price (Ascending)
    public void sortByPrice() {
        if (head == null || head.next == null) return;

        for (Item i = head; i.next != null; i = i.next) {
            for (Item j = head; j.next != null; j = j.next) {
                if (j.price > j.next.price) {
                    swap(j, j.next);
                }
            }
        }
        System.out.println("Inventory sorted by Price.");
    }

    // Swap function for sorting
    private void swap(Item a, Item b) {
        int tempId = a.itemId;
        String tempName = a.itemName;
        int tempQty = a.quantity;
        double tempPrice = a.price;

        a.itemId = b.itemId;
        a.itemName = b.itemName;
        a.quantity = b.quantity;
        a.price = b.price;

        b.itemId = tempId;
        b.itemName = tempName;
        b.quantity = tempQty;
        b.price = tempPrice;
    }

    // Display all inventory items
    public void displayInventory() {
        if (head == null) {
            System.out.println("No items in inventory.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            System.out.println(temp.itemId + " | " + temp.itemName + " | " + temp.quantity + " | $" + temp.price);
            temp = temp.next;
        }
    }
}

public class InventoryManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Remove Item by ID");
            System.out.println("4. Update Quantity by ID");
            System.out.println("5. Search Item by ID or Name");
            System.out.println("6. Calculate Total Inventory Value");
            System.out.println("7. Sort Inventory by Name");
            System.out.println("8. Sort Inventory by Price");
            System.out.println("9. Display Inventory");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1, 2:
                    System.out.print("Enter Item ID: ");
                    int itemId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Item Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    if (choice == 1) inventory.addItemAtBeginning(itemId, itemName, quantity, price);
                    else inventory.addItemAtEnd(itemId, itemName, quantity, price);
                    break;
                case 3:
                    System.out.print("Enter Item ID to Remove: ");
                    inventory.removeItemById(scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Enter Item ID to Update Quantity: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter New Quantity: ");
                    inventory.updateQuantity(id, scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Item ID or Name to Search: ");
                    inventory.searchItem(scanner.nextLine());
                    break;
                case 6:
                    inventory.calculateTotalValue();
                    break;
                case 7:
                    inventory.sortByName();
                    break;
                case 8:
                    inventory.sortByPrice();
                    break;
                case 9:
                    inventory.displayInventory();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
