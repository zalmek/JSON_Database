import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new java.util.ArrayList<>(List.of(scanner.nextLine().split(" ")));
        for (int i = 0; i < list.size(); i += 1) {
            list.remove(i);
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }
}