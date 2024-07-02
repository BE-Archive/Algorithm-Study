import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static int [][][] tetris = {
		{{0, 1}, {0, 2}, {0, 3}},
		{{0, 1}, {1, 0}, {1, 1}},
		{{1, 0}, {1, 1}, {2, 1}}, 
		{{0, 1}, {-1, 1}, {-1, 2}},
		{{0, 1}, {0, -1}, {1, 0}},
		{{0, 1}, {0, -1}, {-1, 0}},
		{{0, 1}, {-1, 0}, {-2, 0}}, 
		{{0, 1}, {1, 0}, {2, 0}},
		{{0, -1}, {-1, 0}, {-2, 0}}, 
		{{0, -1}, {1, 0}, {2, 0}}
	};
	
	static int N, M;
	static int[][] mat;
	
	public static int cal(int row, int col) {
		int result = 0;
		for(int k = 0; k < tetris.length; k++) {
			boolean flag = true, reverse_flag = true;
			int temp = mat[row][col], reverse_temp = mat[row][col];
			for(int l = 0; l < 3; l++) {
				int nrow = row + tetris[k][l][0], ncol = col + tetris[k][l][1];
				int nrrow = row + tetris[k][l][1], nrcol = col + tetris[k][l][0];
				flag = flag & (nrow >= 0 && ncol >= 0 && nrow < N && ncol < M);
				reverse_flag = reverse_flag & (nrrow >= 0 && nrcol >= 0 && nrrow < N && nrcol < M);
				if(flag) temp += mat[nrow][ncol];
				if(reverse_flag) reverse_temp += mat[nrrow][nrcol];
			}
			if(flag) result = Math.max(result, temp);
			if(reverse_flag) result = Math.max(result, reverse_temp);
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				answer = Math.max(answer, cal(i, j));
			}
		}
		System.out.println(answer);
	}
}