import java.util.Scanner;

public class LinearSearchWord {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        System.out.print("Enter a word to search: ");
        String word = scanner.next();

        String[] words = sentence.split("\\s+");
        boolean found = false;
        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                found = true;
                break;
            }
        }
        System.out.println(found ? "Word found!" : "Word not found.");
    }
}
