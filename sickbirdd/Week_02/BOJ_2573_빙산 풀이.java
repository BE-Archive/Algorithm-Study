import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int row, col, height;
		Point(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		
		Point[][] mat = new Point[N][M];
		boolean[][] isMelted = new boolean[N][M];
		
		ArrayList<Point> ices = new ArrayList<Point>();
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; col++) {
				int height = Integer.parseInt(st.nextToken());
				mat[row][col] = new Point(row, col, height);
				if(height != 0) {
					ices.add(mat[row][col]);
				}
				else {
					isMelted[row][col] = true;
				}
			}
		}
		
		int[][] dir = {{1, 0}, {0, 1}, {0, -1},{-1, 0}};
		int time = 0;
		
		while(true) {
			int area = 0;
			boolean[][] visited = new boolean[N][M];
			for(int index = 0; index < ices.size(); index++) {
				Point ice = ices.get(index);
				int row = ice.row, col = ice.col, height = ice.height;
				if(height == 0 || visited[row][col]) continue;
				area++;
				visited[row][col] = true;
				Queue<Point> q = new ArrayDeque<Point>();
				q.add(ice);
				Point now = null;
				while((now = q.poll()) != null) {
					for(int k = 0; k < 4; k++) {
						int nextRow = now.row + dir[k][0], nextCol = now.col + dir[k][1];
						if(visited[nextRow][nextCol]) continue;
						
						if(isMelted[nextRow][nextCol] == true) {
							if(mat[now.row][now.col].height > 0) mat[now.row][now.col].height--;
							continue;
						}
						
						visited[nextRow][nextCol] = true;
						q.add(mat[nextRow][nextCol]);
					}
				}
			}
			if(area == 0) {
				System.out.println(0);
				return;
			}
			else if(area > 1) {
				System.out.println(time);
				return; 
			}
			for(int index = 0; index < ices.size(); index++) {
				Point ice = ices.get(index);
				if(ice.height <= 0) {
					isMelted[ice.row][ice.col] = true;
				}
			}
			time++;
		}
	}
}