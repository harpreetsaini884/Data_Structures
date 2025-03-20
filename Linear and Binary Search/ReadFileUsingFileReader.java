import java.io.FileReader;
import java.io.IOException;

public class ReadFileUsingFileReader {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("sample.txt")) {
            int character;
            while ((character = fr.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
