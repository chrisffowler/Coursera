import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Evacuation {
    private static FastScanner in;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();

        FlowGraph graph = readGraph();
        System.out.println(maxFlow(graph, 0, graph.size() - 1));
    }

    private static int maxFlow(FlowGraph graph, int from, int to) {
        int flow = 0;
        LinkedList<Integer> path = shortestPath(graph, from, to);
        while (path != null) {
        	// calculate the minimum flow allowed by an edge on the path
        	int min = Integer.MAX_VALUE;
        	for (int e : path) {
        		Edge edge = graph.getEdge(e);
        		min = Math.min(min, edge.capacity - edge.flow);
        	}
        	// adjust the flow of all paths now that min is calculated
        	for (int e : path) {
        		graph.addFlow(e, min);
        	}
        	flow += min;
        	path = shortestPath(graph, from, to);
        }
        return flow;
    }
    
    // internal method to get a path of shortest length from --> to, returns the indices of the edges
    // in a LinkedList
    private static LinkedList<Integer> shortestPath(FlowGraph graph, int from, int to) {
    	Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(from);
        int[] trace = new int[graph.size()];
        Arrays.fill(trace, -1);
        while (!queue.isEmpty()) {
        	int next = queue.remove();
        	for (int e : graph.getIds(next)) {
        		Edge edge = graph.getEdge(e);
        		if (edge.flow < edge.capacity && edge.to == to) {
        			// if we've found a min length path to the end then we compile the list of edges
        			LinkedList<Integer> result = new LinkedList<>();
        			result.add(e);
        			int temp = edge.from;
        			while (temp != from) {
        				// iterate through the edges to find the minimum permissible flow
        				// add them to result
        				result.add(trace[temp]);
        				temp = graph.getEdge(trace[temp]).from;
        			}
        			return result;
        		} else if (edge.to != from && trace[edge.to] == -1 && edge.flow < edge.capacity) {
        			// if the edge doesn't lead to a previously explored point and there is allowable
        			// flow then we add it to the queue
        			queue.add(edge.to);
        			trace[edge.to] = e;
        		}
        	}
        }
        // if the queue empties there is no permissible flow and we return null
        return null;
    }

    static FlowGraph readGraph() throws IOException {
        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();
        FlowGraph graph = new FlowGraph(vertex_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            graph.addEdge(from, to, capacity);
        }
        return graph;
    }

    static class Edge {
        int from, to, capacity, flow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    /* This class implements a bit unusual scheme to store the graph edges, in order
     * to retrieve the backward edge for a given edge quickly. */
    static class FlowGraph {
        /* List of all - forward and backward - edges */
        private List<Edge> edges;

        /* These adjacency lists store only indices of edges from the edges list */
        private List<Integer>[] graph;

        public FlowGraph(int n) {
            this.graph = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; ++i)
                this.graph[i] = new ArrayList<>();
            this.edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int capacity) {
            /* Note that we first append a forward edge and then a backward edge,
             * so all forward edges are stored at even indices (starting from 0),
             * whereas backward edges are stored at odd indices. */
            Edge forwardEdge = new Edge(from, to, capacity);
            Edge backwardEdge = new Edge(to, from, 0);
            graph[from].add(edges.size());
            edges.add(forwardEdge);
            graph[to].add(edges.size());
            edges.add(backwardEdge);
        }

        public int size() {
            return graph.length;
        }

        public List<Integer> getIds(int from) {
            return graph[from];
        }

        public Edge getEdge(int id) {
            return edges.get(id);
        }

        public void addFlow(int id, int flow) {
            /* To get a backward edge for a true forward edge (i.e id is even), we should get id + 1
             * due to the described above scheme. On the other hand, when we have to get a "backward"
             * edge for a backward edge (i.e. get a forward edge for backward - id is odd), id - 1
             * should be taken.
             *
             * It turns out that id ^ 1 works for both cases. Think this through! */
            edges.get(id).flow += flow;
            edges.get(id ^ 1).flow -= flow;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
