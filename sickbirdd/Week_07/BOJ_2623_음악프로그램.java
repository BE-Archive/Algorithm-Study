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
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		int[] degree = new int[N + 1];

		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			for(int j = 0; j < L - 1; j++) {
				int next = Integer.parseInt(st.nextToken());
				adj[prev].add(next);
				degree[next]++;
				prev = next;
			}
		}

		Queue<Integer> queue = new ArrayDeque<>();

		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) queue.add(i);
		}

		while(!queue.isEmpty()) {
			Integer now = queue.poll();
			sb.append(now).append('\n');
			for(Integer next : adj[now]) {
				if(--degree[next] == 0) {
					queue.add(next);
				}
			}
		}

		boolean isPossible = true;
		for(int i = 1; i <= N; i++) {
			if(degree[i] != 0) isPossible = false;
		}

		System.out.println(isPossible ? sb : 0);
	}
}