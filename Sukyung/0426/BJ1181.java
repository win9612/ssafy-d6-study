package day0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BJ1181 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<String> names = new HashSet<>();

		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			names.add(name);
		}
		ArrayList<Name> note = new ArrayList<>();
		for (String s : names) {
			note.add(new Name(s, s.length()));
		}
		note.sort(null);
		for (Name n : note) {
			System.out.println(n.name);
		}
	}

	public static class Name implements Comparable<Name> {
		String name;
		int len;

		public Name(String n, int l) {
			name = n;
			len = l;
		}

		@Override
		public int compareTo(Name o) {
			if (this.len < o.len) {
				return -1;
			} else if (this.len == o.len) {
				return this.name.compareTo(o.name);
			} else
				return 1;
		}
	}
}
