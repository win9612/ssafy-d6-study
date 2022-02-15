###v1###
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		long r = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());

		go(r, c, n, 0);

	}

	private static void go(long i, long j, int n, int add) {
		if (n == 1) {
			System.out.println(add + 2 * i + j);
			return;
		}

		int idx = (int) Math.pow(2, n - 1);
		int addnum = (int) Math.pow(2, 2 * (n - 1));
		if (i < idx && j < idx) {
			// 왼쪽위
			go(i, j, n - 1, add);
		} else if (i < idx && j >= idx) {
			// 오른쪽 위
			go(i, j - idx, n - 1, add + addnum);
		} else if (i >= idx && j < idx) {
			// 왼쪽 아래
			go(i - idx, j, n - 1, add + addnum * 2);
		} else if (i >= idx && j >= idx) {
			// 오른쪽 아래
			go(i - idx, j - idx, n - 1, add + addnum * 3);
		}

	}
}








###v2###
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		go(r, c, n, 0);

	}

	private static void go(int i, int j, int n, int add) {
		if (n == 1) {
			System.out.println(add + 2 * i + j);
			return;
		}

		int idx = (int) Math.pow(2, n - 1);
		int iflag = (i >= idx) ? 1 : 0;
		int jflag = (j >= idx) ? 1 : 0;

		go(i - iflag * idx, j - jflag * idx, n - 1, add + (int) Math.pow(2, 2 * (n - 1)) * (iflag * 2 + jflag));
	}
}
