import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
    	int[] colors = new int[adj.length];
        Queue<Integer> q = new LinkedList<Integer>();
        Arrays.fill(colors, -1);
        for (int i = 0; i < adj.length; i++) {
        	// if we haven't explored a vertex yet, we set its color to 0 and run
        	// a BFS to make sure it's connected component is bipartite
        	if (colors[i] == -1) {
        		colors[i] = 0;
        		q.add(i);
        		while (!q.isEmpty()) {
        			int curr = q.poll();
        			// all neighbors should have the other color
        			int nextColor = 1 - colors[curr];
        			for (int next : adj[curr]) {
        				if (colors[next] == -1) {
        					// if we haven't visited a neighbor yet, set its color 
        					// and add to queue
        					colors[next] = nextColor;
        					q.add(next);
        				} else if (colors[next] != nextColor) {
        					// if the color of a neighbor is already set and different than
        					// nextColor then the graph is not bipartite
        					return 0;
        				}
        			}
        		}
        	}
        }
        // if we color the graph with no problems it is bipartite
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}