import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R, size, sum;
	static final int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int[][] A;
	static int[][] visited;
	
	static class Pair {
		int row, col;
		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static boolean isRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
	
	static boolean isPossible(int a, int b){
		int diff = Math.abs(a - b);
		return L <= diff && diff <= R; 
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		visited = new int[N][N];
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < N; col++) {
				A[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		ArrayList<Integer> populations = new ArrayList<>();
		Queue<Pair> queue = new ArrayDeque<>();
		while(true) {
			for(int i = 0; i < N; i++) {
				Arrays.fill(visited[i], 0);
			}
			populations.clear();
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(visited[row][col] > 0) continue;
					int index = populations.size() + 1;
					visited[row][col] = index;
					queue.add(new Pair(row, col));
					int sum = 0, count = 0;
					while(!queue.isEmpty()) {
						Pair front = queue.poll();
						sum += A[front.row][front.col];
						count++;
						for(int k = 0; k < 4; k++) {
							int mrow = front.row + dir[k][0], mcol = front.col + dir[k][1];
							if(!isRange(mrow, mcol) || visited[mrow][mcol] != 0 || !isPossible(A[front.row][front.col], A[mrow][mcol])) continue;
							visited[mrow][mcol] = index;
							queue.add(new Pair(mrow, mcol));
						}
					}
					if(count == 1) {
						visited[row][col] = 0;
						continue;
					}
					populations.add(sum / count);
				}
			}
			if(populations.isEmpty()) break;
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(visited[row][col] > 0) {
						A[row][col] = populations.get(visited[row][col] - 1);
					}
				}
			}
			answer++;
		}
		System.out.println(answer);
	}
}