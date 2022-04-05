package programmers;

public class 전력망을_둘로_나누기 {

    static int V, E; // 정점 수, 간선 수
    static int[][] adjMatrix;
    static boolean[] visited; // dfs로 탐색한 정점인지 표시
    static int count; // dfs로 탐색하며 센 간선 개수

    public static void dfs(int[][] adjMatrix, boolean[] visited, int current){
        if(visited[current]){ // 만약 간선을 이미 센 정점이라면
            return;
        }
        count += 1; // 간선 수 + 1
        visited[current] = true;

        for(int j = 0 ; j < V ; j++){
            if(adjMatrix[current][j] != 0){
                dfs(adjMatrix, visited, j);
            }
        }
    }

    public static int solution(int n, int[][] wires){
        V = n + 1; // 정점 수
        E = wires.length; // 간선 수
        int min = Integer.MAX_VALUE;


        for(int e = 0 ; e < E ; e++){ // 모든 간선을 다 하나씩 빼봄
            // 인접 매트릭스 입력 과정
            adjMatrix = new int[V][V];
            visited = new boolean[V];

            for(int i = 0 ; i < E ; i++){
                if(i != e){ // 현재 간선을 빼고 나머지 간선들을 인접행렬에 입력한다.
                    int from = wires[i][0];
                    int to = wires[i][1];
                    adjMatrix[from][to] = adjMatrix[to][from] = 1;
                }
            }

            // 나뉘어진 두 그래프의 간선 수를 세어서 저장한다.
            int[] counts = new int[2];
            int countsIdx = 0;

            for(int i = 1 ; i < V ; i++){
                count = 0;
                dfs(adjMatrix,visited,i);
                if(count > 0){
                    counts[countsIdx++] = count;
                }
            }
            min = Math.min(min, Math.abs(counts[0] - counts[1]));
        }
        return min;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
        //int n = 4;
        //int[][] wires = {{1,2},{2,3},{3,4}};

        int answer = solution(n, wires);
        System.out.println(answer);
    }
}
