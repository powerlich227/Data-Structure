
/**Mingxiao Ye
 * 
 * Assignment 1
 * 
 * Implement Queue ADT using a fixed-length array in circular fashion. 
 *
 */

package cs2321;

import net.datastructures.Queue;

public class CircularArrayQueue<E> implements Queue<E> {

	private E[] data;	// generic array used for storage
	private int f = 0;	// index of the front element
	private int sz = 0;	// current number of elements
	
	/* Constructs queue with given queuesize. */
	public CircularArrayQueue(int queueSize) {
		data = (E[]) new Object[queueSize]; 	// safe cast; compiler may give warning
	}
	
	/* Returns the number of elements in the queue. */
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		/* TCJ
		 * The worst case of size method is O(1)
		 */
		return sz;
	}
	
	/* Tests whether the queue is empty. */
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		/* TCJ
		 * The worst case of isEmpty method is O(1)
		 */
		return sz == 0;
	}

	/* Throw IllegalStateException when the queue is full. 
	 * Inserts an element at the rear of the queue. */
	@TimeComplexity("O(1)")
	@Override
	public void enqueue(E e) throws IllegalStateException {
		/* TCJ
		 * The worst case of enqueue method is O(1)
		 */
		if (sz == data.length) 
			throw new IllegalStateException("Queue is Full");
		int avail = (f + sz) % data.length; 	// use modular arithmetic
		data[avail] = e;
		sz++;
	}

	/* Null if empty.
	 * Returns, but does not remove, the first element of the queue. */
	@TimeComplexity("O(1)")
	@Override
	public E first() {
		/* TCJ
		 * The worst case of first method is O(1)
		 */
		if (isEmpty())
			return null;
		return data[f];
	}
	
	/* Null if empty.
	 * Removes and returns the first element of the queue. */
	@TimeComplexity("O(1)")
	@Override
	public E dequeue() {
		/* TCJ
		 * The worst case of dequeue method is O(1)
		 */
		if (isEmpty())
			return null;
		E answer = data[f];
		data[f] = null;							// help garbage collection
		f = (f + 1) % data.length;
		sz--;
		return answer;
	}

}
