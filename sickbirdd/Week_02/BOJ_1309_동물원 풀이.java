import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()), MOD = 9901;
		int[] dp = new int[N + 2];
		dp[1] = 3; dp[2] = 7;
		for(int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 2] % MOD + 2 * dp [i - 1] % MOD) % MOD;
		}
		System.out.println(dp[N]);
	}
}