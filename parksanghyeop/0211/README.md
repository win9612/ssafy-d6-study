# 부분수열의 합

### main()

```java
public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		arr = new int[n];
		subset = new int[n];
		seleted = new boolean[n];
		result = 0;
		for(int i=0;i<n;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		subset(0);
		System.out.println(s==0 ? result-1 : result);
	}
```

### subset(int cnt)

```java
public static void subset(int cnt) {
		if(cnt == n) {
			int temp=0;
			for(int i=0;i<n;i++) {
				temp += seleted[i] ? arr[i] : 0;
			}
			if(temp==s)
				result++;
			return;
		}
		
		seleted[cnt] = true;
		subset(cnt+1);
		
		seleted[cnt] = false;
		subset(cnt+1);
	}
```



#  외판원 순회2

### main()

```java
static int n;
	static int[][] w;
	static boolean[] visited;
	static long result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		w = new int[n][n];
		// 배열 입력
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++)
				w[i][j] = Integer.parseInt(st.nextToken());
		}

		// 여행
		for (int start = 0; start < n; start++) {
			visited = new boolean[n];
			visited[start] = true;
			dfs(start, start , 0);
		}
		System.out.println(result);

	}
```

### dfs(int start, int current, long weight) 

```java
public static void dfs(int start, int current, long weight) {
		if(allVisited()) {
			if(w[current][start]!=0) {
				// 지금까지 더한 비용 weight + 나한테 돌아오는 비용 w[current][0]
				// 이전에 계산했던 값 result와 비교해서 더작은걸 result로 갱신
				result = Math.min(weight+w[current][0], result);
			}
		}
		
		for(int i=start;i<n;i++) {
			if(!visited[i] && w[current][i] !=0) {
				visited[i] = true;
				dfs(start, i, weight+w[current][i]);
				visited[i] = false;
			}			
		}
	}
```

### allVisited()

```java
public static boolean allVisited() {
		for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
	}
```

**ww**

