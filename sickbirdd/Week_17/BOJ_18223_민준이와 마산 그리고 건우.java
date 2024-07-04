import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	final static int INF = (int)2e9;
	static int[][] dist;
	static ArrayList<Pair>[] adj;
	
	static class Pair implements Comparable<Pair>{
		int point, cost;
		Pair(int point, int cost) {
			this.point = point;
			this.cost = cost;
		}
		@Override
		public int compareTo(Pair p) {
			return Integer.compare(this.cost, p.cost);
		}
	}
	
	static void dijkstra(int type, int start) {
		dist[type][start] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(start, 0));
		while(!pq.isEmpty()) {
			Pair now = pq.poll();
			if(now.cost > dist[type][now.point]) continue;
			for(Pair next : adj[now.point]) {
				if(dist[type][next.point] > now.cost + next.cost) {
					dist[type][next.point] = now.cost + next.cost;
					pq.add(new Pair(next.point, dist[type][next.point]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken()); 
		adj = new ArrayList[V + 1];
		dist = new int[2][V + 1];
		for(int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
			dist[0][i] = dist[1][i] = INF;
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
			adj[a].add(new Pair(b, c));
			adj[b].add(new Pair(a, c));
		}
		dijkstra(0, 1);
		dijkstra(1, P);
		System.out.println(dist[0][V] == dist[0][P] + dist[1][V] ? "SAVE HIM" : "GOOD BYE");
	}
}