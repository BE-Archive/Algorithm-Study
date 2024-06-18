import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int row, col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int mat[][] = new int[N][M];
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0, cheese = 0;
		int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		while(true) {
			boolean isRemoved = true;
			boolean[][] visited = new boolean[N][M];
			int temp = 0;
			Queue<Point> queue = new ArrayDeque<Point>();
			queue.add(new Point(0, 0));
			visited[0][0] = true;
			Point front;
			while((front = queue.poll()) != null) {
				for(int k = 0; k < 4; k++) {
					int nextRow = front.row + dir[k][0], nextCol = front.col + dir[k][1];
					if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
					if(visited[nextRow][nextCol]) continue;
					visited[nextRow][nextCol] = true;
					if(mat[nextRow][nextCol] == 1) {
						isRemoved = false;
						mat[nextRow][nextCol] = 0;
						temp++;
					}
					else queue.add(new Point(nextRow, nextCol));
				}
			}
			if(isRemoved) {
				System.out.println(time + "\n" + cheese);
				break;
			}
			cheese = temp;
			time++;
		}
	}
}