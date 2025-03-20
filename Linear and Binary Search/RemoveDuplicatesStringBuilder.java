public class RemoveDuplicatesStringBuilder {
    public static String removeDuplicates(String input) {
        StringBuilder sb = new StringBuilder();
        boolean[] seen = new boolean[256];

        for (char c : input.toCharArray()) {
            if (!seen[c]) {
                sb.append(c);
                seen[c] = true;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "banana";
        System.out.println("Original String: " + input);
        System.out.println("Without Duplicates: " + removeDuplicates(input));
    }
}
