import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        String[] strings = str.split(" ");
        Integer[] integers = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.valueOf(strings[i]);
        }
        return new HashSet<Integer>(List.of(integers));
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        set.removeIf(elem -> elem > 10);
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}