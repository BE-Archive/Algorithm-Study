import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = {{0, 1},{1, 0},{-1, 0},{0, -1}};
	
	static class Point {
		int row, col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
		
		int[][] box = new int[N][M];
		
		Queue<Point> queue = new LinkedList<Point>();
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; col++) {
				box[row][col] = Integer.parseInt(st.nextToken());
				if(box[row][col] == 1) {
					queue.add(new Point(row, col));
				}
			}
		}
		
		Point now = null;
		while((now = queue.poll()) != null) {
			int row = now.row, col = now.col;
			for(int k = 0; k < 4; k++) {
				int nextRow = row + dir[k][0], nextCol = col + dir[k][1];
				if(nextRow >= N || nextCol >= M || nextRow < 0 || nextCol < 0 || box[nextRow][nextCol] != 0) {
					continue;
				}
				box[nextRow][nextCol] = box[row][col] + 1;
				queue.add(new Point(nextRow, nextCol));
			}
		}
		int answer = -1;
		boolean flag = false;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(box[row][col] == 0) {
					flag = true;
					break;
				}
				answer = Math.max(answer, box[row][col]);
			}
			if(flag) break;
		}
		if(flag) bw.write(String.valueOf(-1));
		else bw.write(String.valueOf(answer - 1));
		bw.close();
		br.close();
	}
}