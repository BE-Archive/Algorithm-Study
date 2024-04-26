import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { 
	final static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int N, M, answer = Integer.MAX_VALUE;
	static boolean[][][][] visited;
	static char[][] mat;
	
	static boolean isRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < M;
	}
	
	static void backTracking(int[][] coin, int count) {
		if(count > 10 || count >= answer) return;
		int[][] temp = new int[coin.length][2];
		for(int r = 0; r < coin.length; r++) {
			temp[r][0] = coin[r][0];
			temp[r][1] = coin[r][1];
		}
		for(int[] d : dir) {
			int move = 0, fall = 0;
			for(int k = 0; k < coin.length; k++) {
				int nr = coin[k][0] + d[0], nc = coin[k][1] + d[1];
				if(!isRange(nr, nc)) {
					fall++;
					continue;
				}
				if(mat[nr][nc] != '#') {
					coin[k][0] = nr;
					coin[k][1] = nc;
					move++;
				}
			}
			if(fall == 1) answer = Math.min(answer, count);
			else if(fall == 0 && move > 0 && !visited[coin[0][0]][coin[0][1]][coin[1][0]][coin[1][1]]) {
				visited[coin[0][0]][coin[0][1]][coin[1][0]][coin[1][1]] = true;
				backTracking(coin, count + 1);
				visited[coin[0][0]][coin[0][1]][coin[1][0]][coin[1][1]] = false;
			}
			for(int r = 0; r < coin.length; r++) {
				coin[r][0] = temp[r][0];
				coin[r][1] = temp[r][1];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new char[N][M];
		int[][] init = new int[2][2];
		visited = new boolean[N][M][N][M];
		int idx = 0;
		for(int i = 0; i < N; i++) {
			mat[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(mat[i][j] == 'o') {
					init[idx][0] = i;
					init[idx][1] = j;
					idx++;
				}
			}
		}
		backTracking(init, 1);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
}