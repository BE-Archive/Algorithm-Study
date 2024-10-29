import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
    static final int MOD = 9901;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		int[] DP = new int[N+3];
	
		DP[0] = 1;
		DP[1] = 3;
		DP[2] = 7;
		
		for(int i=3; i<=N; ++i)
		{
			DP[i] = (((2*DP[i-1])%MOD) + (DP[i-2] % MOD))%MOD;
		}
		System.out.print(DP[N] %  9901);
		
	} // main
}
