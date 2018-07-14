import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        int mid = (left + right) / 2;
        int first = getMajorityElement(a, left, mid);
        int second = getMajorityElement(a, mid, right);
        if (first == second) {
        	// if they're both -1 there is no majority, if they're both the same int
        	// then we know it's a majority element
        	return first;
        }
        if (first != -1 && verifyMajority(a, left, right, first)) {
        	return first;
        }
        if (second != - 1 && verifyMajority(a, left, right, second)) {
        	return second;
        }
        // if all of these failed no majority element
        return -1;
    }
    
    private static boolean verifyMajority(int[] a, int left, int right, int val) {
    	int count = 0;
    	for (int i = left; i < right; i++) {
    		if (a[i] == val) {
    			count++;
    		}
    	}
    	return count > (right - left) / 2;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

