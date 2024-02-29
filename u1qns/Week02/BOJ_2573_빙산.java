import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pair> ice = new LinkedList<>(); // 빙산

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean isValid(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static void init(boolean[][] v) {
		for (int i = 0; i < N; ++i) {
			v[i] = new boolean[M + 1];
			for (int j = 0; j < M; ++j) {
				v[i][j] = false;
			}
		}
	}

	static void meltIce() {
		Queue<Pair> melt = new LinkedList<>(); // 녹은 빙산
		int sea = 0;
		int x, y;
		int qSize = ice.size();

		while (qSize-- > 0) {
			sea = 0;
			Pair pos = ice.poll();

			for (int d = 0; d < 4; ++d) {
				x = pos.x + dx[d];
				y = pos.y + dy[d];

				if (!isValid(x, y) || map[x][y] != 0)
					continue;

				++sea;
			}

			if (map[pos.x][pos.y] <= sea) {
				melt.add(pos);
			} else {
				ice.add(pos);
				map[pos.x][pos.y] -= sea;
			}
		}

		for (Pair p : melt) {
			map[p.x][p.y] = 0;
		}
	}

	static void findGroup(int _x, int _y) {
		int x, y;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(_x, _y));

		while (!q.isEmpty()) {
			Pair pos = q.poll();
			visited[pos.x][pos.y] = true;
			for (int d = 0; d < 4; ++d) {
				x = pos.x + dx[d];
				y = pos.y + dy[d];

				if (!isValid(x, y) || visited[x][y] || map[x][y] == 0)
					continue;

				visited[x][y] = true;
				q.add(new Pair(x, y));
			}
		}

	}

	static int countGroup() {
		int result = 0;

		for (final Pair p : ice) {
			if (!isValid(p.x, p.y) || visited[p.x][p.y] || map[p.x][p.y] == 0)
				continue;

			findGroup(p.x, p.y);
			++result;
		}

		return result;
	}

	static int solve() {
		int answer = 0;
		init(visited);

		// 1. 빙산 개수 세라
		while (countGroup() == 1) 
		{

			++answer;

			// 2. 녹아라
			meltIce();

			if (ice.isEmpty())
				return 0;

			init(visited);
		}
		
		return answer;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][];
		visited = new boolean[N + 1][];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			map[i] = new int[M + 1];
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0) {
					ice.add(new Pair(i, j));
				}
			}
		}
		System.out.println(solve());
	}
}
