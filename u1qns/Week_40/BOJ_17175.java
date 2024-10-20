import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 1000000007;
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[51];
        dp[0] = dp[1] = 1;
        for(int i=2; i<=N; ++i) {
            dp[i] = (dp[i-1] + dp[i-2] + 1) % MOD;
        }
        System.out.print(dp[N] % MOD);
    }
}
