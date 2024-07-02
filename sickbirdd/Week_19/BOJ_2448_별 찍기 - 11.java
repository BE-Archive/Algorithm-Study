import java.io.*;
public class Main {
	static char[][] star;
	static void foo(int row, int col, int exp) {
		if(exp == 3) {
			star[row][col + 2] = '*';
			star[row + 1][col + 1] = '*';
			star[row + 1][col + 3] = '*';
			for(int j = 0; j < 5; j++) {
				star[row + 2][col + j] = '*';
			}
			return;
		}
		foo(row, col + exp / 2, exp / 2);
		foo(row + exp / 2, col, exp / 2);
		foo(row + exp / 2, col + exp, exp / 2);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		star = new char[N][2 * N - 1];
		foo(0, 0, N);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2 * N - 1; j++) {
				sb.append(star[i][j] == '*' ? '*' : ' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}