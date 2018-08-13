import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int[] dist = new int[adj.length];
        boolean[] explored = new boolean[adj.length];
        Arrays.fill(explored, false);
        int next = 0; // integer to keep track of the next vertex to search with as source
        while (next != -1) {
        	boolean check = findNegativeCycle(adj, cost, dist, explored, next);
        	if (check) {
        		return 1;
        	}
        	next = -1;
        	for (int i = 0; i < adj.length; i++) {
        		if (!explored[i]) {
        			if (dist[i] != Integer.MAX_VALUE) {
        				// we just explored this vertex
        				explored[i] = true;
        			} else {
        				next = (next == -1) ? i : next;
        			}
        		}
        	}
        }
        // if we get here we've explored all of the graph with no negative cycles
        return 0;
    }
    
    private static boolean findNegativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, boolean[] explored, int s) {
    	for (int i = 0; i < adj.length; i++) {
        	dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        for (int i = 1; i < adj.length; i++) {
        	relaxEdges(adj, cost, dist, explored);
        }
        // relax edges one last time
        return relaxEdges(adj, cost, dist, explored);
    }

    private static boolean relaxEdges(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, boolean[] explored) {
    	boolean result = false;
    	for (int i = 0; i < adj.length; i++) {
    		// look through the neighbors to this vertex only if it has a real distance and has not been explored
    		if (!explored[i] && dist[i] != Integer.MAX_VALUE) {
    			for (int j = 0; j < adj[i].size(); j++) {
    				int distance = dist[i] + cost[i].get(j);
    				int to = adj[i].get(j);
    				if (dist[to] > distance) {
    					dist[to] = distance;
    					result = true;
    				}
    			}
    		}
    	}
    	return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}