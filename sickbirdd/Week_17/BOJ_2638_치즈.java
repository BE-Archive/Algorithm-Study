import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] mat = new int[N][M], air = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>(), temp = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		mat[0][0] = -1;
		queue.add(new int[] {0, 0});
		while(true) {
			while(!queue.isEmpty()) {
				int[] now = queue.poll();
				for(int k = 0; k < 4; k++) {
					int nr = now[0] + dir[k][0], nc = now[1] + dir[k][1];
					if(nr < 0 || nc < 0 || nr >= N || nc >= M || mat[nr][nc] == -1 || visited[nr][nc]) continue;
					if(mat[nr][nc] == 0) {
						queue.add(new int[] {nr, nc});
						mat[nr][nc] = -1;
					}
					else {
						if(++air[nr][nc] >= 2) {
							visited[nr][nc] = true;
							temp.add(new int[] {nr, nc});
						}
					}
				}
			}
			
			if(temp.isEmpty()) break; 
			ans++;
			while(!temp.isEmpty()) {
				int[] now = temp.poll();
				mat[now[0]][now[1]] = -1;
				queue.add(now);
			}
		}
		System.out.println(ans);
	}
}