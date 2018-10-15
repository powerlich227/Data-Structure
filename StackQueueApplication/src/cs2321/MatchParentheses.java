/**Author: Mingxiao Ye
 * 
 * Assignment 2
 * 
 * @parameter parenthesis 	A string of parentheses, such as "()", "()((()(())".
 * @return 					Check if the closing parentheses match the open parentheses in the correct order, that is "(" appears before ")".  
 */

package cs2321;

public class MatchParentheses<E> {

	public boolean match(String parenthesis) {
		final String opening = "(";											// opening parentheses
		final String closing = ")";											// closing parentheses
		ArrayListStack <Character> buffer = new ArrayListStack<>();
		
		for (char c : parenthesis.toCharArray()) {
			if (opening.indexOf(c) != -1) {									// left parenthesis
				buffer.push(c);
			}
			else if (closing.indexOf(c) != -1) {							// right parenthesis
				if (buffer.isEmpty()) {										// nothing to match with
					return false;
				}
				if (closing.indexOf(c) != opening.indexOf(buffer.pop())){	// mismatched parenthesis
					return false;
				}
			}
		}
		
		return buffer.isEmpty();											// all opening parenthesis matched
	}
}
