import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import ConnectingPoints.Pair;

import java.util.Queue;


public class Test {
	private static double minimumDistance(int[] x, int[] y) {
    	// implementation of finding minimal spanning tree of the graph containing all edges
    	// between two point using Kruskal's algorithm
        HashMap<Integer, List<Integer>> sets = new HashMap<>();
        int[] set = new int[x.length];
        for (int i = 0; i < x.length; i++) {
        	set[i] = i;
        	sets.put(i, new LinkedList<Integer>());
        	sets.get(i).add(i);
        }
        // create a comparator for doubles of points the measure the length of the edge
        // between the points
        Comparator<Pair> edgeComp = new Comparator<Pair>() {
        	public int compare(Pair p1, Pair p2) {
        		double l1 = length(x, y, p1.i, p1.j); 
        		double l2 = length(x, y, p2.i, p2.j);
        		if (l2 - l1 > 0.00000001) {
        			return -1;
        		} else if (Math.abs(l1-l2) < 0.00000001) {
        			return 0;
        		} else {
        			return 1;
        		}
        	}
        };
        PriorityQueue<Pair> pq = new PriorityQueue<>(x.length*(x.length - 1) / 2, edgeComp);
        for (int i = 0; i < x.length; i++) {
        	for (int j = i+1; j < x.length; j++) {
        		pq.add(new Pair(i,j));
        	}
        }
        double distance = 0.0;
        while (sets.get(set[0]).size() < x.length) {
        	// continue adding edges so long as the set containg 0 does not contain
        	// every vertex
        	Pair edge = pq.poll();
        	if (set[edge.i] != set[edge.j]) {
        		// if the two vertices of the next shortest edge are not in the same
        		// set then we add that edge and combine sets
        		int top = (sets.get(set[edge.i]).size() >= sets.get(set[edge.j]).size()) ? set[edge.i] : set[edge.j];
        		int bottom = (top == set[edge.i]) ? set[edge.j] : set[edge.i];
        		for (int i : sets.get(bottom)) {
        			// move the smaller set vertices to the larger set
        			set[i] = top;
        		}
        		sets.get(top).addAll(sets.get(bottom));  // combine the sets in the HashMap
        		sets.remove(bottom);  // remove the bottom set from the HashMap
        		distance += length(x, y, edge.i, edge.j);
        	}
        }
        return distance;
    }
    
    private static double length(int[] x, int[] y, int i, int j) {
    	double dx = (double) x[i] - x[j];
    	double dy = (double) y[i] - y[j];
    	return Math.sqrt(dx*dx + dy*dy);
    }
    
    static class Pair {
    	int i, j;
    	public Pair(int i, int j) {
    		this.i = i;
    		this.j = j;
    	}
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
        System.out.println(minimumDistance(x, y));
    }
}
