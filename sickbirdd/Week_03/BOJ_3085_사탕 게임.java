import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static char[][] candy;
	public static int choose() {
		int answer = calculate();
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N - 1; col++) {
				if(candy[row][col] != candy[row][col + 1]) {
					swap(candy, row, col, row, col + 1);
					answer = Math.max(answer, calculate());
					swap(candy, row, col, row, col + 1);
				}
				if(candy[col][row] != candy[col + 1][row]) {
					swap(candy, col, row, col + 1, row);
					answer = Math.max(answer, calculate());
					swap(candy, col, row, col + 1, row);
				}
			}
		}
		return answer;
	}
	public static int calculate() {
		int answer = 1;
		for(int i = 0; i < N; i++) {
			int tempCol = 0, tempRow = 0;
			for(int j = 0; j < N; j++) {
				if(candy[i][tempCol] != candy[i][j]) tempCol = j;
				if(candy[tempRow][i] != candy[j][i]) tempRow = j;
				answer = Math.max(answer, Math.max(j - tempCol + 1, j - tempRow + 1));
			}
		}
		return answer;
	}
	public static void swap(char[][] candy, int r1, int c1, int r2, int c2) {
		char temp = candy[r1][c1];
		candy[r1][c1] = candy[r2][c2];
		candy[r2][c2] = temp;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		candy = new char[N][N];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			candy[i] = input.toCharArray();
		}
		System.out.println(choose());
	}
}