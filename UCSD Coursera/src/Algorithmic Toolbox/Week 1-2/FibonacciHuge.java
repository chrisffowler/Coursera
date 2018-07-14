import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;
        
        long prev = 0;
        long curr  = 1;
        long count = 1;
        HashMap<Long,Long> map = new HashMap<>();
        map.put((long) 0, prev);
        map.put((long) 1, curr);
        while (count < n && (prev != 1 || curr != 0)) {
        	long temp = (prev + curr) % m;
        	prev = curr;
        	curr = temp;
        	count++;
        	map.put(count, curr);
        }
        if (count == n) {
        	return curr;
        }
        return map.get(n % count);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}

