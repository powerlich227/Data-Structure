
/**Mingxiao Ye
 * 
 * Assignment 1
 * 
 * Implement List ADT using a resizable array.
 *
 */

package cs2321;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	public static final int CAPACITY = 16;		// default initial array capacity is 16
	public int cap = 16;						// adjusted array capacity
	private E[] data;							// generic array used for storage
	private int size = 0;						// current number of elements
	
	/* Constructs list with default capacity of 16. */
	public ArrayList() {
		data = (E[]) new Object[CAPACITY];
	}
	

	/* Returns the number of elements in the array list. */
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		/* TCJ
		 * The worst case of size method is O(1)
		 */
		return size;
	}

	/* Returns whether the array list is empty. */
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		/* TCJ
		 * The worst case of empty method is O(1)
		 */
		return size == 0;
	}

	/* Returns, but does not remove, the element at index i. */
	@TimeComplexity("O(1)")
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		/* TCJ
		 * The worst case of get method is O(1)
		 */
		checkIndex(i, size);
		return data[i];
	}
	
	/* Replaces the element at index i with e, and returns the replaced element. */
	@TimeComplexity("O(1)")
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * The worst case of set method is O(1)
		 */
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}

	/* Inserts element e to be at index i, shifting all subsequent elements later*/
	@TimeComplexity("O(n)")
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * The number of shifting is n-i. At worst case when i=0, there are n shifts.
		 * The worst case of add method is O(n)
		 */
		checkIndex (i, size + 1);
		if (size == cap) {						// not enough capacity
			resize(capacity()); 				// double the current capacity
		}
		for (int k = size - 1; k >= i; k--)	{	// start by shifting rightmost;
			data[k + 1] = data[k];
		}
		data[i] = e;							// ready to replace the new element
		size++;	
	}
	
	/* Removes and returns the element at index i, shifting subsequent elements earlier. */
	@TimeComplexity("O(n)")
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		/* TCJ
		 * The number of shifting is n-i. At worst case when i=0, there are n shifts.
		 * The worst case of remove method is O(n)
		 */
		checkIndex(i, size);
		E temp = data[i];
		for (int k = i; k < size - 1; k++) {	// shift elements to fill hole
			data[k] = data[k + 1];
		}
		data[size - 1] = null;				    // help garbage collection
		size--;
		return temp;
	}

	/* Returns an iterator of the elements in the ArrayList. */
	@TimeComplexity("O(1)")
	@Override
	public Iterator<E> iterator() {
		/* TCJ
		 * The worst case of Iterator method is O(1)
		 */
		return new ArrayIterator();				// create a new instance of the inner class
	}
	/* Nested arrayIterator Class. */
	private class ArrayIterator implements Iterator<E> {
		int index = 0; 							// index of the next element to report
		boolean removable = true;				// can remove be called?
		
		@Override
		public boolean hasNext() {
			return index < size;
		}
		
		@Override
		public E next() throws NoSuchElementException {
			if(index == size) {
				throw new NoSuchElementException("No next element");
			}
			removable = true;
			return data[index++];
		}
		
		@Override
		public void remove() throws IllegalStateException {
			if (!removable) {
				throw new IllegalStateException("Nothing to remove");
			}
			ArrayList.this.remove(index - 1);
			index--;
			removable = false;
		}
	}
	
	/* Adds an element at the first of the ArrayList. */
	@TimeComplexity("O(n)")
	public void addFirst(E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * At worst case, there are n shifts.
		 * The worst case of addFirst method is O(n)
		 */
		if (size == cap) {						// not enough capacity
			resize(capacity()); 				// double the current capacity
		}
		for (int k = size - 1; k >= 0; k--)	{	// start by shifting right;
			data[k + 1] = data[k];
		}
		data[0] = e;							// ready to replace the new element
		size++;	
	}
	
	/* Adds an element at the end of the ArrayList. */
	@TimeComplexity("O(n)")
	public void addLast(E e) throws IndexOutOfBoundsException {
		/* TCJ
		 * The worst case of addLast method is O(1)
		 */
		if (size == cap) {						// not enough capacity
			resize(capacity()); 				// double the current capacity
		}
		data[size] = e;
		size++;
	}
	
	/* Removes and returns the first element of the ArrayList. */
	@TimeComplexity("O(n)")
	public E removeFirst() throws IndexOutOfBoundsException {
		/* TCJ
		 * At worst case, there are n shifts.
		 * The worst case of removeFirst method is O(n)
		 */
		E first = data[0];
		for (int k = 0; k < size - 1; k++) {	// shift elements to first
			data[k] = data[k + 1];
		}
		data[size - 1] = null;					// help garbage collection
		size--;
		return first;
	}
	
	/* Removes and returns the last element of the ArrayList. */
	@TimeComplexity("O(1)")
	public E removeLast() throws IndexOutOfBoundsException {
		/* TCJ
		 * The worst case of removeLast method is O(1)
		 */
		E last = data[size - 1];
		data[size - 1] = null;					// help garbage collection
		size--;
		return last;
	}
	
	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled.
	@TimeComplexity("O(1)")
	public int capacity() {
		/* TCJ
		 * The worst case of capacity method is O(1)
		 */
		if (size == cap) {
			cap = 2 * cap;
		}
		return cap;
	}
	
	/* Resizes internal array to have given capacity larger size. */
	@TimeComplexity("O(n)")
	public void resize(int c) {
		/* TCJ
		 * At worst case, there are n shifts.
		 * The worst case of resize method is O(n)
		 */
		E[] temp = (E[]) new Object[c]; 
		for (int k = 0; k < size; k++) {
			temp[k] = data[k];
		}
		data = temp;							// start using the new array
	}
	
	/* Checks whether the given index is in the range [0, n-1]. */
	@TimeComplexity("O(1)")
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		/* TCJ
		 * The worst case of checkIndex method is O(1)
		 */
		if (i < 0 || i >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}
}
