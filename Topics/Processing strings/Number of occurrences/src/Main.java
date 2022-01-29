import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int sum = 0;
        Pattern pattern = Pattern.compile(scanner.nextLine());
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            sum += 1;
        }
        System.out.println(sum);
    }
}