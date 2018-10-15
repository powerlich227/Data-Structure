/**Author: Mingxiao Ye
 * 
 * Assignment 2
 * 
 * Implement Stack ADT using ArrayList.
 *
 */

package cs2321;

import net.datastructures.Stack;

public class ArrayListStack<E> extends ArrayList<E> implements Stack<E> {

	private int top = -1;						// index of the top element in ArrayListStack
	
	@Override
	public int size() {
		return (top + 1);
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}
	
	@Override
	public void push(E e) {
		addLast(e);
		top++;
	}

	@Override
	public E top() {
		if(isEmpty()) {
			return null;
		}			
		return get(top);
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		top--;
		return removeLast();
	}
}
