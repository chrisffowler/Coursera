import java.util.*;

public class Inversions {
	private static long merge(int[] a, int[] b, int left, int mid, int right) {
		long count = 0;
		int i = left, j = mid, next = left;
		while (i < mid && j < right) {
			if (a[i] <= a[j]) {
				b[next] = a[i];
				i++;
			} else {
				b[next] = a[j];
				count += mid - i;
				j++;
			}
			next++;
		}
		while (i < mid) {
			b[next] = a[i];
			next++;
			i++;
		}
		// if j < right then they're already in the order we need
		for (i = left; i < j; i++) {
			a[i] = b[i];
		}
		return count;
        
	}
	
	
	
    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        numberOfInversions += merge(a,b, left, ave, right);
        
        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

