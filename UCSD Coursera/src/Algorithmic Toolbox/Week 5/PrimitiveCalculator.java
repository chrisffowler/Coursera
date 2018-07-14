import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        int[][] dp = new int[n+1][2];
        if (n > 1) {
        	dp[2][0] = 1;
        	dp[2][1] = 1;
        }
        if (n > 2) {
        	dp[3][1] = 1;
            dp[3][0] = 1;
        }
        for (int i = 2; i < n; i++) {
        	if (i*2 <= n && (dp[i*2][0] == 0 || dp[i*2][0] > dp[i][0]+1 )) {
        		dp[i*2][0] = dp[i][0] +1;
        		dp[i*2][1] = i;
        	}
        	if (i*3 <= n && (dp[i*3][0] == 0 || dp[i*3][0] > dp[i][0]+1 )) {
        		dp[i*3][0] = dp[i][0] +1;
        		dp[i*3][1] = i;
        	}
        	if (dp[i+1][0] == 0 || dp[i+1][0] > dp[i][0]+1) {
        		dp[i+1][0] = dp[i][0] +1;
        		dp[i+1][1] = i;
        	}
        }
        List<Integer> result = new LinkedList<>();
        while (n > 0) {
        	result.add(n);
        	n = dp[n][1];
        }
        Collections.reverse(result);
        return result;
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

