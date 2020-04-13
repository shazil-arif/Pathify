/**
 * @brief QuickSort algorithm
 * @file QuickSort.java
 * @author Shazil Arif,  Robert Sedgewick and Kevin Wayne 
 * @date April 12th 2020
 */
package com.cas2XB3.group8;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @brief QuickSort algorithm
 * @details Based on the implementation at: https://algs4.cs.princeton.edu/23quicksort/Quick.java.html
 */

public class QuickSort {

    /**
     * @brief sort the array in ascending order, also shuffle to improve performance
     * @param arrayList the array to be sorted
     */
    public static void sort(ArrayList<Integer> a) {
        Collections.shuffle(a);
        sort(a, 0, a.size()-1);
    }

    private static void sort(ArrayList<Integer> a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    /**
     * @brief partitions so that a[lo..j-1] <= a[j] <= a[j+1..hi]
     * @param a The array
     * @param lo The low pointer
     * @param hi The high pointer
     * @return The index of j
     */
    private static int partition(ArrayList<Integer> a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a.get(lo);
        while (true) { 

            // find item on lo to swap
            while (less(a.get(++i), v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a.get(--j))) {
                if (j == lo) break;    
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        return j;
    }

  
    /**
     * @brief Check if is less
     * @param v First item to compare
     * @param w Second item to compare
     * @return boolean indicating result of comparison
     */
    private static boolean less(Integer v, Integer w) {
        if (v == w) return false;   
        return v.compareTo(w) < 0;
    }
        
    /**
     * @brief swap two items
     * @param a Array to change
     * @param i Index of first item
     * @param j Index of second item
     */
    private static void exch(ArrayList<Integer> a, int i, int j) {
        int swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
    }

}