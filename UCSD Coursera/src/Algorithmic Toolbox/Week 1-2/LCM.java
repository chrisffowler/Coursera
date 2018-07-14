import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    return ((long) a * b) / gcd(a, b);
  }
  
  private static long gcd(int a, int b) {
	    long m = (a > b) ? a : b;
	    long q = (a > b) ? b : a;
	    while (m % q != 0) {
	    	long r = m %q;
	    	m = q;
	    	q = r;
	    }
	    return q;
	  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_naive(a, b));
  }
}
