import java.io.*;
import java.util.*;

public class Main {
	final static int INF = (int) 1e9;

	static class Fox implements Comparable<Fox>{
		int idx;
		float val;
		Fox(int idx, float val) {
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Fox f) {
			return Float.compare(this.val, f.val);
		}
	}

	static class Wolf implements Comparable<Wolf>{
		int idx, cnt;
		float val;
		Wolf(int idx, int cnt, float val) {
			this.idx = idx;
			this.cnt = cnt;
			this.val = val;
		}
		@Override
		public int compareTo(Wolf w) {
			return Float.compare(this.val, w.val);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

		ArrayList<Fox>[] adj = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
			adj[a].add(new Fox(b, d));
			adj[b].add(new Fox(a, d));
		}

		float[] foxDist = new float[N + 1];
		PriorityQueue<Fox> fpq = new PriorityQueue<>();
		Arrays.fill(foxDist, INF);
		foxDist[1] = 0;
		fpq.add(new Fox(1, 0));

		while(!fpq.isEmpty()) {
			Fox now = fpq.poll();
			if(foxDist[now.idx] < now.val) continue;
			for(Fox next : adj[now.idx]) {
				if(foxDist[now.idx] + next.val < foxDist[next.idx]) {
					foxDist[next.idx] = foxDist[now.idx] + next.val;
					fpq.add(new Fox(next.idx, foxDist[next.idx]));
				}
			}
		}

		float[][] wolfDist = new float[2][N + 1];
		Arrays.fill(wolfDist[0], INF);
		Arrays.fill(wolfDist[1], INF);

		wolfDist[0][1] = 0;
		PriorityQueue<Wolf> wpq = new PriorityQueue<>();
		wpq.add(new Wolf(1, 0, 0));
		while(!wpq.isEmpty()) {
			Wolf now = wpq.poll();
			if(wolfDist[now.cnt][now.idx] < now.val) continue;
			for(Fox next : adj[now.idx]) {
				if(now.cnt == 0 && wolfDist[now.cnt][now.idx] + next.val / 2.0f < wolfDist[now.cnt ^ 1][next.idx]) {
					wolfDist[now.cnt ^ 1][next.idx] = wolfDist[now.cnt][now.idx] + next.val / 2.0f;
					wpq.add(new Wolf(next.idx, now.cnt ^ 1, wolfDist[now.cnt ^ 1][next.idx]));
				}
				else if(now.cnt == 1 && wolfDist[now.cnt][now.idx] + next.val * 2.0f < wolfDist[now.cnt ^ 1][next.idx]){
					wolfDist[now.cnt ^ 1][next.idx] = wolfDist[now.cnt][now.idx] + next.val * 2.0f;
					wpq.add(new Wolf(next.idx, now.cnt ^ 1, wolfDist[now.cnt ^ 1][next.idx]));
				}
			}
		}

		int ans = 0;
		for(int i = 1; i <= N; i++) {
			if(Math.min(wolfDist[0][i], wolfDist[1][i]) <= foxDist[i]) continue;
			ans++;
		}

		System.out.println(ans);
	}
}