import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        /**
         * 0 : 추가된 칸에 한마리도 없을 때
         * 1 : 왼쪽에 있을 떄
         * 2 : 오른쪽에 있을 때
         */
        int[][] dp = new int[N + 1][3];
        for (int i = 0; i < 3; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }
        int res = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
        sb.append(res);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}