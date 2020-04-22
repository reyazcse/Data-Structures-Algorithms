/*
Question source: https://code.google.com/codejam/contest/5264486/dashboard#s=p2 
	Given a dictionary  ["this", "is", "a", "monster", "retsnom"] for example, and an enciphered string "ishtsiarestmon", there are 
	four there are four possible original sentences:
	"is this a monster"
	"is this a retsnom"
	"this is a monster"
	"this is a retsnom".
	
	We have to find the number of possible sentences.
 * */
package misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Sentences {
	//dictionary to store the given words. it stores a word and its count. Each word is sorted
	private Map<String, Integer> mp = new HashMap<String, Integer>();
	//longestWd in the dictionary
	private int longestWd = 0;
	
	public int totalSentences(String str) {
		int[]dp = new int [str.length()+1];
		for(int i=0; i<=str.length(); i++) 
			dp[i] = -1;
		totalSentencesUtl(str, 0, dp);
		return dp[0];						//dp[0] gives the number of results
	}
	
	public int totalSentencesUtl(String str, int index, int []dp) {
		if (index == str.length())	return 1;
		if (dp[index] != -1) return dp[index];
		int total = 0;
		int cnt = 0;
		int to = index + longestWd + 1;
		to = Math.min(to, str.length());  //we need to move till the longest word as any word longer than this wont be in dictionary
		for(int i=index; i<to; i++) {
			String substr = str.substring(index, i+1);
			char [] chararr = substr.toCharArray(); 
			Arrays.sort(chararr);
			substr = new String(chararr);
			//if str[index...i] is found in the dictionary, then find solutions for string i+1 to str.length();
			if(mp.containsKey(substr)) {
				cnt = mp.get(substr);
				total += cnt * totalSentencesUtl(str, i+1, dp);
			}
			
		}
		dp[index] = total;
		return total;
	}
	
	public static void main(String[] args) {
		Sentences obj = new Sentences();
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.nextLine());   //no of test cases
		char [] charArr = null;
		String str = null;
		//taking input
		while (t > 0) {
			String [] val = sc.nextLine().split(" ");
			int n = Integer.parseInt(val[0]);			//no of words in the dictionary
			int s = Integer.parseInt(val[1]); 			//no of queries
			for(int i=0; i<n; i++) {
				//store the count of word after sorting it
				charArr = sc.nextLine().toCharArray();
				Arrays.sort(charArr);
				str = new String(charArr);
				
				Object longestWd;
				obj.longestWd = Math.max(obj.longestWd, str.length());
				if (!obj.mp.containsKey(str)) {
					obj.mp.put(str, 1);
				} else {
					obj.mp.put(str, obj.mp.get(str)+1);
				}
			}
			while (s > 0) {
				String queryStr = sc.nextLine();
				System.out.println(obj.totalSentences(queryStr) + "  ");
				s--;
			}
			t--;
			obj.mp.clear();
			obj.longestWd = 0;
		}
	}
	
	
}
