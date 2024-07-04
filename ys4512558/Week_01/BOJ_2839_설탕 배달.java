import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        sb.append(sugar(N, dp));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sugar(int n, int[] dp) {
        if(dp[n] != 0){
            return dp[n];
        }
        if (n < 3 || n == 4) {
            return -1;
        }
        if (n == 3 || n == 5) {
            dp[n] = 1;
            return dp[n];
        }
        int res5 = sugar(n - 5, dp);
        int res3 = sugar(n - 3, dp);
        if (res5 == -1 && res3 == -1) {
            dp[n] = -1;
        } else if (res5 == -1 || res3 == -1) {
            dp[n] = Math.max(res5, res3) + 1;
        } else{
            dp[n] = Math.min(res5, res3) + 1;
        }
        return dp[n];
    }
}