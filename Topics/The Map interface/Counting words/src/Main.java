import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        TreeMap<String, Integer> hashMap = new TreeMap<>();
        for (String string : strings
        ) {
            Integer integer = hashMap.putIfAbsent(string, 1);
            if (integer != null) {
                hashMap.put(string, integer + 1);
            }
        }
        return hashMap;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((s, integer) -> System.out.println(s + " : " + integer));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}
