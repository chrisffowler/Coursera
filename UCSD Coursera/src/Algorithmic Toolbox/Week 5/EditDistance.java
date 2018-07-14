import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    if (s.equals(t) || (s.length() == 0 && t.length() == 0)) {
    	return 0;
    }
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    return distance(s, t, dp);
  }
  
  private static int distance(String s, String t, int[][] dp) {
	  int n = s.length();
	  int m = t.length();
	  if (dp[n][m] != 0) {
		  return dp[n][m];
	  }

	  if (n == 0 || m == 0) {
		  dp[n][m] = Math.max(n, m);
		  return m;
	  }
	  int min = Integer.MAX_VALUE;
	  if (s.charAt(0) == t.charAt(0)) {
		  // if the first characters are the same we start by just looking at the rest
		  min = distance(s.substring(1), t.substring(1), dp);

	  } else {
		  // corresponds to a substitution
		  min = distance(s.substring(1), t.substring(1), dp) + 1;
	  }
	  // corresponds to delete and/or insertion
	  min = Math.min(min, distance(s.substring(1), t, dp) + 1);
	  min = Math.min(min, distance(s, t.substring(1), dp) + 1);
	  
	  dp[n][m] = min;
	  return min;
  }
  
  
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
