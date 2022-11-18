package discrete;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Dijkstra{
    public class Node implements Comparable<Node>{
        private int idx, weight;
        Node(int idx, int weight) {this.idx = idx; this.weight = weight;}

        @Override
        public int compareTo(Node o) {
            if(this.weight == o.weight){
                return this.idx - o.idx;
            }
            return this.weight - o.weight;
        }
    }
    private int n;
    private static Scanner scan = new Scanner(System.in);

    // field variables
    private final int INF = Integer.MAX_VALUE;
    private int[][] node;
    private int[] dist;
    private boolean[] visited;
    private ArrayList<Integer>[] routes;

    // constructor
    Dijkstra(){
        this.node = new int[][]
            {
                {0, 20, 0, 30, 0, 0},
                {0, 0, 20, 0, 40, 0},
                {0, 0, 0, 0, 0, 20},
                {0, 0, 20, 0, 20, 30},
                {0, 0, 0, 0, 0, 10},
                {0, 0, 0, 0, 0, 0}
            };
    /*
            {
                {0, 10, 0, 30, 100},
                {0, 0, 50, 0, 0},
                {0, 0, 0, 0, 10},
                {0, 0, 20, 0, 60},
                {0, 0, 0, 0, 0}
            };
                {
                {0, 7, 4, 6, 1},
                {0, 0, 0, 0, 0},
                {0, 2, 0, 5, 0},
                {0, 3, 0, 0, 0},
                {0, 0, 0, 1, 0}
            };
             */
        //input2reader(); // 인접행렬 초기화
        this.n = node.length;       // n 받아옴
        this.visited = new boolean[n];
        // route 초기화 -> 일단 빈 int 리스트로, 시작 인덱스는 {0}으로
        this.routes = new ArrayList[n];
        Arrays.fill(routes, new ArrayList<>());
        routes[0].add(0);
        // dist 초기화 -> INF
        this.dist = new int[n];
        Arrays.fill(dist, INF);
    }

    // static PriorityQueue<ArrayList<Integer>>
    // solving
    void dijkstra(){
        PriorityQueue<Node> pqueue = new PriorityQueue<>();
        pqueue.add(new Node(0, 0));
        dist[0] = 0;
        while(!pqueue.isEmpty()){
            Node currentNode = pqueue.poll();   // 최소인덱스값 뽑음
            int start = currentNode.idx;
            visited[start] = true;
            for(int i = 0; i < n; i++){
                if(node[start][i] > 0 && !visited[i] && dist[i] > dist[start] + node[start][i]){
                    dist[i] = dist[start] + node[start][i]; // dist 갱신
                    pqueue.offer(new Node(i, dist[i]));
                    // 이전까지의 길에 현재 idx 추가.
                    routes[i] = new ArrayList<>(routes[start]);
                    routes[i].add(i);
                }
            }
        }
    }

    ArrayList<Integer> shortestPath(int end){
        return routes[end - 1];
    }

    int pathLength(int end){
        return dist[end - 1];
    }
}
