/**
 * @brief class Node represents a node in a graph and associates a generic value with it
 * @file Node.java
 * @author Shazil Arif
 * @date April 10th 2020
 */
package com.cas2XB3.group8;

/**
 * @brief Generic node class parameterized over any type T
 */
public class Node<T>{
	private T val;
	
	/**
	 * @brief constructor method for class Node
	 * @param a generic value
	 **/
	public Node(T val)  {this.val = val;}
	
	/**
	 * @brief getter method for the value
	 * @return the generic value contained in the node
	 **/
	public T getVal() { return this.val;  }
	
	/**
	 * @brief setter method for the value
	 * @param the generic value to set
	 **/
	public void set(T val) {this.val = val;}

}
