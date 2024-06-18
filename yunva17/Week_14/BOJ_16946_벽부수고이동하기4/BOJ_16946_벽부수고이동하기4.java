import java.io.*;
import java.util.*;

public class BOJ_16946_벽부수고이동하기4 {
	static int N, M;
	static int[][] map, answer;
	static boolean[][] visited;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		answer = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] str = st.nextToken().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);

			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited = new boolean[N][M];

				if (map[i][j] == 1) {
					answer[i][j] = bfs(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(answer[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static boolean isGraph(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { x, y });
		int count = 1;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int[] d : direct) {
				int dx = now[0] + d[0];
				int dy = now[1] + d[1];

				if(!isGraph(dx, dy) || visited[dx][dy]) {
					continue;
				}

				if (map[dx][dy] == 0) {
					visited[dx][dy] = true;
					queue.add(new int[] { dx, dy });
					count++;
				}
			}

		}
		
		return count;
	}
}