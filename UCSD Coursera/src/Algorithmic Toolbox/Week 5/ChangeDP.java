// Following the dynamic programming outline suggested by the course,
// though there's definitely a faster method
import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] dp = new int[m+1];
        return getChange(m, dp);
    }
    
    private static int getChange(int m, int[] dp) {
    	if (dp[m] != 0) {
    		return dp[m];
    	}
    	if (m == 1 || m == 3 || m == 4) {
    		dp[m] = 1;
    		return 1;
    	}
    	if (m ==2) {
    		dp[m] = 2;
    		return 2;
    	}
    	dp[m] = Math.min(getChange(m- 4, dp), getChange(m-3, dp)) + 1;
    	return dp[m];
    	
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

