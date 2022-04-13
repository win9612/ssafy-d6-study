package month2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 효율적인 해킹
public class bj1325 {

    static class Node{
        int vertex; // 정점 번호
        ArrayList<Node> list; // 정점이 가진 인접리스트

        public Node(int vertex) {
            this.vertex = vertex;
            this.list = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", list=" + list +
                    '}';
        }
    }

    static int N, M; // N : 정점 수, M : 간선 수
    static Node[] nodes; // 각 정점 정보
    static boolean[] visited;
    static int[] ans; // 각 정점이 최대로 해킹할 수 있는 컴퓨터 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 수
        M = Integer.parseInt(st.nextToken()); // 간선 수
        nodes = new Node[N+1];
        ans = new int[N+1];
        for(int i = 1 ; i <= N ; i++){
            nodes[i] = new Node(i);
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            nodes[from].list.add(nodes[to]);
        }

//        for(int i = 1 ; i <= N ; i++){
//            System.out.println(nodes[i].toString());
//        }

        int max = 0;
        for(int i = 1 ; i <= N ; i++){
            visited = new boolean[N+1];
            visited[i] = true; // 시작 정점은 일단 방문 처리
            dfs(i, i); // 최대 해킹에 도달할때까지 dfs
            max = Math.max(max, ans[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i++){
            if(max == ans[i]) sb.append(i + " ");
        }
        System.out.println(sb);
    }

    public static void dfs(int current, int start){
        for(Node node : nodes[current].list){
            if(!visited[node.vertex]){
                ans[start]++;
                visited[node.vertex] = true;
                dfs(node.vertex, start);
            }
        }
    }
}
