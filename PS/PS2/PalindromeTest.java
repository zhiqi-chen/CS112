package PS.PS2;

/*
 * Problem Set 2
 *
 * File: PalindromeTest.java
 *
 * Author: Zhiqi Chen
 * Course: CS112, Boston University
 *
 */

import java.util.Scanner;

public class PalindromeTest {
    
    public static void main(String[] args) {
        System.out.print(inputStringPalindrome());
    } // main

    public static boolean isPalindrome( String s ) {
        boolean isPal = false;    // assume that it is not
     
        // code to determine if the string s is a palindrome

        int central_index = (s.length() - 1) / 2;

        for (int i = 0; i <= central_index; i++) {

            // convert the string s to all lower case
            // remove any character from the string which is neither a letter nor a digit

            // split each letters into seperated characters
            char c = s.charAt(i);

            if (c >= 65 && c <= 90) { // capital letters
                // +32 convert to lower case
                c += 32; 
            } else if (c >= 97 && c <=122) { // lower case letters
                if (central_index == 0) { // cases for only one letter
                    isPal = true;
                }
            } else {
                s.replace(c, ' ');
            }
    
            if (i == central_index) {
                for (int j = 0; j < central_index; j++) {
                    if (s.charAt(central_index - j) == s.charAt(central_index + j)) {
                        if ( j != 0) { // not the central letter itself
                            isPal = true;
                        }
                    } else {
                        isPal = false;
                    }
                }
            }
        }
     
        // If the default (as above) assumes the string is not a palindrome,
        // the logic here should determine if it is and reassign the return
        // variable isPal appropriately, or vice verse.
     
        return( isPal );
     }

    public static int[] inputStringPalindrome() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nType in a line of text (a String) or \"quit\" to end: ");
        int result[] = new int[2];
        while (userInput.hasNextLine()) {
            String line = userInput.nextLine();
            String[] words = line.split("\\s*'|\\s* ");
            int counter = 0;
            for (int i = 0; i < words.length; i++) {
                if (isPalindrome(words[i]) == true){
                    counter += 1;
                }
            }

            result[0] = words.length;
            result[1] = counter;

            if (line.equals("quit"))
                break;
            else {
                System.out.print("\nType in a line of text (a String) or \"quit\" to end: ");
            }
        }
        System.out.println("bye!");
        userInput.close();
        return result;        
    }
}
