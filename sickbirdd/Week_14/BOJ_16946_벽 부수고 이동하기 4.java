import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { 
	final static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		char[][] c = new char[N][M];
		for(int i = 0; i < N; i++) {
			c[i] = br.readLine().toCharArray();
		}
		int[][] count = new int[N][M];
		int[][] visited = new int[N][M];
		StringBuilder sb = new StringBuilder();
		Map<Integer, Boolean> map = new HashMap<>();
		Queue<int[]> queue = new ArrayDeque<>(), tmp = new ArrayDeque<>();
		int areaIndex = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(c[i][j] == '0' && visited[i][j] == 0) {
					queue.add(new int[] {i, j});
					visited[i][j] = ++areaIndex;
					int cnt = 0;
					while(!queue.isEmpty()) {
						int[] now = queue.poll();
						tmp.add(new int[] {now[0], now[1]});
						cnt++;
						for(int k = 0; k < 4; k++) {
							int nr = now[0] + dir[k][0], nc = now[1] + dir[k][1];
							if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] > 0 || c[nr][nc] == '1') continue;
							visited[nr][nc] = areaIndex;
							queue.add(new int[] {nr, nc});
						}
					}
					while(!tmp.isEmpty()) {
						int[] now = tmp.poll();
						count[now[0]][now[1]] = cnt;
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(c[i][j] == '1') {
					int cal = 1;
					for(int k = 0; k < 4; k++) {
						int nr = i + dir[k][0], nc = j + dir[k][1];
						if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
						if(map.getOrDefault(visited[nr][nc], false)) continue;
						map.put(visited[nr][nc], true);
						cal += count[nr][nc];
					}
					sb.append(cal % 10);
					map.clear();
				}
				else sb.append(0); 
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}