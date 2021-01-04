//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/arrange-buildings-official/ojquestion
/*
Solution:
	This problem is similar to CountBinaryStrings problem
	We will find the number of ways to arrange buildings on one side of road.
	Then we multiple this number with itself to find total ways to arrange on both sides.
	
	If we ended with a building for n = k-1, then we cannot put a building for k+1.  "..b" then no "bb"
	If we ended with a space for n = k-1, then we can add a building or a space for k+1. "s" then "ss" or "sb"
	
	Complexity: O(n) time and O(1) space
 * */
package misc;

import java.util.Scanner;

public class ArrangeBuildings {
	
	public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int end_with_bldngs = 1;			//ways to arrange if there is a building at the end and n == 1
        int end_with_spaces = 1;			//ways to arrange if there is a space at the end and n == 1
        
        for(int i=2; i<=n; i++) {
            int new_end_with_bldngs = end_with_spaces;
            int new_with_spaces = end_with_bldngs + end_with_spaces;
            
            end_with_bldngs = new_end_with_bldngs;
            end_with_spaces = new_with_spaces;
        }
        int total = end_with_bldngs + end_with_spaces;  //total ways of arranging on one side of road
        System.out.println(total * total);              //for both sides, it will be total * total
    }

}
