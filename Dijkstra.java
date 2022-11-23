package discrete;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Dijkstra{
    // Node 클래스 만들어서 { weight, idx } 형식의 자료형을 우선순위큐에 넣을 수 있도록 함.
    // 크기가 2로 고정되는 1차원배열을 한 번에 만들기 쉽지 않았기 때문. compareTo도 직접 만들어 줄 수도 있고.
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
    // field variables
    private final int INF = Integer.MAX_VALUE;
    private int[][] node;
    private int[] dist;
    private boolean[] visited;
    private ArrayList<Integer>[] routes;

    // constructor - 한 번에 하나의 인접 행렬을 받아옴,
    // 인접 행렬 여러개면 for문으로 돌리면서 각 반복에서 Dijkstra d = new Dijkstra(array); 형식으로 받아올 것.
    Dijkstra(int[][] array){
        this.node = array;          // 인접행렬 초기화
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
    ArrayList<Integer>[] getRoutes(){
        return routes;
    }
    int pathLength(int end){
        return dist[end - 1];
    }
    int[] getDist(){
        return dist;
    }
}
