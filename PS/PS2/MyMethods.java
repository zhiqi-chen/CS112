package PS.PS2;

/*
 * Problem Set 2
 * 
 * File: MyMethods.java
 *
 * Author: Zhiqi Chen
 * Course: CS112, Boston University
 *
 */

public class MyMethods {
    public static void printDecreasing(String method) {
        String[] characters = method.split("");
        
        for (int i = characters.length - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.print(characters[j]);
            }
            System.out.println();
        }
    }

    public static String firstAndLast(String str) {
        String result = "";

        String[] characters = str.split("");

        for (int i = 0; i < characters.length; i++) {
            if (i == 0 || i == characters.length - 1) {
               result += characters[i];
            }
        }
        return result;
    }

    public static int lastIndexOf(String str, char ch) {
        int index = -1;

        String[] characters = str.split("");

        for (int i = 0; i < characters.length; i++) {
            String ch_string = Character.toString(ch);
            if (characters[i].equals(ch_string)) {
               index = i;
            } 
        }
        return index;
    }

    public static String repeat(String str, int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += str;
        }
        return result;
    }

    public static void main(String[] args) {
        printDecreasing("method");
        System.out.println(firstAndLast("Boston"));
        System.out.println(lastIndexOf("Boston", 'o'));
        System.out.println(repeat("oh!", 7));
    }

}