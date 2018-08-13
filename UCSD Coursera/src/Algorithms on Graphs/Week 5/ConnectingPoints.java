import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;



public class ConnectingPoints {
	private static double minimumDistance(int[] x, int[] y) {
    	double[] cost = new double[x.length];
    	for (int i = 0; i < x.length; i++) {
    		cost[i] = Double.MAX_VALUE;
    	}
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(x.length, new Comparator<Integer>() {
    		@Override public int compare(Integer a, Integer b) {
    			if (cost[a] == Double.MAX_VALUE) {
    				if (cost[b] == Double.MAX_VALUE) {
    					return 0;
    				} else {
    					return 1;
    				}
    			}
    			if (cost[a] + 0.00000001 < cost[b]) {
    				return -1;
    			} else if (cost[b] + 0.00000001 < cost[a]) {
    				return 1;
    			} else {
    				return 0;
    			}
    		}
    	});
    	cost[0] = 0.0;
    	for (int i = 1; i < x.length; i++) {
    		cost[i] = length(x, y, 0, i);
    		pq.add(i);
    	}
    	while (!pq.isEmpty()) {
    		int u = pq.poll();
    		Queue<Integer> temp = new LinkedList<Integer>();
    		while (!pq.isEmpty()) {
    			int v = pq.poll();
    			cost[v] = Math.min(cost[v], length(x, y, u, v));
    			temp.add(v);
    		}
    		while(!temp.isEmpty()) {
    			pq.add(temp.poll());
    		}
    	}
    	double result = 0.0;
    	for (double d : cost) {
    		result += d;
    	}
    	return result;
    }
    
    private static double length(int[] x, int[] y, int i, int j) {
    	double dx = (double) x[i] - x[j];
    	double dy = (double) y[i] - y[j];
    	return Math.sqrt(dx*dx + dy*dy);
    }
	
	
	
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}