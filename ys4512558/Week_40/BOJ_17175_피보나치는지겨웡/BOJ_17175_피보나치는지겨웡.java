import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;
    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[51];
        dp[0] = dp[1] = 1;
        System.out.println(fibonacci(N));
    }

    public static int fibonacci(int N) {
        if (dp[N] != 0) {
            return dp[N];
        }
        return dp[N] = (fibonacci(N - 1) + fibonacci(N - 2) + 1) % MOD;
    }
}