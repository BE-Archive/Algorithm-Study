import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
			adj[A].add(B);
			adj[B].add(A);
		}

		int answer = Integer.MAX_VALUE, index = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			queue.add(new int[] { i, 0 });
			visited[i] = true;
			int temp = 0;
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				temp += now[1];
				for (int next : adj[now[0]]) {
					if (!visited[next]) {
						visited[next] = true;
						queue.add(new int[] { next, now[1] + 1 });
					}
				}
			}
			if (answer > temp) {
				answer = temp;
				index = i;
			}
		}
		System.out.println(index);
	}
}