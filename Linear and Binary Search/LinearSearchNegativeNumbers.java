public class LinearSearchNegativeNumbers {
    public static void main(String[] args) {
        int[] arr = {10, -3, 5, -7, 8, -2};
        System.out.print("Negative numbers: ");
        for (int num : arr) {
            if (num < 0) {
                System.out.print(num + " ");
            }
        }
    }
}
