package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Mingxiao Ye
 */

public class HeapPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	
	/** Nested HeapPQEntry Class. */
	protected static class APQEntry<K, V> implements Entry<K, V> {
		private K k;						
		private V v;
		private int i;
		
		public APQEntry(K key, V value, int index) {
			k = key;
			v = value;
			i = index;
		}
		
		@Override
		public K getKey() {
			return k;
		}
		public void setKey(K key) {
			k = key;
		}
		@Override
		public V getValue() {
			return v;
		}
		public void setValue(V value) {
			v = value;
		}
		public int getIndex() {
			return i;
		}
		public void setIndex(int index) {
			i = index;
		}	
	}
	/** End of Nested APQEntry Class. */
	
	private Comparator<K> comp;
	private ArrayList<APQEntry<K, V>> heap = new ArrayList<>();
	
	protected int parent(int j) {
		return (j - 1) / 2;
	}
	protected int left(int j) {
		return 2 * j + 1;
	}
	protected int right(int j) {
		return 2 * j + 2;
	}
	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}
	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}
	protected void swap(int i, int j) {
		APQEntry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
		heap.get(i).setIndex(i);
		heap.get(j).setIndex(j);
	}
	
	/* Restores the heap property by moving the entry at index j upward/downward.*/
	@TimeComplexity("O(lg n)")
	protected void bubble(int i) {
		if(i > 0 && comp.compare(heap.get(i).getKey(), heap.get(parent(i)).getKey()) < 0) {
			upheap(i);
		}
		else
			downheap(i);
	}
	
	/* Validate an entry to ensure it is location aware. */
	protected APQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
		if (!(entry instanceof APQEntry)) {
			throw new IllegalArgumentException("Invalid entry");
		}
		APQEntry<K, V> locator = (APQEntry<K, V>) entry;
		int i = locator.getIndex();
		if(i >= heap.size() || heap.get(i) != locator) {
			throw new IllegalArgumentException("Invalid entry");
		}
		return locator;
	}
	
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return comp.compare(key, key) == 0;
		}
		catch(ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}	
	}
	
	public HeapPQ() {
		this(new DefaultComparator<K>());
	}
	
	public HeapPQ(Comparator<K> c) {
		comp = c;
	}
	
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param int move the entry at index j higher if necessary, to restore the heap property
	 */
	@TimeComplexity("O(lg n)")
	public void upheap(int j){
		//TODO: implement this method
		while (j > 0) {
			int p = parent(j);
			if (comp.compare(heap.get(j).getKey(),heap.get(p).getKey()) >= 0) {
				break;
			}
			swap(j, p);
			j = p;
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	@TimeComplexity("O(lg n)")
	public void downheap(int j){
		while(hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if(hasRight(j)) {
				int rightIndex = right(j);
				if(comp.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if(comp.compare(heap.get(smallChildIndex).getKey(), heap.get(j).getKey()) >= 0) {
				break;
			}
			swap(j,smallChildIndex);
			j = smallChildIndex;
		}
	}
	
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return heap.size();
	}
	
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return heap.size() == 0;
	}

	/* Inserts a key-value pair and returns the entry created. */
	@TimeComplexity("O(lg n)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		APQEntry<K, V> entry = new APQEntry<>(key, value, heap.size());
		heap.addLast(entry);
		upheap(heap.size() - 1);
		return entry;
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		if(heap.isEmpty()) {
			return null;
		}
		return heap.get(0);
	}
	
	@TimeComplexity("O(lg n)")
	@Override
	public Entry<K, V> removeMin() {
		if(heap.isEmpty()) {
			return null;
		}
		Entry<K, V> minimum = heap.get(0);
		swap(0, heap.size()-1);
		heap.removeLast();
		downheap(0);
		return minimum;
	}

	@TimeComplexity("O(lg n)")
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		APQEntry<K, V> locator = validate(entry);
		int i = locator.getIndex();
		
		if(i == heap.size() - 1) {
			heap.removeLast();
		}
		else {
			swap(i, heap.size() - 1);
			heap.removeLast();
			bubble(i);
		}
	}

	/* Replaces the key of an entry. */
	@TimeComplexity("O(lg n)")
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		APQEntry<K, V> locator = validate(entry);
		checkKey(key);										// might throw an exception
		locator.setKey(key);
		bubble(locator.getIndex());							// might move entry
	}

	/* Replaces the value of an entry. */
	@TimeComplexity("O(1)")
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		APQEntry<K, V> locator = validate(entry);
		locator.setValue(value);
	}
}
