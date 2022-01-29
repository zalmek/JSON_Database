import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = List.of(scanner.nextLine().split(" "));
        System.out.println(list);
    }
}