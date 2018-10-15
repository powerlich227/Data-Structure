/**Author: Mingxiao Ye
 * 
 * Assignment 1
 * 
 * Implement PositionList ADT using a DoublyLinkedList
 *
 */

package cs2321;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {

	/* Nested Node Class */
	private static class Node<E> implements Position<E> {
		private E element;		// reference to the element stored at this node
		private Node<E> prev;	// reference to the previous node in the list
		private Node<E> next;	// reference to the subsequent node in the list
		
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement() throws IllegalStateException {
			if (next == null)	// convention for defunct node
				throw new IllegalStateException("Position no longer valid");
			return element;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setElement(E e) {
			element = e;
		}
		
		public void setPrev(Node<E> p) {
			prev = p;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	/* End of Nested Node Class. */
	
	
	/* Instance variables of the DoublyLinkedList. */
	private Node<E> header;		// header sentinel
	private Node<E> trailer;	// trailer sentinel
	private int size = 0;		// number of elements in the list
	
	/* Constructs a new empty list. */
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}

	
	/* Private utilities.
	 * 
	 * Validates the position and returns it as a node. */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null)
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	
	/* Returns the given node as a Position. 
	 * If it is a sentinel, returns null. */
	private Position<E> position(Node<E> node){
		if(node == header || node == trailer)
			return null;
		return node;
	}

	
	/* Public accessor methods.
	 * 
	/* Returns the number of elements in the linked list. */
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		/* TCJ
		 * The worst case of size method is O(1)
		 */
		return size;
	}

	/* Tests whether the linked list is empty. */
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		/* TCJ
		 * The worst case of empty method is O(1)
		 */
		return size == 0;
	}

	/* Returns the first Position in the linked list. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> first() {
		/* TCJ
		 * The worst case of first method is O(1)
		 */
		return position(header.getNext());
	}

	/* Returns the last Position in the linked list. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> last() {
		/* TCJ
		 * The worst case of last method is O(1)
		 */
		return position(trailer.getPrev());
	}

	/* Returns the Position immediately before Position p.
	 * If p is first, returns null. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		/* TCJ
		 * The worst case of before method is O(1)
		 */
		Node<E> node = validate(p);
		return position(node.getPrev());
	}
	
	/* Returns the Position immediately after Position p.
	 * If p is last, returns null. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		/* TCJ
		 * The worst case of after method is O(1)
		 */
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	
	/* Private Utilities.
	 * 
	 * Adds element e to the linked list between the given nodes. */
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}
	
	
	/* Public Update Methods
	 * 
	 * Inserts element e at the front of the linked list and returns its new Position. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> addFirst(E e) {
		/* TCJ
		 * The worst case of addFirst method is O(1)
		 */
		return addBetween(e, header, header.getNext());		// just after the header
	}

	/* Inserts element e at the back of the linked list and returns its new Position. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> addLast(E e) {
		/* TCJ
		 * The worst case of addLast method is O(1)
		 */
		return addBetween(e, trailer.getPrev(), trailer);	//just before the trailer
	}

	/* Inserts element e immediately before Position p, and returns it new Position. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		/* TCJ
		 * The worst case of addBefore method is O(1)
		 */
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	/* Inserts element e immediately after Position p, and returns it new Position. */
	@TimeComplexity("O(1)")
	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		/* TCJ
		 * The worst case of addAfter method is O(1)
		 */
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}
	
	/* Replaces the element stored at Position p and returns the replaced element. */
	@TimeComplexity("O(1)")
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		/* TCJ
		 * The worst case of set method is O(1)
		 */
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	/* Removes, invalidates the element stored at Position p and returns it. */
	@TimeComplexity("O(1)")
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		/* TCJ
		 * The worst case of remove method is O(1)
		 */
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);					// help garbage collection
		node.setNext(null);						// convention for defunct node
		node.setPrev(null);
		return answer;
	}

	/* Returns an iterator of the elements in the DoublyLinkedList. */
	@TimeComplexity("O(1)")
	@Override
	public Iterator<E> iterator() {
		/* TCJ
		 * The worst case of Iterator method is O(1)
		 */
        return new ElementIterator();
    }
	/* Nested doublyLinkedListIterator class*/
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = new PositionIterator();
		
		@Override
		public boolean hasNext() { 
			return posIterator.hasNext(); 
		} 
		public E next() { 
			return posIterator.next().getElement(); 
		}
		public void remove() { 
			posIterator.remove(); 
		}
	}

	/* Returns an iterable representation of the list's positions. */
	@TimeComplexity("O(1)")
	@Override
	public Iterable<Position<E>> positions() {
		/* TCJ
		 * The worst case of Iterable method is O(1)
		 */
		return new PositionIterable();
	}
	/* Nested PositionIterable Class */
	private class PositionIterable implements Iterable<Position<E>> {
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}
	/* Nested PositionIterator Class */	
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first();		// position of the next element to report
		private Position<E> recent = null;
		
		@Override
		public boolean hasNext() {
			return cursor != null;
		}
		
		@Override
		public Position<E> next() throws NoSuchElementException { 
			if (cursor == null) 
				throw new NoSuchElementException("Nothing left");
			recent = cursor;						// element at this position might later be removed
			cursor = after(cursor);
			return recent;
		}
		
		@Override
		public void remove( ) throws IllegalStateException { 
			if (recent == null) 
				throw new IllegalStateException("Nothing to remove");
			DoublyLinkedList.this.remove(recent);	// remove from outer list
			recent = null;
		}
	}
	
	/* Removes and returns the first element of the DoublyLinkedList. */
	@TimeComplexity("O(1)")
	public E removeFirst() throws IllegalArgumentException {
		/* TCJ
		 * The worst case of removeFirst method is O(1)
		 */
		if (isEmpty())
			return null;							// nothing to remove
		size--;
		return remove(header.getNext());
	}
	
	/* Removes and returns the last element of the DoublyLinkedList. */
	@TimeComplexity("O(1)")
	public E removeLast() throws IllegalArgumentException {
		/* TCJ
		 * The worst case of removeLast method is O(1)
		 */
		if (isEmpty())
			return null;							// nothing to remove
		size--;
		return remove(trailer.getPrev());
	}

}