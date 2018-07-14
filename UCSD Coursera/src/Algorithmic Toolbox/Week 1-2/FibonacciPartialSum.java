import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
    	long result = getFibonacciSum(to) - getFibonacciSum(from-1);
        // Could have cached the second call to FibonacciSum to potentially
        // cut the run time in half
        if (result < 0) {
        	result += 10;
        }
        return result;
    }
    
    // return last digit of the sum of first n Fibonacci numbers
    private static long getFibonacciSum(long n) {
        if (n < 0)
        	return 0;
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
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumNaive(from, to));
    }
}

