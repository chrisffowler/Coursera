import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    
	// we new class to keep track of the value-weight pairs of things
	// in the knapsack
	private static class Item implements Comparable<Item> {
    	int weight;
    	int value;
    	double vbw; // value by weight
    	public Item(int val, int wt) {
    		this.weight = wt;
    		this.value = val;
    		vbw = ((double) val) / ((double) wt);
    	}
    	
    	@Override public int compareTo(Item other) {
    		if (this.vbw > other.vbw) {
    			return 1;
    		} else {
    			return -1;
    		}
    	}
    }
	
	private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        int n = values.length;
        // create an array of Items and fill it
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
        	items[i] = new Item(values[i], weights[i]);
        }
        Arrays.sort(items);  // call on Arrays built-in sort
        double result = 0.0;
        int remaining = capacity;
        for (int i = n-1; i >= 0; i--) {
        	if (items[i].weight <= remaining) {
        		remaining -= items[i].weight;
        		result += (double) items[i].value;
        	} else {
        		result += items[i].vbw*((double) remaining);
        		break;
        	}
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
