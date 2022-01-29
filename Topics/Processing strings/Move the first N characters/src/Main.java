import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int value = scanner.nextInt();
        if (value > text.length()) {
            System.out.println(text);
        } else {
            String firstsub = text.substring(0, value);
            String secondsub = text.substring(value, text.length());
            System.out.println(secondsub + firstsub);
        }
    }
}