import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14722 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = null;
        int[][] land = new int[n][n];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0; j<n; j++){
                land[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n][n][3];
        if (land[0][0] == 0) {
            dp[0][0][0] = 1;
        }

        // 첫 번째 행 초기화
        for (int i = 1; i < n; i++) {
            int now=land[0][i];
            dp[0][i][0] = (now == 0) ? dp[0][i - 1][2] + 1 : dp[0][i - 1][0];
            dp[0][i][1] = (now == 1 && dp[0][i][0] > dp[0][i][2]) ? dp[0][i - 1][0] + 1 : dp[0][i - 1][1];
            dp[0][i][2] = (now == 2 && dp[0][i][1] > dp[0][i][0]) ? dp[0][i - 1][1] + 1 : dp[0][i - 1][2];
        }
        // 첫 번째 열 초기화
        for (int i = 1; i < n; i++) {
            int now=land[i][0];
            dp[i][0][0] = (now == 0) ? dp[i - 1][0][2] + 1 : dp[i - 1][0][0];
            dp[i][0][1] = (now == 1 && dp[i][0][0] > dp[i][0][2]) ? dp[i - 1][0][0] + 1 : dp[i - 1][0][1];
            dp[i][0][2] = (now == 2 && dp[i][0][1] > dp[i][0][0]) ? dp[i - 1][0][1] + 1 : dp[i - 1][0][2];
        }

        for (int i=1; i<n; i++) {
            for (int j=1; j<n; j++) {
                int now=land[i][j];

                dp[i][j][0] = (now==0)
                        ? Math.max(dp[i][j-1][2]+1, dp[i-1][j][2]+1)
                        : Math.max(dp[i][j-1][0], dp[i-1][j][0]);

                dp[i][j][1] = (now==1 && dp[i][j][0] > dp[i][j][2])
                        ? Math.max(dp[i][j-1][0]+1, dp[i-1][j][0]+1)
                        : Math.max(dp[i][j-1][1], dp[i-1][j][1]);

                dp[i][j][2] = (now==2 && dp[i][j][1] > dp[i][j][0])
                        ? Math.max(dp[i][j-1][1]+1, dp[i-1][j][1]+1)
                        : Math.max(dp[i][j-1][2], dp[i-1][j][2]);
            }
        }

        int result = Math.max(dp[n-1][n-1][0], Math.max(dp[n-1][n-1][1], dp[n-1][n-1][2]));
        System.out.println(result);
    }
}