/*
 * MergeIntersect.java
 *
 * Computer Science 112, Boston University
 * 
 * Take two arrays of integers as parameters and uses an approach based 
 * on merging to find and return the intersection of the two arrays
 * 
 * Name: Zhiqi Chen
 * 
 * BU ID: U97832308
 * 
 */

import java.util.Arrays;

public class MergeIntersect {
    public static int[] intersect(int[] arr1, int[] arr2) {

        // begin by creating a new array for the intersection,
        // the array containing the intersection has the 
        // same length as the shorter of the original arrays
        int length = 0;
        if (arr1.length <= arr2.length) {
            length = arr1.length;
        } else {
            length = arr2.length;
        }
        int[] result = new int[length];

        // sort both of the arrays
        Sort.quickSort(arr1);
        Sort.quickSort(arr2);

        // index into arr1 
        int i = 0;
        // index into arr2
        int j = 0;
        // index into result
        int k = 0;

        // search for intersection
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                result[k] = arr1[i];
                i++;
                j++;
                k++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = MergeIntersect.intersect(a1, a2);
        System.out.println(Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = MergeIntersect.intersect(a3, a4);
        System.out.println(Arrays.toString(result2));
    }

}
