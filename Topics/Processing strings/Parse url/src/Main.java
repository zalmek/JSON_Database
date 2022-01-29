import java.util.Objects;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] url = scanner.nextLine().split("\\?");
        String[] parameters = url[1].split("&");
        boolean password_exist = false;
        String[] password = null;
        for (String parameter : parameters
        ) {
            String[] key_value = parameter.split("=");
            if (key_value.length == 1) {
                System.out.println(key_value[0] + " : not found");
            } else {
                System.out.println(key_value[0] + " : " + key_value[1]);
                if (Objects.equals(key_value[0], "pass")) {
                    password_exist = true;
                    password = key_value;
                }
            }
        }
        if (password_exist) {
            System.out.println("password : " + password[1]);
        }
    }
}