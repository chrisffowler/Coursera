import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    long last = 0;
    long now = 1;
    for (int i = 2; i <= n; i++) {
    	now = now + last;
    	last = now - last;
    }
    return now;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
