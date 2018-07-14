import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        int[] points = new int[segments.length];
        Arrays.sort(segments);
        int index = 0;
        int i = 0;
        while (index < segments.length) {
        	int pt = segments[index].end;
        	while (index < segments.length && segments[index].contains(pt)) {
        		index++;
        	}
        	points[i++] = pt;
        }
        return Arrays.copyOf(points, i);
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        // easy way to determine if an interval contains a point
        public boolean contains(int x) {
        	return x >= this.start && x <= this.end;
        }
        // we need a we to sort the segments, so we implement Comparable
        // order the intervals by their endpoints
        @Override public int compareTo(Segment other) {
        	if (this.end < other.end) {
        		return -1;
        	} else if (this.end == other.end) {
        		// if their ends are the same the one with the shorter length is less
        		return other.start - this.start;
        	} else {
        		return 1;
        	}
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
