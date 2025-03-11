import java.util.Scanner;

class Process {
    int processId, burstTime, remainingTime;
    Process next;

    public Process(int processId, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null, tail = null;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    
    public void addProcess(int processId, int burstTime) {
        Process newProcess = new Process(processId, burstTime);
        if (head == null) {
            head = tail = newProcess;
            tail.next = head; 
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head; 
        }
    }

    
    public void removeProcess(int processId) {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }

        Process current = head, prev = null;
        do {
            if (current.processId == processId) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Process " + processId + " completed and removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    
    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        int totalTime = 0, completedProcesses = 0;
        double totalWaitingTime = 0, totalTurnaroundTime = 0;
        Process current = head;

        System.out.println("\nExecuting processes in Round Robin Scheduling...");
        while (completedProcesses < countProcesses()) {
            if (current.remainingTime > 0) {
                int executionTime = Math.min(timeQuantum, current.remainingTime);
                System.out.println("Executing Process " + current.processId + " for " + executionTime + " units.");
                
                current.remainingTime -= executionTime;
                totalTime += executionTime;

                if (current.remainingTime == 0) {
                    int turnaroundTime = totalTime;
                    int waitingTime = turnaroundTime - current.burstTime;

                    totalWaitingTime += waitingTime;
                    totalTurnaroundTime += turnaroundTime;
                    completedProcesses++;

                    System.out.println("Process " + current.processId + " completed. Turnaround Time: " + turnaroundTime + ", Waiting Time: " + waitingTime);
                    removeProcess(current.processId);
                }
            }
            current = current.next;
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / completedProcesses));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / completedProcesses));
    }

    
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }
        Process temp = head;
        System.out.println("Process Queue:");
        do {
            System.out.println("Process ID: " + temp.processId + " | Burst Time: " + temp.burstTime + " | Remaining Time: " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    
    private int countProcesses() {
        if (head == null) return 0;
        int count = 0;
        Process temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

public class RoundRobinSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Time Quantum: ");
        int timeQuantum = scanner.nextInt();

        RoundRobinScheduler scheduler = new RoundRobinScheduler(timeQuantum);

        while (true) {
            System.out.println("\n1. Add Process");
            System.out.println("2. Display Processes");
            System.out.println("3. Execute Processes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    int processId = scanner.nextInt();
                    System.out.print("Enter Burst Time: ");
                    int burstTime = scanner.nextInt();
                    scheduler.addProcess(processId, burstTime);
                    break;
                case 2:
                    scheduler.displayProcesses();
                    break;
                case 3:
                    scheduler.executeProcesses();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
