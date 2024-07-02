import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 N
		int N = Integer.parseInt(br.readLine());
		
		// 초기화
		int[] sequence = new int[N];
		int[] dp = new int[N];
		
		// 입력 sequence, dp
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sequence[i] = Integer.parseInt(stk.nextToken());
			dp[i] = sequence[i];
		}
		
		// DP 확인
		for(int i=1; i<N; i++) {
			for(int j=i-1; j>=0; j--) {
				if(sequence[j] >= sequence[i]) continue;
					
				dp[i] = Math.max(dp[i], sequence[i]+dp[j]);
			}
		}

		// 출력
		int result = 0;
		for(int n: dp) result = Math.max(result, n);
		System.out.println(result);
	}

}
