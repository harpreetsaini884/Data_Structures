import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(double[] prices, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(prices, left, mid);
            mergeSort(prices, mid + 1, right);
            merge(prices, left, mid, right);
        }
    }

    private static void merge(double[] prices, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        double[] leftArray = new double[n1];
        double[] rightArray = new double[n2];

        System.arraycopy(prices, left, leftArray, 0, n1);
        System.arraycopy(prices, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                prices[k++] = leftArray[i++];
            } else {
                prices[k++] = rightArray[j++];
            }
        }
        while (i < n1) prices[k++] = leftArray[i++];
        while (j < n2) prices[k++] = rightArray[j++];
    }

    public static void main(String[] args) {
        double[] bookPrices = {199.99, 89.50, 120.00, 75.75, 150.25};
        mergeSort(bookPrices, 0, bookPrices.length - 1);
        System.out.println("Sorted Book Prices: " + Arrays.toString(bookPrices));
    }
}
