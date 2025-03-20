import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertByteStreamToCharacterStream {
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            System.out.print("Enter a character: ");
            char c = (char) isr.read();
            System.out.println("You entered: " + c);
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}
