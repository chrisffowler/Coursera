import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
        
        public double distanceTo(Point other) {
        	double dx = (double) this.x - other.x;
        	double dy = (double) this.y - other.y;
        	return Math.sqrt(dx*dx + dy*dy);
        }
    }

    static double minimalDistance(int[] x, int y[]) {
        double ans = Double.POSITIVE_INFINITY;
        //write your code here
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
        	points[i] = new Point(x[i], y[i]);
        }
        Comparator<Point> xComparator = new Comparator<Point>() {
        	public int compare(Point p1, Point p2) {
        		return (p1.x != p2.x) ?  Long.signum(p1.x - p2.x) : Long.signum(p1.y - p2.y); 
        	}
        };
        Arrays.sort(points, xComparator);
        ans = getMin(points, 0, points.length - 1);
        return ans;
    }
    
    private static double getMin(Point[] pts, int left, int right) {
    	if (left == right) {
    		return Double.POSITIVE_INFINITY;
    	}
    	if (left + 1 == right) {
    		return pts[left].distanceTo(pts[right]);
    	}
    	int mid = (left+right) / 2;
    	double min = Math.min(getMin(pts, left, mid), getMin(pts, mid + 1, right));
    	if (min == 0.0) {
    		return min;
    	}
    	double ref = ((double) pts[mid +1].x - pts[mid].x) / 2.0;
    	if (ref < min) {
    		double middle = ref + (double) pts[mid].x; // the x-coordinate of the vertical bisecting line
    		// if the two middle points have sufficiently close x-coordinates, then we need to check
    		// the points that are close to the bisecting vertical line
    		int lo = mid; // first index within min of the middle line
    		while (lo > left && (middle - (double) pts[lo-1].x) < min) {
    			lo--;
    		}
    		int hi = mid + 1; // last index
    		while (hi< right && ((double) pts[hi+1].x - middle) < min) {
    			hi++;
    		}
    		Arrays.sort(pts, lo, hi + 1); // sort the points close to the middle line wrt y-coordinate
    		return Math.min(min, minNearLine(pts, lo, hi));
    	} else {
    		return min;
    	}
    }
    
    private static double minNearLine(Point[] pts, int lo, int hi) {
        double result = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
        	for (int j = 1; j < 8 && i + j <= hi; j++) {
        		result = Math.min(result, pts[i].distanceTo(pts[i+j]));
        		if (result == 0.0) {
        			return 0.0;
        		}
        	}
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
