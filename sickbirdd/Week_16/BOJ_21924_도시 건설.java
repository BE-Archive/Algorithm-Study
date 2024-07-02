import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Tuple implements Comparable<Tuple> {
		int a, b, c;
		Tuple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		@Override
		public int compareTo(Tuple t) {
			return Integer.compare(this.c, t.c);
		}
	}
	
	static class UnionFind {
		int[] p;
		UnionFind(int n) {
			this.p = new int[n];
			Arrays.fill(this.p, -1);
		}
		boolean merge(int a, int b) {
			int ra = find(a), rb = find(b);
			if(ra == rb) return false;
			p[ra] += p[rb];
			p[rb] = ra;
			return true;
		}
		int find(int x) {
			if(p[x] < 0) return x;
			return p[x] = find(p[x]);
		}
		
		int size(int x) {
			return -p[find(x)];
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
 		
		long total = 0L;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
			pq.add(new Tuple(a, b, c));
			total += (long)c;
		}
		
		UnionFind uf = new UnionFind(N + 1);
		while(!pq.isEmpty()) {
			Tuple t = pq.poll();
			if(uf.merge(t.a, t.b)) total -= t.c;
		}
		System.out.println(uf.size(1) == N ? total : -1);
	}
}