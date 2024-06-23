import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14722_우유도시 {

    static int N;
    static int[][] map;
    static int[][][] dp; // 딸기, 초코, 바나나
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dp = new int[3][N+1][N+1];

        for(int i=1; i<=N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++){
                map[i][j] =  Integer.parseInt(stk.nextToken());
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dp[0][i][j] = Math.max(dp[0][i-1][j], dp[0][i][j-1]);
                if(map[i][j] == 0){
                    if(dp[2][i-1][j] >0) dp[0][i][j] = Math.max(dp[0][i][j], dp[2][i-1][j]+1);
                    if(dp[2][i][j-1] >0) dp[0][i][j] = Math.max(dp[0][i][j], dp[2][i][j-1]+1);
                    if(map[i][j] == 0) dp[0][i][j] = Math.max(dp[0][i][j], 1);
                }
                

                dp[1][i][j] = Math.max(dp[1][i-1][j], dp[1][i][j-1]);
                if(map[i][j] == 1){
                    if(dp[0][i-1][j] >0) dp[1][i][j] = Math.max(dp[1][i][j], dp[0][i-1][j]+1);
                    if(dp[0][i][j-1] >0) dp[1][i][j] = Math.max(dp[1][i][j], dp[0][i][j-1]+1);
                }

                dp[2][i][j] = Math.max(dp[2][i-1][j], dp[2][i][j-1]);
                if(map[i][j] == 2){
                    if(dp[1][i-1][j] >0) dp[2][i][j] = Math.max(dp[2][i][j], dp[1][i-1][j]+1);
                    if(dp[1][i][j-1] >0) dp[2][i][j] = Math.max(dp[2][i][j], dp[1][i][j-1]+1); 
                }
            }
        }

        System.out.println(Math.max(dp[0][N][N], Math.max(dp[1][N][N], dp[2][N][N])));        
    }
}