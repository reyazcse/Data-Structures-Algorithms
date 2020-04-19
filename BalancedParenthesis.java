/*
Question:
	Given a string of parenthesis and alphabets, digits (a(0)) or (#($)*). Remove the minimum number of parenthesis to get balanced expression.
For example:
	Input: "(a)())()"
	Output: ["(a)()()", "(a())()"]

Solution:
	We solve this using BFS.
	In the queue, we add strings by removing parenthesis at each position.
	If we find one balanced expression, then we look for other expressions stored currently on the queue, rather than going to store other exprssions on queue
 * */
package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BalancedParenthesis {
	public static List<String> minRemovalOfParentheis(String str) {
		List<String> result = new ArrayList<>();
		Map<String, Boolean> visited = new HashMap<>();
		Queue<String> q = new LinkedList<>();
		q.add(str);
		boolean found = false;
		String expr = null;
		
		//BFS algo
		while (!q.isEmpty()) {
			expr = q.remove();
			if(visited.containsKey(expr) && visited.get(expr))
				continue;
			//mark this expression as visited
			visited.put(expr, true);
			
			//if balanced expression is found, add to result and continue removing from queue
			if (isBalanced(expr)) {
				found = true;
				result.add(expr);
			} 
				
			if(found)
				continue;
			
			//below is not executed if we find a balanced expression above
			//storing strings by removing parenhesis at ith position.
			for(int i=0; i<expr.length(); i++) {
				if (expr.charAt(i) == '(' || expr.charAt(i) == ')') {
					String subExpr = expr.substring(0,i) + expr.substring(i+1, expr.length());
					q.add(subExpr);
				}
				
			}
		}
		return result;
	}
	public static boolean isBalanced(String expr) {
		int count = 0;
		for(int i=0; i<expr.length(); i++) {
			if (expr.charAt(i) == '(') count++;
			else if (expr.charAt(i) == ')') {
				count--;
				if (count < 0)
					return false;
			}
		}
		return count == 0;
	}
	
	public static void main(String [] args) {
		String expr = "(a)())()";
		List<String> balancedExpressions = minRemovalOfParentheis(expr);
		for (String str: balancedExpressions) {
			System.out.println(str);
		}
	}
}
