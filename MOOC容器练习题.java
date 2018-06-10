import java.lang.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<String> city = new ArrayList<String>();
        String s = new String();
        Scanner input = new Scanner(System.in);
        while ((s = input.next()).equals("###") == false) {
            city.add(s);
        }
        int[][] data = new int[city.size()][city.size()];
        int i,j;
        for(i=0;i<city.size();i++){
            for (j=0;j<city.size();j++){
                data[i][j]=input.nextInt();
            }
        }
        i=city.indexOf(input.next());
        j=city.indexOf(input.next());
        System.out.print(data[i][j]);
    }
}