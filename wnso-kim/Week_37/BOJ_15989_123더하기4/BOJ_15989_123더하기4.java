import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_15989_123더하기4 {

    static int T;
    static int END = 10_001;
    static int[][] dp = new int[4][END];
    // 2+2+1 == 2+1+2 == 1+2+2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        fillDP();

        StringBuilder answer = new StringBuilder();
        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine());
            answer.append((dp[1][n]+dp[2][n]+dp[3][n])).append("\n");
        }

        System.out.println(answer);
    }

    static void fillDP(){
        Arrays.fill(dp[1], 1);
        dp[2][2] = 1;
        dp[3][3] = 1;

        for(int i=3; i<END; i++){
            dp[3][i] = dp[1][i-3] + dp[2][i-3] + dp[3][i-3];
            dp[2][i] = dp[1][i-2] + dp[2][i-2];
            dp[1][i] = dp[1][i-1];
        }
    }

}