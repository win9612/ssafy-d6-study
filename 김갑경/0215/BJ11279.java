import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11279
public class BJ11279 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < n; i++) {
			long x = Long.parseLong(br.readLine());
			if (x == 0) 
				System.out.println(pq.isEmpty() ? 0 : pq.poll());
			else
				pq.offer(x);
		}
	}
}
