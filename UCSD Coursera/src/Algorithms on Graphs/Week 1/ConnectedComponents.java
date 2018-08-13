import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
    	int current = 0;  // records the connected component we're currently exploring
    	Queue<Integer> q = new LinkedList<Integer>();
        int[] components = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
        	// iterate through the vertices, if it's not in a connected component
        	// then we explore the component containing the next vertex
        	if (components[i] == 0) {
        		current++;  // increment the current component number
        		components[i] = current;
        		q.add(i);
        		// implement a basic BFS to explore the connected component
        		while (!q.isEmpty()) {
        			int next = q.poll();
        			for (int j : adj[next]) {
        				// if we haven't explore a neighbor yet we add it to the queue
        				// and record the component it's in
        				if (components[j] == 0) {
        					components[j] = current;
        					q.add(j);
        				}
        			}
        		}
        	}
        }
        return current;
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
        System.out.println(numberOfComponents(adj));
    }
}