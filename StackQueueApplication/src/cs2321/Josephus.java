/**Author: Mingxiao Ye
 * 
 * Assignment 2
 *  
 * All persons sit in a circle. 
 * When we go around the circle, initially starting from the first person, then the second person, then the third... 
 * We count 1,2,3,..k. The k-th person is out.
 * Then we restart the counting from the next person, go around, the k-th person is out.
 * Keep going the same way, when there is only one person left, she/he is the winner. 
 *  
 * @parameter persons  	An array of string which contains all player names.
 * @parameter k  		An integer specifying the k-th person will be kicked out of the game
 * @return 				Return a doubly linked list in the order when the players were out of the game. The last one in the list is the winner.  
 */

package cs2321;

public class Josephus {
		
	public DoublyLinkedList<String> order(String[] persons, int k) {
		
		CircularArrayQueue<String> data = new CircularArrayQueue<>(persons.length);			// generic CircularArrayQueue to store the input
		DoublyLinkedList<String> output = new DoublyLinkedList<>();							// generic DoublyLinkedList to return the ordered output
		int index = 1;																		// index of the first element
		
		/* Build a circular queue from array of string. */
		for (int i = 0; i < persons.length; i++) {											
			data.enqueue(persons[i]);
		}
		
		if (data.isEmpty()) {
			return null;
		}
	
		while (data.size() >= 1) {
			if (index == k) {
				String next = data.dequeue();												// remove the kth element from the CircularArrayQueue
				output.addLast(next);														// add to the DoublyLinkedList output
				index = 1;
			}
			else {
				String next = data.dequeue();												// remove the non-kth element from the CircularArrayQueue
				data.enqueue(next);															// rejoin the CircularArrayQueue
				index++;
			}
		}
		return output;
	}
}