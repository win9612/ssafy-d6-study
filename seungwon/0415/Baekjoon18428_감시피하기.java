package Day0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon18428_감시피하기 {
	
    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Node> student = new ArrayList<>();
    static int N;
    static char[][] input;
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new char[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = st.nextToken().charAt(0);
                if(input[i][j] == 'S'){ // 학생이면
                    student.add(new Node(i, j)); // 리스트에 저장
                }
            }
        }
        
        dfs(0); // 여기서 종료되지 않았으면

        System.out.println("NO"); // No 출력

    }

    static void dfs(int cnt) { // 3개를 고르는 모든 경우의 수
        if (cnt == 3) { // 장애물이 3개가 누적되면
            bfs(); // 검사 실행
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (input[i][j] == 'X') {
                    input[i][j] = 'O'; // 장애물을 놓는다.
                    dfs(cnt + 1); // dfs
                    input[i][j] = 'X'; // 원래대로
                }
            }
        }
    }

    private static void bfs() { // 감시에 들키는지 검사

        Queue<Node> queue = new LinkedList<>();
        char[][] copy = new char[N][N];
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = input[i][j]; // 현재 장애물이 놓인 경우의 수를 그대로 복사
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j] == 'T') { // 선생님일 떄, 
                    queue.add(new Node(i, j)); // queue에 위치 추가
                    visited[i][j] = true; // 방문 처리
                }
            }
        }

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x;
            int y = now.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                while(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (copy[nx][ny] != 'O') { // 장애물이 아닌 경우 계속 진행해가면서 방문처리
                        visited[nx][ny] = true;
                        nx += dx[k];
                        ny += dy[k];
                    }else{ // 장애물을 만나면 진행 중지.
                        break;
                    }
                }
            }
        }
        
        // bfs를 다 돌고 나서
        if(catchStudent(visited)){ // true가 return되면 감시에 걸리지 않음.
            System.out.println("YES"); // yes 출력 후 
            System.exit(0); // 종료
        }
    }

    private static boolean catchStudent(boolean[][] check) {

        for (Node node : student) {
            if (check[node.x][node.y] == true) { // 학생의 위치가 방문처리 되어있으면 
                return false; // 감시에 걸린 것임.
            }
        }
        return true; // 학생의 위치가 한명도 방문처리 되어있지 않으면 감시에 걸리지 않음.
    }
}