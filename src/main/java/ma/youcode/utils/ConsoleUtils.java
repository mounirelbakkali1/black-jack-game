package ma.youcode.utils;

import ma.youcode.App;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUtils {
    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static int readInt(String message, boolean allowZero) {
        System.out.println(message);
        int in = 0;
        boolean invalid = true;
        while (invalid) {
            Scanner sc = new Scanner(System.in);
            try {
                in = sc.nextInt();
                if ((allowZero && in >= 0) || (!allowZero && in > 0)) {
                    invalid = false;
                } else {
                    System.out.println("[invalid input]");
                }
            } catch (Exception e) {
                System.out.println("[retry]");
                continue;
            }
        }
        return in;
    }

    public static String readString(String message, boolean allowEmpty,String[] expected) {
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        String in = "";
        if (!allowEmpty) {
            while (in.isEmpty()) {
                in = sc.nextLine();
                if (!Arrays.asList(expected).contains(in)) {
                    System.out.println("[invalid input]");
                    in = "";
                }
            }
        } else {
            in = sc.nextLine().trim();
        }

        return in.trim();
    }
}
