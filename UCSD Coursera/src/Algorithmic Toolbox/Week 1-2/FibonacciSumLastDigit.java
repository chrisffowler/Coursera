import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSum(long n) {
        if (n <= 2)
            return n;
        // there are only a finite number of combinations of consecutive
        // last digits, it must repeat, and it must start back at the beginning
        // due to uniqueness
        long prev = 0;
        long curr = 1;
        long sum  = 1;
        long[] sums = new long[100];
        sums[0] = prev;
        sums[1] = curr;
        int count = 1;
        while (count < n && (prev != 1 || curr != 0)) {
        	count++;
        	long temp = (prev + curr) % 10;
            prev = curr;
            curr = temp;
            sum = (sum + curr) % 10;
            sums[count] = sum;
        }
        if (count == n) {
        	return sum;
        }
        
        return sums[(int)(n % count)];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);
    }
}

