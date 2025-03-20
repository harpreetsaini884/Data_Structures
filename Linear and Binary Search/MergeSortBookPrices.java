import java.util.Arrays;

public class MergeSortBookPrices {
    public static void mergeSort(double[] prices, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(prices, left, mid);
            mergeSort(prices, mid + 1, right);
            merge(prices, left, mid, right);
        }
    }

    private static void merge(double[] prices, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        double[] leftArr = new double[n1];
        double[] rightArr = new double[n2];

        System.arraycopy(prices, left, leftArr, 0, n1);
        System.arraycopy(prices, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                prices[k++] = leftArr[i++];
            } else {
                prices[k++] = rightArr[j++];
            }
        }
        while (i < n1) {
            prices[k++] = leftArr[i++];
        }
        while (j < n2) {
            prices[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        double[] bookPrices = {299.99, 499.50, 150.75, 600.00, 199.99};
        System.out.println("Original Prices: " + Arrays.toString(bookPrices));

        mergeSort(bookPrices, 0, bookPrices.length - 1);

        System.out.println("Sorted Prices: " + Arrays.toString(bookPrices));
    }
}
