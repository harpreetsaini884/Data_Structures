import java.util.Arrays;

public class CountingSortStudentGrades {
    public static void countingSort(char[] grades) {
        int[] count = new int[256];
        for (char grade : grades) {
            count[grade]++;
        }

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                grades[index++] = (char) i;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        char[] studentGrades = {'B', 'A', 'D', 'C', 'B', 'A', 'F'};
        System.out.println("Original Grades: " + Arrays.toString(studentGrades));

        countingSort(studentGrades);

        System.out.println("Sorted Grades: " + Arrays.toString(studentGrades));
    }
}
