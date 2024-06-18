import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int[][] dir = {{0, 1},{1, 0},{-1, 0},{0, -1}};
	
	static class Point implements Comparable<Point> {
		int row, col, cost;
		Point(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int problemIndex = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			
			int[][] cave = new int[N][N];
			int[][] dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], INF);
			}
			
			for(int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < N; col++) {
					cave[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			PriorityQueue<Point> pq = new PriorityQueue<Point>();
			dist[0][0] = cave[0][0];
			pq.add(new Point(0, 0, dist[0][0]));
			while(!pq.isEmpty()) {
				Point now = pq.remove();
				int row = now.row, col = now.col, cost = now.cost;
				for(int k = 0; k < 4; k++) {
					int nextRow = row + dir[k][0], nextCol = col + dir[k][1];
					if(nextRow >= N || nextCol >= N || nextRow < 0 || nextCol < 0 || dist[nextRow][nextCol] <= cost + cave[nextRow][nextCol]) {
						continue;
					}
					dist[nextRow][nextCol] = cost + cave[nextRow][nextCol];
					pq.add(new Point(nextRow, nextCol, dist[nextRow][nextCol]));
				}
			}
			sb.append("Problem ");
			sb.append(problemIndex++);
			sb.append(": ");
			sb.append(dist[N - 1][N - 1]);
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}