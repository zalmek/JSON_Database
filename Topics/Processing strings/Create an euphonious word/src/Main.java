import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Character> vowels = new ArrayList<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int nonvowel = 0;
        int vowel = 0;
        int sum = 0;
        boolean bool = false;
        for (int k = 0; k < text.length(); k++) {
            bool = false;
            for (int i = 0; i < vowels.size(); i++) {
                if (text.charAt(k) == vowels.get(i)) {
                    nonvowel = 0;
                    vowel += 1;
                    bool = true;
                    if (vowel == 3) {
                        sum += 1;
                        vowel = 1;
                    }
                }
            }
            if (!bool) {
                vowel = 0;
                nonvowel += 1;
                if (nonvowel == 3) {
                    sum += 1;
                    nonvowel = 1;
                }
            }
        }
        System.out.println(sum);
    }
}