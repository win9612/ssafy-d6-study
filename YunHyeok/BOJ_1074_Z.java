package day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074_Z {
	static int N, r, c;
	static int count; // 재귀함수가 1x1로 쪼개졌을 때 방문순서 ++하는 변수
	static int ans; // r, c가 몇번째로 방문되는지 저장
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 2^15 = 32768 x 32768  = 9억 배열의 크기가 너무 커서 생성하면 안됨
		// 영역마다 재귀를 돌릴것이 아니라 rc가 포함된 영역에 들어갈때만 재귀를 하고 그전영역들의 크기를 누적 해주면 된다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		r = stoi(st.nextToken());
		c = stoi(st.nextToken());
		
		visit(0, 0, 1<<N, 1<<N);
		System.out.println(ans);
	}
	
	static void visit(int si, int sj, int ei, int ej) {
		if(si>r || ei<=r || sj>c || ej<=c){ // 주어진 영역에 r과c가 없으면 넘어가게끔
			count += (ei-si) * (ej-sj); // 행차이 * 열차이를 더해서 한꺼번에 방문처리를 하겠다.
			return;
		}
			
		if(ei-si == 1){ // 이미 si~ei가 1칸이라 더 못쪼갬
			if(si == r && sj == c) { // 목적지 칸에 도착했다면
				ans = count; 
			}
			count++;
			return;
		}
		
		int mi = (si+ei)/2; // y축 중간값
		int mj = (sj+ej)/2; // x축 중간값
		
		visit(si, sj, mi, mj); // 1
		visit(si, mj, mi, ej); // 2
		visit(mi, sj, ei, mj); // 3
		visit(mi, mj, ei, ej); // 4
	}
	
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
	

}
