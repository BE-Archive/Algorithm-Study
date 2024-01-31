import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Corn implements Comparable<Corn> {
		int row, col, value;
		Corn(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
		
		@Override
		public int compareTo(Corn c1) {
			return c1.value - this.value;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] value = new int[N][M];
		
		PriorityQueue<Corn> pq = new PriorityQueue<Corn>();
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; col++) {
				value[row][col] = Integer.parseInt(st.nextToken());
				
				if(row == 0 || col == 0 || row == N - 1 || col == M - 1) {
					pq.add(new Corn(row, col, value[row][col]));
					value[row][col] = -1;
				}
			}
		}
		
		int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int K = Integer.parseInt(br.readLine());
		Corn highestCorn;
		while(K-- > 0 && (highestCorn = pq.poll()) != null) {
			int row = highestCorn.row, col = highestCorn.col;
			sb.append(row + 1).append(' ').append(col + 1).append('\n');
			for(int k = 0; k < 4; k++) {
				int nextRow = row + dir[k][0], nextCol = col + dir[k][1];
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				if(value[nextRow][nextCol] == -1) continue;
				pq.add(new Corn(nextRow, nextCol, value[nextRow][nextCol]));
				value[nextRow][nextCol] = -1;
			}
		}
		System.out.println(sb.toString());
	}
}