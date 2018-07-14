import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
    	int[][][] dp = new int[a.length][b.length][c.length];
        for (int i = 0; i < a.length; i++) {
        	for (int j = 0; j < b.length; j++) {
        		for (int k = 0; k < c.length; k++) {
        			dp[i][j][k] = -1;
        		}
        	}
        }
        return lcs(a, 0, b, 0, c, 0, dp);
    }
    
    private static int lcs(int[] a, int s1, int[] b, int s2, int[] c, int s3, int[][][] dp) {
    	if (s1 == a.length || s2 == b.length || s3 == c.length) {
    		return 0;
    	}
    	if (dp[s1][s2][s3] != -1) {
    		return dp[s1][s2][s3];
    	}
    	int result = 0;
    	if (a[s1] == b[s2] && b[s2] == c[s3]) {
    		result = 1 + lcs(a, s1 + 1, b, s2 + 1, c, s3 + 1, dp);
    	} else {
    		result = lcs(a, s1 + 1, b, s2 + 1, c, s3 + 1, dp);
    	}
    	result = Math.max(result, lcs(a, s1 + 1, b, s2, c, s3, dp));
    	result = Math.max(result, lcs(a, s1, b, s2 + 1, c, s3, dp));
    	result = Math.max(result, lcs(a, s1, b, s2, c, s3 + 1, dp));
    	dp[s1][s2][s3] = result;
    	return result;
    }
        

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

