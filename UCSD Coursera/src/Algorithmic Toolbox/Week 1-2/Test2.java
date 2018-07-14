import java.io.*;
import java.util.*;

public class Test2 {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        String s = in.nextLine();
        if (s.length() == 0) {
        	System.out.println("failure");
        	return;
        }
        do {
        	try {
        		int n = Integer.parseInt(s);
        		list.add(n);
        	} catch (NumberFormatException e) {
        		System.out.println("failure");
        		return;
        	}
        	s = in.nextLine();
        } while (s.length() > 0);
        in.close();
        System.out.println(list.toString());
	}
}
