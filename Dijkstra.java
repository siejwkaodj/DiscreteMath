package discrete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dijkstra implements Discreteinterface{
    private int n;
    private static Scanner scan = new Scanner(System.in);

    private int[][] node;
    private int[] dist;

    public void makeGraph(String fileName){
        try(Scanner scan = new Scanner(new File(fileName))) {
            int i = 0;
            while(scan.hasNextLine()){
                String str = scan.nextLine();
                String[] temp = str.split(" ");
                if(i == 0){
                    this.n = Integer.parseInt(temp[0]);
                    node = new int[n][n];
                    dist = new int[n];
                }else{
                    for(int j = 0; j < temp.length; j++){

                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int[] SerachDone() {
        return dist;
    }
}
