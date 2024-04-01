import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] puzzle = new int[N + 1], problem = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			puzzle[number] = i;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			problem[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < N; i++) {
			int curr = problem[i], next = problem[(i + 1) % N];
			int diff = Math.abs(puzzle[curr] - puzzle[next]);
			if(diff != 1 && diff != N - 1) {
				System.out.println("bad puzzle");
				return;
			}
		}
		System.out.println("good puzzle");
	}
}