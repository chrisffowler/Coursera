import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int max = 0;
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        for (int i = 0; i < w.length; i++) {
        	List<Integer> toAdd = new LinkedList<>();
        	for (int sum : visited) {
        		if (sum + w[i] <= W && !visited.contains(sum + w[i])) {
        			toAdd.add(sum + w[i]);
        			max = Math.max(max, sum + w[i]);
        			if (max == W) {
        				return max;
        			}
        		}
        	}
        	visited.addAll(toAdd);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

