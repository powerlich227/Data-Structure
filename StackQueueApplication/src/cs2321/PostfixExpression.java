/**Author: Mingxiao Ye
 * 
 * Assignment 2
 * 
 * Evaluate a postfix expression. 
 * postfix expression notation has operands first, following by the operations.
 * For example:
 *    13 5 *           is same as 13 * 5 
 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
 *    
 * In this homework, expression in the argument only contains
 *     integer, +, -, *, / and a space between every number and operation. 
 * You may assume the result will be integer as well. 
 * 
 * @parameter exp  The postfix expression
 * @return 		   The result of the expression
 */

package cs2321;

public class PostfixExpression {

	/* Determine if the string is number. */
	public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

	public int evaluate(String exp) throws Exception {
		int value = 0;
	    ArrayListStack<Integer> number = new ArrayListStack<>();
		
		String[] temp = exp.split(" ");								// splits this string by " " and stores as String Array
		
		for (int j = 0; j < temp.length; j++) {
			if (isNumeric(temp[j])) {							
				number.push(Integer.parseInt(temp[j]));				// copy the number from String Array to ArrayListStack
			}
			else {
				int number1 = number.pop();
				int number2 = number.pop();
				switch(temp[j]) {
					case "+":
						value = number2 + number1;
						break;
					case "-":
						value = number2 - number1;
						break;
					case "*":
						value = number2 * number1;
						break;
					case "/":
						if (number1 == 0){							// number1 can not be zero
							throw new Exception("invalid input");
						}
						value = number2 / number1;
						break;
				
					default:
						break;
				}
			number.push(value);
			}
		}
		return number.pop();
	}
}
