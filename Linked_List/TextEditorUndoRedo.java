import java.util.Scanner;

class TextState {
    String text;
    TextState next, prev;

    public TextState(String text) {
        this.text = text;
        this.next = this.prev = null;
    }
}

class TextEditor {
    @SuppressWarnings("unused")
    private TextState head, tail, current;
    private int historySize = 10;
    private int count = 0;

    public TextEditor() {
        head = tail = current = null;
    }

    
    public void addTextState(String text) {
        TextState newState = new TextState(text);

        if (current != null) {
            current.next = newState;
            newState.prev = current;
        } else {
            head = newState;
        }

        current = newState;
        tail = newState;
        count++;

     
        if (count > historySize) {
            head = head.next;
            head.prev = null;
            count--;
        }
    }

    
    public void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Undo not possible! Already at the earliest state.");
            return;
        }
        current = current.prev;
        System.out.println("Undo performed. Current state: " + current.text);
    }

    
    public void redo() {
        if (current == null || current.next == null) {
            System.out.println("Redo not possible! Already at the latest state.");
            return;
        }
        current = current.next;
        System.out.println("Redo performed. Current state: " + current.text);
    }

    
    public void displayCurrentState() {
        if (current == null) {
            System.out.println("No text available.");
        } else {
            System.out.println("Current text: " + current.text);
        }
    }
}

public class TextEditorUndoRedo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        while (true) {
            System.out.println("\n1. Add Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Show Current Text");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = scanner.nextLine();
                    editor.addTextState(text);
                    System.out.println("Text saved.");
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
