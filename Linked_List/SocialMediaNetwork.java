import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds; // List of friend IDs
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class FriendNetwork {
    private User head = null;

    
    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
        System.out.println("User " + name + " added successfully!");
    }

    
    private User findUser(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    
    public void addFriend(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!user1.friendIds.contains(userId2)) {
            user1.friendIds.add(userId2);
        }
        if (!user2.friendIds.contains(userId1)) {
            user2.friendIds.add(userId1);
        }
        System.out.println(user1.name + " and " + user2.name + " are now friends!");
    }

    
    public void removeFriend(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.friendIds.remove(Integer.valueOf(userId2));
        user2.friendIds.remove(Integer.valueOf(userId1));

        System.out.println(user1.name + " and " + user2.name + " are no longer friends.");
    }

    
    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        List<Integer> mutualFriends = new ArrayList<>(user1.friendIds);
        mutualFriends.retainAll(user2.friendIds);

        if (mutualFriends.isEmpty()) {
            System.out.println(user1.name + " and " + user2.name + " have no mutual friends.");
        } else {
            System.out.println("Mutual friends of " + user1.name + " and " + user2.name + ":");
            for (int friendId : mutualFriends) {
                User friend = findUser(friendId);
                System.out.println(friend.name);
            }
        }
    }

    
    public void displayFriends(int userId) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println(user.name + "'s friends:");
        if (user.friendIds.isEmpty()) {
            System.out.println("No friends.");
        } else {
            for (int friendId : user.friendIds) {
                User friend = findUser(friendId);
                System.out.println(friend.name);
            }
        }
    }

    
    public void searchUser(String nameOrId) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.userId).equals(nameOrId) || temp.name.equalsIgnoreCase(nameOrId)) {
                System.out.println("User Found: " + temp.name + " (ID: " + temp.userId + ", Age: " + temp.age + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }

    
    public void countFriends() {
        User temp = head;
        System.out.println("\nFriend count for each user:");
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }

    
    public void displayUsers() {
        if (head == null) {
            System.out.println("No users available.");
            return;
        }

        User temp = head;
        System.out.println("\nList of Users:");
        while (temp != null) {
            System.out.println("ID: " + temp.userId + " | Name: " + temp.name + " | Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaNetwork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FriendNetwork network = new FriendNetwork();

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Find Mutual Friends");
            System.out.println("5. Display Friends of a User");
            System.out.println("6. Search User");
            System.out.println("7. Count Friends of Each User");
            System.out.println("8. Display All Users");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    network.addUser(userId, name, age);
                    break;
                case 2:
                    System.out.print("Enter first User ID: ");
                    int id1 = scanner.nextInt();
                    System.out.print("Enter second User ID: ");
                    int id2 = scanner.nextInt();
                    network.addFriend(id1, id2);
                    break;
                case 3:
                    System.out.print("Enter first User ID: ");
                    int rid1 = scanner.nextInt();
                    System.out.print("Enter second User ID: ");
                    int rid2 = scanner.nextInt();
                    network.removeFriend(rid1, rid2);
                    break;
                case 4:
                    System.out.print("Enter first User ID: ");
                    int mid1 = scanner.nextInt();
                    System.out.print("Enter second User ID: ");
                    int mid2 = scanner.nextInt();
                    network.findMutualFriends(mid1, mid2);
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    int fid = scanner.nextInt();
                    network.displayFriends(fid);
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.print("Enter User ID or Name: ");
                    String searchQuery = scanner.nextLine();
                    network.searchUser(searchQuery);
                    break;
                case 7:
                    network.countFriends();
                    break;
                case 8:
                    network.displayUsers();
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
