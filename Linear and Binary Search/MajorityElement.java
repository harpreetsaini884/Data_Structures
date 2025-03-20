import java.util.Arrays;

public class MajorityElement {
    public static int findMajorityElement(int[] arr) {
        Arrays.sort(arr);
        int candidate = arr[arr.length / 2];

        int count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        return count > arr.length / 2 ? candidate : -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 4, 2, 3, 3, 3, 3};
        System.out.println("Majority Element: " + findMajorityElement(arr));
    }
}
