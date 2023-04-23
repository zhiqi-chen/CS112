/*
 * Duplicates.java
 *
 * Computer Science 112, Boston University
 * 
 * Take a sorted array of integers and removes whatever elements 
 * are necessary to ensure that no item appears more than once
 * 
 * Name: Zhiqi Chen
 * 
 * BU ID: U97832308
 * 
 */

import java.util.Arrays;

public class Duplicates {

    // O(n)
    public static int removeDups(int[] arr){
        int dups = 0;
        int number_index = 0;
        for (int i = 1; i < arr.length; i++) {
            // if duplicate, dups++
            if (arr[i] == arr[i - 1]) {
                dups += 1;
            } else {
                number_index = i;
                // move the unique values foward to cover the duplicate numbers
                arr[number_index - dups] = arr[number_index];
            }
        }
        // set rest of the duplicate numbers to 0s
        for (int i = arr.length - dups; i <= arr.length - 1; i++) {
            arr[i] = 0;
        }
        return arr.length - dups;
    }

    public static void main(String args[]) { 
        int[] arr = {2, 5, 5, 5, 10, 12, 12};
        int ret = removeDups(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(ret);
    }

}
