import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        int[][] dp = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
        	for (int j = 0; j < b.length; j++) {
        		dp[i][j] = -1;
        	}
        }
        return lcs(a, 0, b, 0, dp);
    }
    
    private static int lcs(int[] a, int s1, int[] b, int s2, int[][] dp) {
    	if (s1 == a.length || s2 == b.length) {
    		return 0;
    	}
    	if (dp[s1][s2] != -1) {
    		return dp[s1][s2];
    	}
    	int result = 0;
    	if (a[s1] == b[s2]) {
    		result = 1 + lcs(a, s1 + 1, b, s2 + 1, dp);
    	} else {
    		result = lcs(a, s1 + 1, b, s2 + 1, dp);
    	}
    	result = Math.max(result, lcs(a, s1 + 1, b, s2, dp));
    	result = Math.max(result, lcs(a, s1, b , s2 + 1, dp));
    	dp[s1][s2] = result;
    	return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

