import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] answer;
	static ArrayList<Integer>[] adj;
	
	public static void dfs(int index, int weight) {
		answer[index] += weight;
		for(int nextIndex : adj[index]) {
			dfs(nextIndex, answer[index]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		answer = new int[n + 1];
		adj = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent == -1) continue;
			adj[parent].add(i);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int employee = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
			answer[employee] += weight;
		}

		dfs(1, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
	}
}