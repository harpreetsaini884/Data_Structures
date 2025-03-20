import java.util.Arrays;

public class HeapSortTournamentScores {
    public static void heapSort(int[] scores) {
        int n = scores.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(scores, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = scores[0];
            scores[0] = scores[i];
            scores[i] = temp;
            heapify(scores, i, 0);
        }
    }

    private static void heapify(int[] scores, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && scores[left] > scores[largest]) {
            largest = left;
        }
        if (right < n && scores[right] > scores[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = scores[i];
            scores[i] = scores[largest];
            scores[largest] = temp;
            heapify(scores, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] tournamentScores = {88, 75, 90, 66, 92, 85};
        System.out.println("Original Scores: " + Arrays.toString(tournamentScores));

        heapSort(tournamentScores);

        System.out.println("Sorted Scores: " + Arrays.toString(tournamentScores));
    }
}
