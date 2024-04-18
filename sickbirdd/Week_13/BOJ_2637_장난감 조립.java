import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Component {
		int type, amount;
		Component(int type, int amount) {
			this.type = type;
			this.amount = amount;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
		ArrayList<Component>[] adj = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Component>();
		}
		int[] degree = new int[N + 1];
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			adj[X].add(new Component(Y, K));
			degree[Y]++;
		}
		int[] total = new int[N + 1];
		total[N] = 1;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Component next : adj[now]) {
				total[next.type] += next.amount * total[now];
				if(--degree[next.type] == 0) {
					queue.add(next.type);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N; i++) {
			if(adj[i].isEmpty()) {
				sb.append(i).append(" ").append(total[i]).append("\n");
			}
		}
		System.out.println(sb);
	}
}