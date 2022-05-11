package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Gold1967 {
	static LinkedList<Node> tree[];
    static int distance[], max, index;
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        tree = new LinkedList[10001];
        distance = new int[10001];
        visited = new boolean[10001];
        
        for(int i = 1; i <= 10000; i++)
            tree[i] = new LinkedList<>();
        
        for(int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[p].add(new Node(c, w));
            tree[c].add(new Node(p, w));
        }
        
        if(n > 1) {
            dfs(1, 0);
            visited = new boolean[10001];
            distance = new int[10001];
            dfs(index, 0);
            System.out.println(max);
        }
        else
            System.out.println(0);
    }
    
    public static void dfs(int node, int weight) {
        distance[node] = weight;
        visited[node] = true;
        
        if(weight > max) {
            max = weight;
            index = node;
        }
        
        for(Node next : tree[node]) {
            if(!visited[next.number])
                dfs(next.number, weight + next.weight);
        }
    }
    
    static class Node {
        int number;
        int weight;
		public Node(int number, int weight) {
			super();
			this.number = number;
			this.weight = weight;
		}
    }
}
