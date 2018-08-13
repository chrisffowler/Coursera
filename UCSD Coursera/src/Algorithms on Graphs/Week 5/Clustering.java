import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.LinkedList;


public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
    	// we will implement Krustal's algorithm, adding minimal edges and forming components until
    	// we are left with precisely k components. At this point the next smallest edge will be
    	// the max distance between edges
    	// currently assuming we don't get repeats of points, will need to amend solution if not
    	int n = x.length;
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	LinkedList<Integer>[] components = new LinkedList[n]; // sets of vertices in each component
    	int[] component = new int[n];  // component of a given vertex
    	// set every vertex as corresponding to its own component
    	for (int i = 0; i < n; i++) {
    		component[i] = i;
    		components[i] = new LinkedList<Integer>();
    		components[i].add(i);
    	}
    	int number = n;  // number of components
    	// add all edges betweens pairs of vertices
    	for (int i = 0; i < n; i++) {
    		for (int j = i + 1; j < n; j++) {
    			pq.add(new Edge(i, j, length(x, y, i, j)));
    		}
    	}
    	while (number > k) {
    		Edge e = pq.poll();
    		int comp1 = component[e.index1];
    		int comp2 = component[e.index2];
    		if (comp1 != comp2) {
    			// move every vertex in the smaller component into the larger one
    			int first = (components[comp1].size() >= components[comp2].size()) ? comp1 : comp2;
    			int second = (first == comp1) ? comp2 : comp1;
    			for (int i : components[second]) {
    				component[i] = first;
    			}
    			components[first].addAll(components[second]);
    			components[second].clear(); 
    			// clear second component of vertices
    			number--;
    		}
    	}
    	Edge e = pq.poll();
    	while (component[e.index1] == component[e.index2]) {
    		e = pq.poll();
    	}
    	return e.length;
    }
    
    static class Edge implements Comparable<Edge> {
    	int index1, index2;
    	double length;
    	public Edge(int i1, int i2, double length) {
    		this.index1 = i1;
    		this.index2 = i2;
    		this.length = length;
    	}
    	
    	public int compareTo(Edge o) {
			if (this.length + 0.00000001 < o.length) {
				return -1;
			} else if (o.length + 0.00000001 < this.length) {
				return 1;
			} else {
				return 0;
			}
    	}
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}
