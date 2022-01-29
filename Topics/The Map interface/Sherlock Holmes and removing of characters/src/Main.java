import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> firstTreeMap = new TreeMap<>();
        TreeMap<String, Integer> secondTreeMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String[] first = scanner.nextLine().split("");
        String[] second = scanner.nextLine().split("");
        for (String string : first
        ) {
            Integer integer = firstTreeMap.putIfAbsent(string.toLowerCase(), 1);
            if (integer != null) {
                firstTreeMap.put(string.toLowerCase(), integer + 1);
            }
        }
        for (String string : second
        ) {
            Integer integer = secondTreeMap.putIfAbsent(string.toLowerCase(), 1);
            if (integer != null) {
                secondTreeMap.put(string.toLowerCase(), integer + 1);
            }
        }
        AtomicInteger sum = new AtomicInteger();
        firstTreeMap.forEach((string, integer) -> {
            sum.addAndGet(Math.abs(integer - secondTreeMap.getOrDefault(string, 0)));
            secondTreeMap.remove(string);
        });
        secondTreeMap.forEach((string, integer) -> sum.addAndGet(integer));
        System.out.println(sum);
    }
}