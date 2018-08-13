import java.util.*;

public class Dijkstra {
    static class Vertex implements Comparable<Vertex> {
    	int v; // vertex number
    	int d; // distance from source
    	public Vertex(int v, int d) {
    		this.v = v;
    		this.d = d;
    	}
    	
    	public int compareTo(Vertex o) {
    		return this.d - o.d;
    	}
    }
	
	private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
        	dist[i] = Integer.MAX_VALUE;
        	prev[i]	= -1;
        }
        dist[s] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(s, 0));
        while (!pq.isEmpty()) {
        	Vertex current = pq.poll();
        	int d = current.d;
        	int curr = current.v;
        	if (curr == t) {
        		// our target is now the next closest vertex, so we've calculated the distance
        		return d;
        	}
        	if (d == dist[curr]) {
        		// if the distance associated to this vertex differs from our record in dist
        		// then we have already explored this vertex
        		for (int i = 0; i < cost[curr].size(); i++) {
        			// iterate through all neighbors, if we have found a shorter path to a vertex,
        			// then we update dist, prev, and add the new point-distance pair to our
        			// priority queue
        			if (dist[adj[curr].get(i)] > d + cost[curr].get(i)) {
        				dist[adj[curr].get(i)] = d + cost[curr].get(i);
        				prev[adj[curr].get(i)] = curr;
        				pq.add(new Vertex(adj[curr].get(i), d + cost[curr].get(i)));
        			}
        		}
        	}
        	
        }
        
        // never found the target, so we return -1
        return -1;
        
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

