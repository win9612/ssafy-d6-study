package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최소 스패닝 트리
public class bj1197 {

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int V, E;
    static int[] parents;
    static Edge[] edgeList;

    static void makeSet(){
        parents = new int[V+1];
        for(int i = 0 ; i < V+1 ; i++){
            parents[i] = i;
        }
    }

    static int findSet(int a){
        if(a == parents[a]){
            return a;
        }
        return parents[a] = findSet(parents[a]); // path compression
    }

    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot){
            return false; // 같은 집합이므로 합칠 수 없음
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edgeList = new Edge[E];
        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);
        }

        //
        Arrays.sort(edgeList);
        makeSet();

        int result = 0;
        int cnt = 0;

        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                result += edge.weight;
                if(++cnt == V-1) break;
            }
        }
        System.out.println(result);
    }
}
