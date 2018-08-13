import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        boolean[] removed = new boolean[adj.length];
        boolean[] inPath = new boolean[adj.length];
    	for (int i = 0; i < adj.length; i++) {
        	if (!removed[i]) {
        		if (explore(adj, i, removed, inPath) == 1) {
        			return 1;
        		}
        	}
        }
        return 0;
    }
    
    private static int explore(ArrayList<Integer>[] adj, int start, boolean[] removed, boolean[] inPath) {
    	inPath[start] = true;
    	for (int i : adj[start]) {
    		if (inPath[i]) {
    			// if it's already in the path we've found a cycle!
    			return 1;
    		}
    		if (!removed[i] && explore(adj, i, removed, inPath) == 1) {
    			// if this vertex has not been removed we explore it and check for a cycle
    			return 1;
    		}
    	}
    	inPath[start] = false;
    	removed[start] = true;
    	return 0;
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
        System.out.println(acyclic(adj));
    }
}