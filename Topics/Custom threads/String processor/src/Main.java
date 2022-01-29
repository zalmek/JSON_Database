import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        while (true) {
            String text = scanner.nextLine();
            if (!Objects.equals(text, text.toUpperCase(Locale.ROOT))) {
                System.out.println(text.toUpperCase(Locale.ROOT));
            } else {
                System.out.println("FINISHED");
                break;
            }
        }
    }
}