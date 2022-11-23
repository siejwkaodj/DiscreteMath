package discrete;

public class DijkstraTestMain {
    public static void main(String[] args) {
        // reads file
        Txtreader2 fileReader = new Txtreader2();
        fileReader.readtxt("input2.txt");
        for(int i = 0; i < fileReader.arrays.size(); i++){
            Dijkstra dijkstra = new Dijkstra(fileReader.arrays.get(i));
            dijkstra.dijkstra();        // runs dijkstra algorithm
            System.out.println(dijkstra.getDist());     // 최단거리 정보 저장하는 int[] dist 가져옴 
            System.out.println(dijkstra.getRoutes());   // 경로 정보 저장하는 ArrayList<Integer>[] routes 가져옴
        }
    }
}