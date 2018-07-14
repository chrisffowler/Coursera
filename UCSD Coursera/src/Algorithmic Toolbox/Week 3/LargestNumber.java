import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        Arrays.sort(a,  new StringComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = a.length - 1; i >= 0; i--) {
        	sb.append(a[i]);
        }
        return sb.toString();
    }
    
    static class StringComparator implements Comparator<String> {
        @Override public int compare(String a, String b) {
            return (a+b).compareTo(b+a);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

