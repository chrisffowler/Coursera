import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
    	ArrayList<Integer>[] reverseAdj = reverseGraph(adj);
    	ArrayList<Integer> order = new ArrayList<>();
    	boolean used[] = new boolean[adj.length];
    	// build an order from the reverse graph
    	for (int i = 0; i < adj.length; i++) {
    		if (!used[i]) {
    			dfs(reverseAdj, used, order, i);
    		}
    	}
    	Arrays.fill(used, false);
    	int components = 0;
    	// start from the vertex with highest postorder and explore all reachable vertices
    	// incrementing the components count whenever we start a new exploration
    	for (int i = order.size() - 1; i >= 0; i--) {
    		if (!used[order.get(i)]) {
    			explore(adj, used, order.get(i));
    			components++;
    		}
    	}
    	return components;
    	
    }
    
    private static void explore(ArrayList<Integer>[] adj, boolean[] used, int s) {
    	if (used[s]) {
    		return;
    	}
    	used[s] = true;
    	for (int next : adj[s]) {
    		explore(adj, used, next);
    	}
    }
    
    private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
    	ArrayList<Integer>[] result = (ArrayList<Integer>[])new ArrayList[adj.length];
    	for (int i = 0; i < adj.length; i++) {
    		result[i] = new ArrayList<>();
    	}
    	for (int i = 0; i < adj.length; i++) {
    		for (int nbr : adj[i]) {
    			result[nbr].add(i);
    		}
    	}
    	return result;
    }
    
    private static void dfs(ArrayList<Integer>[] adj, boolean[] used, ArrayList<Integer> order, int s) {
    	//write your code here
    	// if we have already visited this vertex we return
    	if (used[s]) {
    		return;
    	}
    	used[s] = true;
    	// otherwise we explore all adjacent vertices
    	for (int next : adj[s]) {
    		dfs(adj, used, order, next);
    	}
    	order.add(s);
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
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}
