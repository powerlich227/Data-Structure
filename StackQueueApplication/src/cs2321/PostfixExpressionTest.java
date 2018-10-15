/**Author: Mingxiao Ye
 * 
 * Assignment 2
 * 
 * Create 5 representative test cases.
 *
 */

package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PostfixExpressionTest {
	
	private static PostfixExpression postfix;
	
	@Before
	public void setUp() throws Exception {
		postfix = new PostfixExpression();
	}

	@Test
	public void testEvaluate1() throws Exception {
		assertEquals("No.1", 3, postfix.evaluate("3"));
	}
	
	@Test
	public void testEvaluate2() throws Exception {
		assertEquals("No.2", 7, postfix.evaluate("3 4 +"));
	}
	
	@Test
	public void testEvaluate3() throws Exception {
		assertEquals("No.3", 27, postfix.evaluate("3 4 5 + *"));
	}
	
	@Test
	public void testEvaluate4() throws Exception {
		assertEquals("No.4", 21, postfix.evaluate("3 4 5 + * 6 -"));
	}

	@Test
	public void testEvaluate5() throws Exception {
		assertEquals("No.5", 3, postfix.evaluate("3 4 5 + * 6 - 7 /"));
	}
	
	@Test
	public void testEvaluate6() throws Exception {
		assertEquals("No.6", 3, postfix.evaluate("18 6 /"));
	}

}
