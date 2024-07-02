import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pair implements Comparable<Pair> {
		int index, time;
		public Pair(int index, int time) {
			this.index = index;
			this.time = time;
		}
		@Override
		public int compareTo(Pair p) {
			return Integer.compare(this.time, p.time);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), X = Integer.parseInt(st.nextToken());
		int[][] cost  = new int[N][N];
		
		ArrayList<Pair>[] adj = new ArrayList[N]; 
		
		for(int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
			adj[S - 1].add(new Pair(E - 1, T));
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			pq.add(new Pair(i, 0));
			cost[i][i] = 0;
			while(!pq.isEmpty()) {
				Pair top = pq.poll();
				for(Pair next : adj[top.index]) {
					if(cost[i][next.index] > top.time + next.time) {
						cost[i][next.index] = top.time + next.time;
						pq.add(new Pair(next.index, cost[i][next.index]));
					}
				}
			}
		}
		
		int answer = cost[0][X - 1] + cost[X - 1][0];
		for(int i = 1; i < N; i++) {
			answer = Math.max(answer, cost[i][X - 1] + cost[X - 1][i]);
		}
		
		System.out.println(answer);
	}
}