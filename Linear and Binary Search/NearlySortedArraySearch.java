public class NearlySortedArraySearch {
    public static int searchNearlySorted(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid >= 0 && mid < arr.length && arr[mid] == target) {
                return mid;
            } else if (mid - 1 >= left && arr[mid - 1] == target) {
                return mid - 1;
            } else if (mid + 1 <= right && arr[mid + 1] == target) {
                return mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 2;
            } else {
                left = mid + 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 3, 40, 20, 50, 80, 70};
        int target = 40;
        System.out.println("Index of " + target + ": " + searchNearlySorted(arr, target));
    }
}
