import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : (Objects.equals(o1, o2)) ? 0 : -1;
            }
        });
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        Integer from = Integer.parseInt(line[0]);
        Integer to = Integer.valueOf(line[1]);
        Integer number = Integer.valueOf(scanner.nextLine());
        for (int i = 0; i < number; i++) {
            line = scanner.nextLine().split(" ");
            treeMap.put(Integer.valueOf(line[0]), line[1]);
        }

        treeMap.forEach(((integer, s) ->
        {
            if (integer >= from && integer <= to) {
                System.out.println(integer + " " + s);
            }
        }));
    }
}