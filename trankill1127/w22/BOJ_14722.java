import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = null;
        int[][] land = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= n; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n + 1][n + 1][3];
        if (land[1][1] == 0) {
            dp[1][1][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);
                }

                int milk = land[i][j];
                int befMilk = (milk + 2) % 3;
                if (dp[i][j][befMilk] != 0) 
                    dp[i][j][milk] = Math.max(dp[i][j][milk], dp[i][j][befMilk] + 1);
                else if (milk == 0) 
                    dp[i][j][milk] = Math.max(dp[i][j][milk], dp[i][j][befMilk] + 1);
            }
        }

        System.out.println(Math.max(dp[n][n][0], Math.max(dp[n][n][1], dp[n][n][2])));
    }
}
