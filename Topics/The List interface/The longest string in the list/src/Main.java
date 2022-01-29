import java.util.*;

public class Main {

    static void changeList(List<String> list) {
        final String[] longest = {" "};
        list.forEach(element -> longest[0] = longest[0].length() > element.length() ? longest[0] : element);
        list.replaceAll(elem -> longest[0]);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}