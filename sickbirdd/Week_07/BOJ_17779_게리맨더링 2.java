import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, total_sum = 0, x, y, d1, d2;
	static int[][] arr;
	static int[] area_sum = new int[5];
	static boolean[][] visited;
	
	public static boolean isRange(int r, int c, int type) {
		if(type == 0) return 1 <= r && r < x + d1 && 1 <= c && c <= y;
		else if(type == 1) return 1 <= r && r <= x + d2 && y < c && c <= N;
		else if(type == 2) return x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2;
		else if(type == 3) return x + d2 < r && r <= N && y - d1 + d2 <= c && c <= N;
		return false;
	}
	
	public static int foo() {
		for(int i = 1; i <= N; i++) {
			Arrays.fill(visited[i], false);
		}
		for(int i = 0; i <= d1; i++) {
			visited[x + i][y - i] = true;
		}
		for(int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = true;
		}
		for(int i = 0; i <= d2; i++) {
			visited[x + d1 + i][y - d1 + i] = true;
		}
		for(int i = 0; i <= d1; i++) {
			visited[x + d2 + i][y + d2 - i] = true;
		}
		
		area_sum[0] = bfs(1, 1, 0);
 		area_sum[1] = bfs(1, N, 1);
		area_sum[2] = bfs(N, 1, 2);
		area_sum[3] = bfs(N, N, 3);
		area_sum[4] = total_sum - area_sum[0] - area_sum[1] - area_sum[2] - area_sum[3];
		
		int max_area = area_sum[0], min_area = area_sum[0];
		for(int i = 0; i < 5; i++) {
			max_area = Math.max(area_sum[i], max_area);
			min_area = Math.min(area_sum[i], min_area);
		}
		
		return max_area - min_area;
	}
	
	static class Pair {
		int row, col;
		
		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static int bfs(int sr, int sc, int type) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(sr, sc));
		visited[sr][sc] = true;
		int count = 0;
		while(!queue.isEmpty()) {
			Pair front = queue.poll();
			count += arr[front.row][front.col];
			for(int k = 0; k < 4; k++) {
				int nrow = front.row + dir[k][0], ncol = front.col + dir[k][1];
				if(nrow < 1 || ncol < 1 || nrow > N || ncol > N) continue;
				if(!isRange(nrow, ncol, type) || visited[nrow][ncol]) continue;
				visited[nrow][ncol] = true;
				queue.add(new Pair(nrow, ncol));
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		
		for(int row = 1; row <= N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= N; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
				total_sum += arr[row][col];
			}
		}
		int answer = Integer.MAX_VALUE;
		for(d1 = 1; d1 <= N; d1++) {
			for(d2 = 1; d2 <= N; d2++) {
				for(x = 1; x + d1 + d2 <= N; x++) {
					for(y = 1; y + d2 <= N; y++) {
						if(1 <= y - d1) {
							answer = Math.min(answer, foo());
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
}