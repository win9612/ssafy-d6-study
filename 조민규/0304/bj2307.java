package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도로검문
public class bj2307 {

    static int N, M; // 정점 수, 간선 수
    static int start = 1; // 시작 정점
    static LinkedList<Edge>[] adjList;
    static int[] dist; // 정점1에서 n까지의 거리

    static class Edge implements Comparable<Edge>{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 1; // 0번 정점이 있다고 치자
        M = Integer.parseInt(st.nextToken());
        adjList = new LinkedList[N+1]; // 정점번호 1 ~ N
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new LinkedList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
        }

        dijkstra();
        int before = dist[N-1]; // 경찰이 안 막았을 때 최소 탈출 시간

        // 1. 모든 간선에 대해 반복문으로 막아보기를 한다.
        // 2. 대상 간선을 일시적으로 제거한 후, 다시 다익스트라를 실시한다.
        // 3. (2에서 나온 값)-(before)가 현재 answer보다 클 경우 이것을 answer로 한다.
        // 4. (2에서 나온 값)-(before)이 Integer.MAX_VALUE일 경우 정답으로 -1을 출력한다.
    }

    public static void dijkstra(){
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        boolean[] visit = new boolean[N];
        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            int current = edge.to;

            if(visit[current]) continue;
            visit[current] = true;

            for(Edge out : adjList[current]){
                if(dist[out.to] > dist[current] + out.weight){
                    dist[out.to] = dist[current] + out.weight;
                    pq.add(new Edge(out.to, dist[out.to]));
                }
            }
        }
    }
}
