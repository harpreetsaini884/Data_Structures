public class ConcatenateStringBuffer {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("Hello");
        sb.append(", ");
        sb.append("World!");
        System.out.println("Concatenated String: " + sb.toString());
    }
}
