/**
 * @brief MinPQ is a module to represent a minimum priority queue data structure
 * @author Shazil Arif, Robert Sedgewick, Kevin Wayne
 * @file MinPQ.java
 * @date April 10th 2020
 */
package com.cas2XB3.group8;


import java.util.NoSuchElementException;


/**
 * @brief MinPQ is a module to represent a minimum priority queue data structure
 * @details this class is based on the implementation from: https://algs4.cs.princeton.edu/24pq/IndexMinPQ.java.html and full credit 
 * goes to it
 */
public class MinPQ{
    private int size;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Double[] keys;      // keys[i] = priority of i

    /**
     * @brief Constructor for MinPQ
     * @param size the size of the priority queue
     * @throws IllegalArgumentException 
     */
    public MinPQ(int size) {
        if (size < 0) throw new IllegalArgumentException();
        this.size = size;
        n = 0;
        keys = new Double[size + 1];  
        pq   = new int[size + 1];
        qp   = new int[size + 1];             
        for (int i = 0; i <= size; i++) {
            qp[i] = -1;
        }
    }

    /**
     * @brief Returns true if this priority queue is empty.
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() { return n == 0; }

    /**
     * @brief check if an index is on the priority queue
     * @param i an index
     * @return whether the priority queue contains a node
     * @throws IllegalArgumentException if i is out of bounds
     */
    public boolean contains(int i) {
        validateIndex(i);
        return qp[i] != -1;
    }

  
    /**
     * @brief insert a key into the priority queue
     * @param  i an index
     * @param  key the key to associate with index i
     * @throws IllegalArgumentException if the index is out of bounds or is already in the priority queue
     */
    public void insert(int i, Double key) {
        validateIndex(i);
        if (contains(i)) throw new IllegalArgumentException("Index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        heapifyUp(n);
    }


    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int deleteMin() {
        if (n == 0) throw new NoSuchElementException("Cannot delete on empty priority queue");
        int min = pq[1];
        swap(1, n--);
        heapifyDown(1);
        assert min == pq[n+1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        return min;
    }

  
    /**
     * Change the key associated with index i to a given value
     * @param  i the index of the key to change
     * @param  key the key associated with index i to update
     * @throws IllegalArgumentException unless if the index is not in the priority queue
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void changeKey(int i, Double key) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        heapifyUp(qp[i]);
        heapifyDown(qp[i]);
    }


    /*
     * @brief compare the keys associated with two indices in the priority queue
     * @return result of the comparison
     */
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    /*
     * @brief helper method to swap two nodes in the priority queue
     * @param i the first index
     * @param j the second index
     */
    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    /**
     * @brief private helper method to adjust keys by swimming them up if they are out of order
     * @details parent is at index k/2
     */
    private void heapifyUp(int k) {
    	//go until top of heap 
        while (k > 1 && greater(k/2, k)) {
        	
        	 /**
             * as long as parent node has a key greater than given key at index k keep moving up 
             * parent is at index k/2 so iteratively keep halving the index k and compare to parent
             */
        	
            swap(k, k/2);
            k = k/2;
        }
    }

  
    /**
     * @brief private helper method to help adjust location of keys in heap
     * @param k the index of the key to sink down the heap
     */
    private void heapifyDown(int k) {
    	//go until end of heap
        while (2*k <= n) {
        	
        	/**
             * since there are two children we have to compare to each
             * one child is at 2*k and the other is at 2*k + 1
             
             * compare left and right child
             * if right child is greater then the index to swap is the one on 'right' of heap, which is 2*k + 1 so increment j since it current is at 2*k
             */
        	
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break; //the heap ordering is satisfied, break the loop here
            swap(k, j);
            k = j;
        }
    }
    
    /*
     * @brief helper method to validate indices
     */
    private void validateIndex(int i) {
        if (i < 0) throw new IllegalArgumentException("Index cannot be negative");
        if (i >= size) throw new IllegalArgumentException("Index out of bounds");
    }

}
