import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {
	
    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        Arrays.sort(starts);
        Arrays.sort(ends);
    	int[] cnt = new int[points.length];
    	
    	for (int i = 0; i < points.length; i++) {
            cnt[i] =  lessThan(points[i], starts) + moreThan(points[i], ends) - starts.length;
        }
        return cnt;
    }
    
    private static int lessThan(int value, int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while (right >= left) {
            int m = (left+right) / 2;
            if (value >= arr[m]) {
                left = m+1;
            } else {
                right = m - 1;
            }
        }
        return left;
    }
    
    private static int moreThan(int value, int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while (right >= left) {
            int m = (left+right) / 2;
            if (value > arr[m]) {
                left = m+1;
            } else {
                right = m - 1;
            }
        }
        return arr.length - right - 1;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

