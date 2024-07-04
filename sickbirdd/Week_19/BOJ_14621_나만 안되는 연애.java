import java.io.*;
import java.util.*;

public class Main {

	static class Road implements Comparable<Road> {
		int from, to, dist;
		public Road(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		@Override
		public int compareTo(Road r) {
			return Integer.compare(this.dist, r.dist);
		}
	}

	static class UnionFind {
		int[] par;
		UnionFind(int N) {
			par = new int[N];
			for(int i = 0; i < N; i++) {
				par[i] = -1;
			}
		}

		int find(int x) {
			if(par[x] < 0) return x;
			return par[x] = find(par[x]);
		}

		boolean merge(int a, int b) {
			int ra = find(a), rb = find(b);
			if(ra == rb) return false;
			par[ra] += par[rb];
			par[rb] = ra;
			return true;
		}

		int size(int x) {
			return -par[find(x)];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		boolean[] isMale = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			isMale[i] = st.nextToken().equals("M") ? true : false;
		}

		PriorityQueue<Road> pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
			if(isMale[u - 1] == isMale[v - 1]) continue;
			pq.add(new Road(u - 1, v - 1, d));
		}

		int ans = 0;
		UnionFind uf = new UnionFind(N);
		while(!pq.isEmpty()) {
			Road now = pq.poll();
			if(!uf.merge(now.from, now.to)) continue;
			ans += now.dist;
		}
		System.out.println(uf.size(0) == N ? ans : -1);
	}
}