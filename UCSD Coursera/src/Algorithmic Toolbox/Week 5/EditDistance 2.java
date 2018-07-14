import java.util.*;
import java.lang.Math;

class EditDistance {
  public static int EditDistance(String s, String t) {
      //write your code here
      int[][] D = new int[s.length()+1][t.length()+1];
      for (int i = 0; i <= s.length(); i++) {
          D[i][0] = i;
      }
      for (int j = 1; j <= t.length(); j++) {
          D[0][j] = j;
      }
      for ( int j = 1; j <= t.length(); j++) {
          for (int i = 1; i <= s.length(); i++) {
              if ( s.charAt(i-1) == t.charAt(j-1)) {
                  D[i][j] = Math.min(D[i][j-1]+1, Math.min(D[i-1][j]+1, D[i-1][j-1]));
              } else {
                  D[i][j] = Math.min(D[i][j-1]+1, Math.min(D[i-1][j]+1, D[i-1][j-1]+1));
              }
          }
      }
              
              
      return D[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
