import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17845_수강_과목 {

    static int N, K;
    static int[] dp;
    static int[][] classes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        dp = new int[N+1];
        classes = new int[K][2];

        for(int k=0; k<K; k++){
            stk = new StringTokenizer(br.readLine());
            classes[k][0] = Integer.parseInt(stk.nextToken());
            classes[k][1] = Integer.parseInt(stk.nextToken());
        }

        for(int k=0; k<K; k++){
            for(int n=N; n>0; n--){
                if((n-classes[k][1] >= 0) && (dp[n-classes[k][1]]+classes[k][0] > dp[n])){
                    dp[n] = dp[n-classes[k][1]]+classes[k][0];
                }
            }
        }

        System.out.println(dp[N]);
    }
}