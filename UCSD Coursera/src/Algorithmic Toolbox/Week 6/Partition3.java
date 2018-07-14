import java.util.*;
import java.io.*;

public class Partition3 {
	public static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
    private static int partition3(int[] A) {
        int total = 0;
        for (int i : A) {
        	total += i;
        }
        if (total % 3 != 0) {
        	return 0;
        }
        int target = total / 3;
        Stack<Point> stack = new Stack<>(); 
        // keeps track of the points to look at in the next step, this prevents us from looking at all target^2
        // different possible pairs of sizes at each step
        stack.push(new Point(0,0));
        
        if (A[0] <= target) {
        	stack.push(new Point(A[0], 0));
        	stack.push(new Point(0, A[0]));
        }
        for (int i = 1; i < A.length; i++) {
        	boolean[][] hit = new boolean[target+1][target+1];
        	Stack<Point> temp = new Stack<>();
        	while (!stack.isEmpty()) {
        		Point p = stack.pop();
        		if (!hit[p.x][p.y]) {
        			hit[p.x][p.y] = true;
        			temp.push(p);
        		}
        		if (p.x + A[i] <= target && !hit[p.x + A[i]][p.y]) {
        			hit[p.x + A[i]][p.y] = true;
        			temp.push(new Point(p.x + A[i], p.y));
        		}
        		if (p.y + A[i] <= target && !hit[p.x][p.y + A[i]]) {
        			hit[p.x][p.y + A[i]] = true;
        			temp.push(new Point(p.x, p.y + A[i]));
        		}
        	}
        	if (hit[target][target]) {
        		return 1;
        	}
        	while (!temp.isEmpty()) {
        		stack.push(temp.pop());
        	}
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

