import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[adj.length];
        q.add(x);
        visited[x] = true;
        while (!q.isEmpty()) {
        	int next = q.poll();
        	for (int i : adj[next]) {
        		if (i == y) {
        			return 1;
        		}
        		if (!visited[i]) {
        			visited[i] = true;
        			q.add(i);
        		}
        	}
        }
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}