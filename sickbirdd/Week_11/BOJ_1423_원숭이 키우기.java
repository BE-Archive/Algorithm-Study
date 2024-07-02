import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] count = new long[N], power = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			count[i] = Long.parseLong(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		long init = 0L;

		for(int i = 0; i < N; i++) {
			power[i] = Long.parseLong(st.nextToken());
			init += count[i] * power[i];
		}

		int D = Integer.parseInt(br.readLine());

		long[][] dp = new long[D + 1][D + 1];
		Arrays.fill(dp[0], init);
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				long diff = power[j] - power[i];
				int cost = j - i;
				if(diff <= 0) continue;
				for(int e = D; e >= cost; e--) {
					for(int s = 1; s <= count[i]; s++) {
						if(cost * s > e) break;
						dp[e][s] = Math.max(dp[e][s], dp[e - cost][s - 1] + diff);
						dp[e][s] = Math.max(dp[e][s], dp[e - cost * s][0] + (long)s * diff);
					}
					for(int s = D; s >= count[i]; s--) {
						dp[e][s] = Math.max(dp[e][s], dp[e - s][s] + diff);
					}
				}

			}
		}
		long answer = 0;
		System.out.print("결과\t");
		for(int i = 0; i <= D; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		for(int i = 0; i <= D; i++) {
			System.out.print("i : " + i + "\t");
			for(int j = 0; j <= D; j++) {
				System.out.print(dp[i][j] + "\t");
				answer = Math.max(answer, dp[i][j]);
			}
			System.out.println();
		}
        System.out.println(answer);
	}
}