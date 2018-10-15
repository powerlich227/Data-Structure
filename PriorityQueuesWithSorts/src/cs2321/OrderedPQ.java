package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Mingxiao Ye
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {

	/** Nested PQEntry Class. */
	protected static class PQEntry<K,V> implements Entry<K,V> {
		private K k;						
		private V v;						
		
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		
		@Override
		public K getKey() {
			return k;
		}
		@Override
		public V getValue() {
			return v;
		}
	}
	/** End of Nested PQEntry Class. */
	
	private DoublyLinkedList<Entry<K, V>> list = new DoublyLinkedList<>();
	private Comparator<K> comp;												// the comparator define the order of keys in the PQ.
	
	/* Creates an empty PQ based on the natural ordering of its keys. */
	public OrderedPQ() {
		this(new DefaultComparator<K>());
	}
	
	/* Creates an empty PQ using the given comparator to order keys.*/
	public OrderedPQ(Comparator<K> c) {
		comp = c;
	}
	
	public boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return comp.compare(key, key) == 0;
		}
		catch(ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return list.size();
	}
	
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/* Inserts a key value pair and returns the entry created. */
	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		
		Entry<K,V> entry = new PQEntry<>(key, value);
		Position<Entry<K, V>> test = list.last();
		
		while(test != null && comp.compare(entry.getKey(), test.getElement().getKey()) < 0) {
			test = list.before(test);
		}
		if(test == null) {
			list.addFirst(entry);
		}
		else {
			list.addAfter(test, entry);
		}
		return entry;
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		// TODO Auto-generated method stub
		if(list.isEmpty()) {
			return null;
		}
		return list.first().getElement();
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> removeMin() {
		// TODO Auto-generated method stub
		if(list.isEmpty()) {
			return null;
		}
		return list.remove(list.first());
	}

}
