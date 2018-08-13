import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        boolean used[] = new boolean[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < adj.length; i++) {
        	if (!used[i]) {
        		dfs(adj, used, order, i);
        	}
        }
        reverse(order);
        return order;
    }
    
    private static void reverse(ArrayList<Integer> order) {
    	int n = order.size();
    	for (int i = 0; i < n / 2; i++) {
    		int temp = order.get(i);
    		order.set(i, order.get(n - 1 - i));
    		order.set(n - 1 - i, temp);
    	}
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] used, ArrayList<Integer> order, int s) {
    	//write your code here
    	// if we have already visited this vertex we return
    	if (used[s]) {
    		return;
    	}
    	// otherwise we explore all adjacent vertices
    	for (int next : adj[s]) {
    		dfs(adj, used, order, next);
    	}
    	order.add(s);
    	used[s] = true;
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}