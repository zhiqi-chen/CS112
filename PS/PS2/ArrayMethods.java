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

import java.util.*;

public class ArrayMethods {

    public static void main(String[] args) {

        int a1[] = {1,2,3,4,5,6};
        int a2[] = {10,11,12,13};
        System.out.println( Arrays.toString( ArrayMethods.reverseInterchange(a1, a2) ) );

    } // main

    public static final String[] DAYS =
       {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public static void swapAdjacent(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }

        int result[] = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values.length % 2 == 0) { // even length
                if (i % 2 == 1) { // odd index
                    result[i] = values[i - 1];
                } else { // even index
                    result[i] = values[i + 1];
                }
            } else { // odd length
                if (i % 2 == 1) { // odd index
                    result[i] = values[i - 1];
                } else if (i % 2 == 0 && i != values.length - 1) { // even index
                    result[i] = values[i + 1];
                } else { // the last element
                    result[i] = values[i];
                }
            }
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = result[i];
        }
    }

    public static int[] copyReplace(int[] values, int oldVal, int newVal) {
        if (values == null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }

        int result[] = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            if (result[i] == oldVal) {
                result[i] = newVal;
            } else {
                result[i] = values[i];
            }
        }
        return result;
    }

    public static int maxSorted(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }

        int counter = 0;
        int result = 0;

        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] <= values[i+1]) { // if smaller than next one, counter + 1
                counter += 1; 
            } else { // else, larger than next one, counter plus the last element in the sorted sequence
                if (result <= counter + 1) {
                    result = counter + 1;
                }
                counter = 0; // initialize the counter to get ready for counting next sorted sequence
            }
        }
        return result;
    }

    public static int getIndexOfDay(String day) {

        String input = "";
        int position = -1;

        for (int i = 0; i < day.length(); i++) {
            char c = day.charAt(i);
            if (i == 0) { // the first letter
                if (c >= 97 && c <=122) { // if lower case
                    // -32 convert to capital
                    c -= 32; 
                    input += c;
                } else { // else, capital case
                    input += c;
                }
            } else {
                if (c >= 65 && c <= 90) { // if capital letters
                    // +32 convert to lower case
                    c += 32; 
                    input += c;
                } else { // else, lower case
                    input += c;
                }
            }
        }
       
        for (int i = 0; i < DAYS.length; i++) {
            if (input.equals(DAYS[i])) {
                position = i;
            }
        }
        return position;
    }

    public static int[] reverseInterchange( int[] arr1, int [] arr2 ) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("parameter cannot be null");
        }

        int length = 0;
        if (arr1.length >= arr2.length) {
            length = arr2.length;
        } else {
            length = arr1.length;
        }
        int result[] = new int[length * 2];
        int counter = 0; // counter for index of arr1 (forward) and arr2 (reverse)
        for (int i = 0; i < length * 2; i++) {
            if (i % 2 ==0) {
                result[i] = arr1[counter];
            } else {
                result[i] = arr2[(arr2.length - 1) - counter];
                counter +=1;
            }
        }        
        return result;
    }
}
