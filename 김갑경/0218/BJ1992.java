import java.io.*;

public class BJ1992 {

	private static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		for (int i = 0; i < n; i++)
			arr[i] = br.readLine().toCharArray();
		System.out.println(go(0, 0, n));
	}

	private static String go(int i, int j, int n) {
		if (n <= 1)
			return String.valueOf(arr[i][j]);

		n >>= 1;
		// 좌상
		String s1 = go(i, j, n);
		// 우상
		String s2 = go(i, j + n, n);
		// 좌하
		String s3 = go(i + n, j, n);
		// 우하
		String s4 = go(i + n, j + n, n);

		// 0만 포함하거나 1만 포함해야함
		// 0과 1 둘다 포함하면 안됨
		if (!(s1.contains("0") && s1.contains("1")) && s1.equals(s2) && s2.equals(s3) && s3.equals(s4))
			return s1;
		else
			return "(" + s1 + s2 + s3 + s4 + ")";
	}
}
