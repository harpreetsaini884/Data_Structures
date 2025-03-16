import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] marks) {
        int n = marks.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (marks[j] > marks[j + 1]) {
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Stop if already sorted
        }
    }

    public static void main(String[] args) {
        int[] marks = {85, 78, 92, 65, 89, 76};
        bubbleSort(marks);
        System.out.println("Sorted Marks: " + Arrays.toString(marks));
    }
}
