import java.util.*;

public class FibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 2)
            return n;


        long[] fibs = new long[100];
        fibs[0] = (long) 0;
        fibs[1] = (long) 1;
        fibs[2] = (long) 1;
        int count = 1;
        while (count < n && (fibs[count-1] != 1 || fibs[count] != 0)) {
        	count++;
        	fibs[count + 1] = (fibs[count-1] + fibs[count]) % 10;
        }
        if (count == n) {
        	return fibs[count]*fibs[count + 1] % 10;
        }
        int index = (int) (n % (long) count);
        return fibs[index]*fibs[index + 1] % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquaresNaive(n);
        System.out.println(s);
    }
}

