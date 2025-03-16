import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort(int[] empIDs) {
        int n = empIDs.length;
        for (int i = 1; i < n; i++) {
            int key = empIDs[i];
            int j = i - 1;
            while (j >= 0 && empIDs[j] > key) {
                empIDs[j + 1] = empIDs[j];
                j--;
            }
            empIDs[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] empIDs = {103, 101, 105, 102, 104};
        insertionSort(empIDs);
        System.out.println("Sorted Employee IDs: " + Arrays.toString(empIDs));
    }
}
