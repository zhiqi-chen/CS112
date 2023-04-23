/*
 * PairFinder.java
 *
 * Computer Science 112, Boston University
 * 
 * Find all pairs of values in the array (if any) that sum to a given integer k
 * 
 * Name: Zhiqi Chen
 * 
 * BU ID: U97832308
 * 
 */

import java.util.Arrays;

public class PairFinder {

    // O(n^2)
    public static void findPairSums(int k, int[] arr) {
        // scan each number in arr
        for (int i = 0; i < arr.length; i++) {
            // scan the rest part in arr to search a pair number for arr[i]
            for (int j = i + 1; j < arr.length; j++) {
                // if has pair numbers, then print
                if (arr[j] == (k - arr[i])) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                }
            }
        }
    }

    // O(n log n)
    public static void findPairSumsFaster(int k, int[] arr) {
        Sort.mergeSort(arr);
        int i = 0;  // index going left to right
        int j = arr.length - 1;   // index going right to left

        // do something like parition in quicksort
        do {
            // if sum > k, then the sum needs to be smaller, j move forward
            if (arr[i] + arr[j] > k) {
                j--;
            } 
            // else if sum < k, then the sum needs to be larger, i move backward
            else if (arr[i] + arr[j] < k) {
                i++;
            }
            // else sum = k, then print out the result and move to next round
            else {
                System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                i++;
                j--;
            }
        } while (i < j);
    }

    public static void main(String args[]) {
        int [] arr = SortCount.randomArray(10);
        System.out.println(Arrays.toString(arr));
        System.out.println("findPairSums: ");
        findPairSums(25, arr);
        System.out.println("findPairSumsFaster: ");
        findPairSumsFaster(25, arr);
    }

}
