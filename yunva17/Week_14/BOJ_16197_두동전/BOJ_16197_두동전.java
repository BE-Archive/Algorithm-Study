import java.io.*;
import java.util.*;

public class BOJ_16197_두동전 {
	static int N, M, answer = Integer.MAX_VALUE;
	static char[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		int cx1 = -1, cy1 = -1, cx2 = -1, cy2 = -1;

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'o') {
					if (cx1 == -1) {
						cx1 = i;
						cy1 = j;
					} else {
						cx2 = i;
						cy2 = j;
					}
				}
			}
		}

		solve(cx1, cy1, cx2, cy2, 1);

		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	public static void solve(int x1, int y1, int x2, int y2, int count) {
		if (count > 10) {
			return;
		}

		for (int[] d : direct) {
			int nx1 = d[0] + x1;
			int ny1 = d[1] + y1;
			int nx2 = d[0] + x2;
			int ny2 = d[1] + y2;

			int fallCount = 0;
			if (!isGraph(nx1, ny1)) {
				fallCount++;
			}
			if (!isGraph(nx2, ny2)) {
				fallCount++;
			}

			// 동전이 둘 다 떨어진 경우
			if (fallCount == 2) {
				continue;
			} else if (fallCount == 0) {
				// 이동하려는 칸이 벽이면 이동하지 않음
				if (map[nx1][ny1] == '#') {
					nx1 = x1;
					ny1 = y1;
				}
				if (map[nx2][ny2] == '#') {
					nx2 = x2;
					ny2 = y2;
				}

				solve(nx1, ny1, nx2, ny2, count + 1);
			} else {
				answer = Math.min(answer, count);
			}

		}
	}

	public static boolean isGraph(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M) {
			return false;
		} else
			return true;
	}
}