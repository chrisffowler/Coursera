import java.util.Scanner;

public class PlacingParentheses {
	private static long getMaximValue(String exp) {
        //write your code here
        long[] ints = new long[(exp.length() + 1) / 2];
        char[] ops = new char[exp.length() / 2 ];
        for( int i = 0; i < exp.length(); i++) {
            if (i % 2 == 0) {
                ints[ i / 2 ] = Long.parseLong(exp.substring(i,i+1));
            } else {
                ops[ i / 2] = exp.charAt(i);
            }
        }
        int n = ints.length;
            
        long[][] m = new long[n][n];
        long[][] M = new long[n][n];
        for (int i = 0; i < n; i++) {
            m[i][i] = ints[i];
            M[i][i] = ints[i];
        }
        
        for (int s = 1; s < n; s++) {
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                long max = Long.MIN_VALUE;
                long min = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    long a = eval(M[i][k], M[k+1][j], ops[k]);
                    long b = eval(M[i][k], m[k+1][j], ops[k]);
                    long c = eval(m[i][k], M[k+1][j], ops[k]);
                    long d = eval(m[i][k], m[k+1][j], ops[k]);
                    max = Math.max(Math.max(max, Math.max(a,b)), Math.max(c,d));
                    min = Math.min(Math.min(min, Math.min(a,b)), Math.min(c,d));
                }
                m[i][j] = min;
                M[i][j] = max;
                
            }
        }
        
        return M[0][n-1];
        
        
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

