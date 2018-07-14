import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Test {
    public static void main(String args[] ) throws Exception {
    	Scanner in = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        String s = in.nextLine();
        // check that the first line is not blank
        if (s.length() == 0) {
        	System.out.println("failure");
        	return;
        }
        do {
        	// process lines until a blank line is entered, if a line cannot
        	// be converted to an integer then "failure" is printed to STDOUT
        	try {
        		int n = Integer.parseInt(s);
        		list.add(n);
        	} catch (NumberFormatException e) {
        		System.out.println("failure");
        		in.close();
        		return;
        	}
        	if(in.hasNextLine()) {
        		s = in.nextLine();
        	} else {
        		break;
        	}
        } while (s.length() > 0);
        in.close();
        int[] result = calculate(list);
        if (result == null) {
        	System.out.println("failure");
        } else {
        	StringBuilder sb = new StringBuilder();
        	for (int i = 0; i < result.length; i++) {
        		sb.append("" + result[i] + ", ");
        	}
        	sb.append("out");
        	System.out.println(sb.toString());
        }
    }
    
    
    // Takes a List of integers corresponding allowable flight lengths from each site
    // returns an array corresponding to a legal path out of the dungeon, or null if
    // no such path exists. Effectively treats negative numbers passed in the list like
    // 0s -- they contain dragons and cannot be exited
    private static int[] calculate(List<Integer> list) {
    	int n = list.size();
    	int[][] dp = new int[n + 1][2];
    	// dp[i][0] stores length of shortest path to index i
    	// dp[i][1] stores the previous index
    	for (int row = 0; row <= n; row++) {
    		dp[row][0] = 0;
    		dp[row][1] = 0;
    	}
    	
    	int farthest = 0;
    	for (int i = 0; i < n; i++) {
    		// iterate over all indices and consider every site that can be reached
    		if (list.get(i) != 0 && (i == 0 || dp[i][0] > 0) && i + list.get(i) > farthest) {
    			// if the value at index i is 0 it's corresponds to a dragon and we cannot
    			// land/leave there, and if i > 0 with dp[i][0] == 0 then we can't reach it
    			for (int j = 1; j <= list.get(i) && i+j <= n; j++) {
    				// consider all valid jump lengths, noting we can't jump past index n,
    				// corresponding to escaping the canyon
    				if (dp[i+j][0] == 0) {
    					// if index i+j has been visited before, it was by an early index,
    					// so the length will necessarily be smaller. If not then we record
    					// the path from i
    					dp[i+j][0] = dp[i][0] + 1;
    					dp[i+j][1] = i;
    				}
    				if (i+j == n) {
    					break;
    				}
    			}
    			farthest = list.get(i) + i;
    		}
    		// if we've visited n we're done.
    		if (i + list.get(i) >= n) {
    			break;
    		}
    	}
    	if (dp[n][0] == 0) {
    		// we never reached index n
    		return null;
    	} else {
    		// we reached it and construct an array corresponding to the path
    		return makeResult(dp);    		
    	}
    }
    
    
    // takes the a 2D array corresponding to a valid path out of the dungeon
    // returns an array corresponding to the sites visited in one of the shortest
    // paths out
    private static int[] makeResult(int[][] dp) {
    	int n = dp.length - 1;
    	int[] result = new int[dp[n][0]]; // records all the indices before escape
		int site = dp[n][1];
		int index = result.length - 1;
		// start at the ends of the result array and the path out, tracing back to the start
		while (index >= 0) {
			result[index] = site;
			index--;
			site = dp[site][1];
		}
		return result;
    }
    
}
