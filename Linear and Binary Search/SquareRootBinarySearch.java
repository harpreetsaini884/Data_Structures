public class SquareRootBinarySearch {
    public static int findSquareRoot(int num) {
        int left = 1, right = num, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == num) {
                return mid;
            } else if (mid * mid < num) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int num = 17;
        System.out.println("Square Root (Approximate): " + findSquareRoot(num));
    }
}
