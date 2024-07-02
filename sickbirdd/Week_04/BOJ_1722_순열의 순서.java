import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] factorial = new long[N];
		factorial[0] = 1;
		for(int i = 1; i < N; i++) {
			factorial[i] = factorial[i - 1] * (long) i;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int problem = Integer.parseInt(st.nextToken());
		if(problem == 2) {
			int[] number = new int[N + 1];
			long answer = 1;

			for(int i = 1; i <= N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 1; i < N; i++) {
				int count = 0;
				for(int j = i + 1; j <= N; j++) {
					if(number[i] > number[j]) {
						count++;
					}
				}
				answer += (long) count * factorial[N - i];
			}
			System.out.println(answer);
		}
		else {
			StringBuilder sb = new StringBuilder();
			long index = Long.parseLong(st.nextToken());
			boolean[] select = new boolean[N + 1];
			
			for(int i = 1; i <= N; i++) {
				int count = 0;
				for(int j = N - i; j >= 1; j--) {
					long limit = (long) j * factorial[N - i];
					if(limit < index) {
						count = j;
						break;
					}
				}
				index -= count * factorial[N - i];
				for(int j = 1; j <= N; j++) {
					if(select[j] || --count >= 0) continue;
					sb.append(j).append(' ');
					select[j] = true;
					break;
				}
			}
			System.out.println(sb);
		}
	}
}