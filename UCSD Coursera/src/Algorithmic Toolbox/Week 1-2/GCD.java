import java.util.*;

public class GCD {
  private static int gcd(int a, int b) {
    int m = (a > b) ? a : b;
    int q = (a > b) ? b : a;
    while (m % q != 0) {
    	int r = m %q;
    	m = q;
    	q = r;
    }
    return q;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd(a, b));
  }
}
