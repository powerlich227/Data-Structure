package cs2321;

import java.util.Comparator;

import cs2321.OrderedPQ.PQEntry;
import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Mingxiao Ye
 */

public class UnorderedPQ<K,V> implements PriorityQueue<K,V> {
	
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
	
	private DoublyLinkedList<Entry<K,V>> list = new DoublyLinkedList<>();
	private Comparator<K> comp;	

	public UnorderedPQ() {
		this(new DefaultComparator<K>());
	}
	
	public UnorderedPQ(Comparator<K> c) {
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

	/* Inserts a key-value pair and returns the entry created. */
	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> entry = new PQEntry<>(key, value);
		list.addLast(entry);
		return entry;
	}

	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> min() {
		if(list.isEmpty()) {
			return null;
		}
		
		Position<Entry<K,V>> minimum = list.first();
		for (Position <Entry<K,V>> temp : list.positions()) {
			if (comp.compare(temp.getElement().getKey(), minimum.getElement().getKey()) < 0) {
				minimum = temp;
			}
		}
		return minimum.getElement();
	}

	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) {
			return null;
		}
		
		Position<Entry<K,V>> minimum = list.first();
		for(Position <Entry<K,V>> temp : list.positions()) {
			if (comp.compare(temp.getElement().getKey(), minimum.getElement().getKey()) < 0) {
				minimum = temp;
			}
		}
		return list.remove(minimum);
	}
	
}
