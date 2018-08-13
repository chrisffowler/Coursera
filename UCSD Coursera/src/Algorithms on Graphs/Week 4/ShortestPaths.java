import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
    	reachable[s] = 1;
    	distance[s] = 0;
    	for (int i = 1; i < adj.length; i++) {
    		// relax all the edges |v| - 1 times
    		relaxEdges(adj, cost, distance, reachable);
    	}
    	// a temporary array to track the vertices that change distance with the |v|-th relax call
    	long[] temp = new long[distance.length];
    	for (int i = 0; i < distance.length; i++) {
    		temp[i] = distance[i];
    	}
    	relaxEdges(adj, cost, distance, reachable);
    	for (int i = 0; i < adj.length; i++) {
    		if (shortest[i] == 1 && temp[i] != distance[i]) {
    			// vertex i is part of a negative cycle and has not yet been processed,
    			// we will go through and detect all vertices reachable from i and mark them
    			// in shortest
    			Queue<Integer> queue = new LinkedList<Integer>();
    			queue.add(i);
    			while (!queue.isEmpty()) {
    				int next = queue.poll();
    				shortest[next] = 0;
    				for (int j = 0; j < adj[next].size(); j++) {
    					// go through the neighbors of the next vertex, adding them to the queue
    					// if they have not already been explored.
    					int to = adj[next].get(j);
    					if (shortest[to] == 1) {
    						queue.add(to);
    					}
    				}
    			}
    		}
    	}
    }
    
    private static void relaxEdges(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, long[] distance, int[] reachable) {
    	for (int i = 0; i < adj.length; i++) {
    		// look through the neighbors to this vertex only if it is already marked reachable
    		if (reachable[i] == 1) {
    			for (int j = 0; j < adj[i].size(); j++) {
    				long dist = distance[i] + cost[i].get(j);
    				int to = adj[i].get(j);
    				reachable[to] = 1;
    				if (distance[to] > dist) {
    					distance[to] = dist;
    				}
    			}
    		}
    	}
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

