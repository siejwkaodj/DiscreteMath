package discrete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstraTestMain {
    public static void main(String[] args) {
        // reads file
        int stIdx = 1;  // 시작 정점 번호 설정
        Txtreader2 fileReader = new Txtreader2();
        fileReader.readtxt("input2.txt");
        // 각 그래프 케이스마다 반복
        for(int i = 0; i < fileReader.arrays.size(); i++){
            int[][] array = fileReader.arrays.get(i);
            // 인접 행렬 정보 전달
            Dijkstra dijkstra = new Dijkstra(array, stIdx);
            // runs dijkstra algorithm
            dijkstra.dijkstra();
            dijkstra.printGraph(i);

            System.out.println();
        }
    }
    public static class Dijkstra{
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
        private int n;  // 인접행렬 배열 크기 정보 저장
        // field variables
        private final int INF = Integer.MAX_VALUE;  // INF값 저장, int 최대형으로 저장함.
        private final int stIdx;
        private int[][] node;                       // 인접행렬 저장
        private int[] dist;                         // 최단경로 길이 정보 저장
        private boolean[] visited;                  // 방문 노드 정보 저장
        private ArrayList<Integer>[] routes;        // 각 노드로 가는 최단 경로 노드들 저장, int[] 배열을 요소로 가지는 ArrayList<Integer>[] 형

        // constructor - 한 번에 하나의 인접 행렬을 받아옴,
        // 인접 행렬 여러개면 for문으로 돌리면서 각 반복에서 Dijkstra d = new Dijkstra(array); 형식으로 받아올 것.
        public Dijkstra(int[][] array, int stIdx){
            this.node = array;          // 인접행렬 초기화
            this.stIdx = stIdx - 1;     // 정점번호 -> idx로 바꾸려면 1 빼줘야함
            this.n = node.length;       // n 받아옴
            this.visited = new boolean[n];

            // route 초기화 -> 일단 빈 int 리스트로, 시작 인덱스는 {0}으로
            this.routes = new ArrayList[n];
            Arrays.fill(routes, new ArrayList<>());
            this.routes[this.stIdx].add(this.stIdx);

            // dist 초기화 -> INF
            this.dist = new int[n];
            Arrays.fill(dist, INF);
        }

        // solving
        void dijkstra(){
            PriorityQueue<Node> pqueue = new PriorityQueue<>(); // 우선순위 큐 이용해서 알고리즘 구현함
            pqueue.add(new Node(stIdx, 0));
            dist[stIdx] = 0;
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
        void printGraph(int i){
            System.out.println("그래프 [" + (i+1) + "]");
            System.out.println("------------------------");
            System.out.println("시작점: " + (stIdx + 1));

            for(int j = 0; j < n; j++){          // 해당 그래프의 모든 정점으로의 경로 출력
                if(j == stIdx) continue;
                ArrayList<Integer> route =  this.getRoutes()[j];

                System.out.print("정점 [" + (j+1) + "]: ");
                for(int k = 0; k < route.size() - 1; k++){
                    System.out.print((route.get(k) + 1) + " - ");
                }
                System.out.print(route.get(route.size() - 1) + 1);
                System.out.println(", 길이: " + this.getDist()[j]);
            }
            System.out.println("=========================");
        }

        // 끝점을 입력받으면 최단경로 노드들 정보 반환
        ArrayList<Integer> shortestPath(int end){
            return routes[end - 1];
        }
        // 전체 최단경로 노드 정보 반환
        ArrayList<Integer>[] getRoutes(){
            return routes;
        }
        // 끝점 입력받으면 최단경로 길이 정보 반환
        int pathLength(int end){
            return dist[end - 1];
        }
        // 전체 최단경로 길이 정보 반환
        int[] getDist(){
            return dist;
        }
    }
}