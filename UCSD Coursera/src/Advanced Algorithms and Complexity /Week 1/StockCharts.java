import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class StockCharts {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new StockCharts().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int[][] stockData = readData();
        int result = minCharts(stockData);
        writeResponse(result);
        out.close();
    }

    int[][] readData() throws IOException {
        int numStocks = in.nextInt();
        int numPoints = in.nextInt();
        int[][] stockData = new int[numStocks][numPoints];
        for (int i = 0; i < numStocks; ++i)
            for (int j = 0; j < numPoints; ++j)
                stockData[i][j] = in.nextInt();
        return stockData;
    }

    private int minCharts(int[][] stockData) {
        // Replace this incorrect greedy algorithm with an
        // algorithm that correctly finds the minimum number
        // of charts on which we can put all the stock data
        // without intersections of graphs on one chart.

    	int stocks = stockData.length;
        boolean[][] bipartiteGraph = new boolean[stocks][stocks];
        for (int i = 0; i < stocks - 1; i++) {
        	for (int j = i + 1; j < stocks; j++) {
        		if (stockData[i][0] != stockData[j][0]) {
        			boolean less = (stockData[i][0] - stockData[j][0] < 0) ? true : false;
        			boolean more = !less;
        			for (int k = 1; k < stockData[0].length; k++) {
            			if (less && stockData[i][k] >= stockData[j][k]) {
            				less = false;
            				break;
            			} else if (more && stockData[i][k] <= stockData[j][k]) {
            				more = false;
            				break;
            			}
            		}
        			if (less) {
        				bipartiteGraph[i][j] = true;
        			}
        			if (more) {
        				bipartiteGraph[j][i] = true;
        			}
        		}
        		
        	}
        }
        FlowGraph graph = makeGraph(bipartiteGraph);
        return stocks - maxFlow(graph, 0, graph.size() - 1);
    }
    
    private static FlowGraph makeGraph(boolean[][] bipartiteGraph) {
    	 int numLeft = bipartiteGraph.length;
         int numRight = bipartiteGraph[0].length;

         // realize this bipartite graph as a flowgraph with a sink and source
         FlowGraph graph = new FlowGraph(numLeft + numRight + 2);
         // add edges from source to first set of edges
         for (int i = 1; i <= numLeft; i++) {
         	graph.addEdge(0, i, 1);
         }
         // add edges from second set of edges to sink
         for (int i = numLeft + 1; i <= numLeft + numRight; i++) {
         	graph.addEdge(i, numLeft + numRight + 1, 1);
         }
         for (int i = 0; i < numLeft; i++) {
         	for (int j = 0; j < numRight; j++) {
         		if (bipartiteGraph[i][j]) {
         			graph.addEdge(i + 1, numLeft + j + 1, 1);
         		}
         	}
         }
         return graph;
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

    boolean compare(int[] stock1, int[] stock2) {
        for (int i = 0; i < stock1.length; ++i)
            if (stock1[i] >= stock2[i])
                return false;
        return true;
    }

    private void writeResponse(int result) {
        out.println(result);
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
