import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;

    public Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Student head;

    public void addStudentAtBeginning(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addStudentAtEnd(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    public void addStudentAtPosition(int rollNo, String name, int age, char grade, int position) {
        if (position <= 1 || head == null) {
            addStudentAtBeginning(rollNo, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNo, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            addStudentAtEnd(rollNo, name, age, grade);
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteStudent(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student with Roll No " + rollNo + " not found.");
            return;
        }
        temp.next = temp.next.next;
    }

    public void searchStudent(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Found: " + temp.rollNo + " " + temp.name + " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }

    public void updateGrade(int rollNo, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Updated Grade for Roll No " + rollNo + " to " + newGrade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("No students in the list.");
            return;
        }
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.rollNo + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentList list = new StudentList();

        while (true) {
            System.out.println("\n1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Position");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Update Student Grade");
            System.out.println("7. Display Students");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Roll No: ");
                    int rollNo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter Grade: ");
                    char grade = scanner.next().charAt(0);
                    if (choice == 1) list.addStudentAtBeginning(rollNo, name, age, grade);
                    else if (choice == 2) list.addStudentAtEnd(rollNo, name, age, grade);
                    else {
                        System.out.print("Enter Position: ");
                        int position = scanner.nextInt();
                        list.addStudentAtPosition(rollNo, name, age, grade, position);
                    }
                    break;
                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    rollNo = scanner.nextInt();
                    list.deleteStudent(rollNo);
                    break;
                case 5:
                    System.out.print("Enter Roll No to search: ");
                    rollNo = scanner.nextInt();
                    list.searchStudent(rollNo);
                    break;
                case 6:
                    System.out.print("Enter Roll No to update grade: ");
                    rollNo = scanner.nextInt();
                    System.out.print("Enter new Grade: ");
                    grade = scanner.next().charAt(0);
                    list.updateGrade(rollNo, grade);
                    break;
                case 7:
                    list.displayStudents();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
