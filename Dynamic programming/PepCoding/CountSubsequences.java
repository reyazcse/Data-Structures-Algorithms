//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/count-a+b+c+subsequences-official/ojquestion
/*
1. You are given a string str.
2. You are required to calculate and print the count of subsequences of the nature a+b+c+.
For abbc -> there are 3 subsequences. abc, abc, abbc
For abcabc -> there are 7 subsequences. abc, abc, abbc, aabc, abcc, abc, abc.

 * */

/*
Solution:
	Suppose subsequence formed so far using given string is ending with
	1. a for example a, aa, aaa, etc				:type 1
	2. b for example, aab, ab, abb, etc				:type 2
	3  c for example, abc, aabc, abbc, abbcccc, etc :type 3 

	Now if have character == 'a' to append to the subsequence formed so far, we can append it to subsequences ending with a or we may not append it
	or we can start new subsequence of type 1 with this character. Hence number of subsequences of type 1 = 2 * previous count of type 1 + 1

	If the character is 'b', then we can append it to type 2 subsequences or we may not append. Note that we cannot start a new subsequence with this
	character 'b' since it will not be of type 2. Also we can append it to type 1 subsequences;
	Hence number of subsequences of type 2 = 2 * previous count of type 2 + previous count of type 1 

	If the character is 'c', then we can append it to type 3 subsequences or we may not append. Note that we cannot start a new subsequence with this
	character 'b' since it will not be of type 2. Also we can append it to type 2 subsequences;
	Hence number of subsequences of type 3 = 2 * previous count of type 3 + previous count of type 2

	See pepcoding video for explanation.

 * */

package misc;

import java.util.Scanner;

public class CountSubsequences {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int n = str.length();
		int end_a = 0;								//number of subsequences of form "a+"
		int end_b = 0;								//number of subsequences of form "a+b+"
		int end_c = 0;								//number of subsequences of form "a+b+c+"
		for (int i = 0; i < n; i++) {
			char ch = str.charAt(i);
			if (ch == 'a') {
				end_a = 2 * end_a + 1;		//we can add 'a' or not add it or just start a new subsequence
			} else if (ch == 'b') {
				end_b = 2 * end_b  + end_a;
			} else if (ch == 'c') {
				end_c = 2 * end_c + end_b;
			}

		}
		System.out.println(end_c);

	}

}
