//https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/
/*
Question:
	Given a linked list having two pointers in each node. The first one points to the next node of the list, however, the other pointer is random and can point to any node of the list. 
	Write a program that clones the given list in O(1) space, i.e., without any extra space.
 * */

/*
1.	Iterate over the original list and create a copy of each node and place that 
	copied node after each original node.
2.  Iterate again and update random pointers of each copied node using random pointer of original node.
	Let copied node be 'copied' and original node be 'org'. Then
	copied.random = org.random.next.
3.  Iterate again and separate the original and copied nodes.
 * */
package leetcode;

public class RandomPointerList {
	public Node copyRandomList(Node head) {
		Node start = head;
		Node temp = null;
		//create copy node of each node
		while(start != null) {
			temp = start.next;
			start.next = new Node(start.val);
			start.next.next = temp;
			start = start.next.next;
		}
		
		//fixing random pointer of each copied node
		start = head;
		while(start != null && start.next != null) {
			start.next.random = start.random!= null ? start.random.next : null;
			start = start.next.next;
		}
		
		//separating original and copied nodes
		Node startOrg = head;
		Node startCp = head.next;
		Node startCp2 = head.next;
		while(startCp.next != null) {
			startOrg.next = startOrg.next.next;
			startCp.next = startCp.next.next;
			startOrg = startOrg.next;
			startCp = startCp.next;
		}
		startOrg.next = null;			//fixing next ptr of last node
		return startCp2;
	}
	public void print(Node start) {
		System.out.println();
		while(start != null) {
			System.out.println("val " + start.val);
			start = start.next;
		}
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
		
		RandomPointerList obj = new RandomPointerList();
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
	
	

}
