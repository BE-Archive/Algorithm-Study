import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int n;
	public static int m;
	public static int d;
	public static int[][] map;
	public static int[][] copyMap;
	public static int[] archers = new int[3];
  public static int maxCnt = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		copyMap = new int[n][m];

		solution(0, 0);
		System.out.println(maxCnt);
	}

	public static void solution(int idx, int start) {
		if (idx == 3) {

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					copyMap[i][j] = map[i][j];

			maxCnt = Math.max(maxCnt, getScore());
			return;
		}

		for (int i = start; i < m; i++) {
			archers[idx] = i;
			solution(idx + 1, i + 1);
		}
	}

	public static int getScore() {
		int totScore = 0;
		int leftEnemy = 0;

		do {
			totScore += attack();
			move();

			leftEnemy = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (copyMap[i][j] == 1)
						leftEnemy++;
				}
			}
		} while (leftEnemy != 0);

		return totScore;
	}

	public static void move() {
		for (int i = 0; i < m; i++) {
			for (int j = n - 1; j > 0; j--) {
				copyMap[j][i] = copyMap[j - 1][i];
			}
			copyMap[0][i] = 0;
		}
	}

	public static int attack() {
		int score = 0;

		for (int a = 0; a < 3; a++) { 

			int minDist = Integer.MAX_VALUE;
			int[] minEnemy = new int[2];

			perEnemy:
			for (int i = 0; i < m; i++) {
				for (int j = 1; n-j>=0; j++) {
					int x = n - j;
					if (copyMap[x][i] == 0)
						continue;

					int distance = (n - x) + Math.abs(archers[a] - i);
					if (distance <= d && distance < minDist) {
						minDist = distance;
						minEnemy[0] = x;
						minEnemy[1] = i;
						if (minDist==1) break perEnemy;
					}
				}
			}

			if (minDist<=d && copyMap[minEnemy[0]][minEnemy[1]] == 1) { 
				score++;
				copyMap[minEnemy[0]][minEnemy[1]] = -1;
			} 

		}

		for (int i = 0; i < m; i++) {
			for (int j = 1; n-j>=0; j++) {
				int x = n - j;
				if (copyMap[x][i] == -1) {
					copyMap[x][i] = 0;
				}
			}
		}

		return score;
	}
}
