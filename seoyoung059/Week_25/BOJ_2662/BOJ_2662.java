package Week_25.BOJ_2662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_2662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 각 투자금 input을 받는다
        int[][] arr = new int[n + 1][m];
        int tmp;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                arr[tmp][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열을 채워나간다.
        // 기업 번호 i >> 해당 기업의 투자금 (j) >> 채워 나갈 금액 (k)
        int[][][] dp = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                for (int k = n; k >= 0; k--) {
                    if (k - j < 0) break; // 채워나갈 금액보다 해당 기업의 투자금이 커서는 안된다.
                    if (dp[i + 1][k][0] < dp[i][k - j][0] + arr[j][i]) {
                        dp[i + 1][k][0] = dp[i][k - j][0] + arr[j][i];  // 수익금 갱신
                        dp[i + 1][k][1] = j;                            // 해당 기업의 투자금 기록
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();

        // 투자금 백트래킹 과정
        ArrayDeque<Integer> q = new ArrayDeque<>();
        tmp = n;
        for (int i = m; i > 0; i--) {
            q.offerFirst(dp[i][tmp][1]);
            tmp -= q.peekFirst();
        }

        sb.append(dp[m][n][0]).append("\n");

        for (Integer i : q) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
