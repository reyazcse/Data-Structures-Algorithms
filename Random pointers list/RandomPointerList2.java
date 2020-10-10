//https://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/

/*
Question:
	Given a linked list having two pointers in each node. The first one points to the next node of the list, however, the other pointer is random and can point to any node of the list. 
	Write a program that clones the given list in O(1) space, i.e., without any extra space.
 * */

/*
Solution:
	Put the nodes of the original list in a map along with cloned nodes having just the data.
	Then again iterate over the original list and adjust the next and random pointers of corresponding cloned node using nodes from the map.
	
	O(n) time and O(n) space for the map created
 * */
package leetcode;

import java.util.HashMap;



public class RandomPointerList2 {
	public Node copyRandomList(Node head) {
		HashMap<Node, Node> orgToCopyMap = new HashMap<>();
		Node curr = head, currCopy = null;
		
		//keep original node and a new cloned node with just 'val' part
		while(curr != null) {
			currCopy = new Node(curr.val);
			orgToCopyMap.put(curr, currCopy);
			curr = curr.next;
		}
		//adjust start of current node
		curr = head;
		
		//while iterating over original nodes, populate the next and random pointers
		while(curr != null) {
			currCopy = orgToCopyMap.get(curr);
			currCopy.next = orgToCopyMap.get(curr.next);
			currCopy.random = orgToCopyMap.get(curr.random);
			curr = curr.next;
		}
		return orgToCopyMap.get(head);
	}
	
	public static void main(String[] args) {
		Node head = new Node(7);
		
		head.next = new Node(13);
		
		head.next.next = new Node(11);
		
		head.next.next.next = new Node(10);

		head.next.next.next.next = new Node(1);
		
		head.random = null;
		head.next.random = head;
		head.next.next.random = head.next.next.next.next;
		head.next.next.next.random = head.next.next;
		head.next.next.next.next.random = head;
		
		RandomPointerList2 obj = new RandomPointerList2();
		Node copied = obj.copyRandomList(head);
		obj.print(copied);
	}

	private static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
	
	public void print(Node start) {
		System.out.println();
		while(start != null) {
			System.out.println("val " + start.val);
			start = start.next;
		}
	}

}
