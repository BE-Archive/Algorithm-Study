import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] benefit = new int[n+1][m+1];
        for (int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine().trim());
            st.nextToken();
            for (int j=1; j<=m; j++){
                benefit[i][j]=Integer.parseInt(st.nextToken());
                //i 만큼의 돈을 j에 투자해서 얻는 이득
            }
        }

        int[][] dp = new int[n+1][m+1];
        int[][] track = new int[n+1][m+1];
        for (int j=1; j<=m; j++){ //투자액
            for (int i=0; i<=n; i++){ //회사
                for (int k=0; k<=n-i; k++){
                    if (dp[k+i][j]<dp[k][j-1]+benefit[i][j]){
                        //k+i 금액을 j 회사까지 투자한 금액이
                        //k 금액을 j-1 회사까지 투자한 후, i 만큼 j에 투자해서 얻는 이익보다 작은 경우,

                        dp[k+i][j]=dp[k][j-1]+benefit[i][j];
                        track[k+i][j]=i; //이때 j에 투자한 금액을 기록한다.
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n][m]).append("\n");

        int[] portion = new int[m+1];
        int x=n, y=m;
        while (y>0){
            portion[y]=track[x][y];
            x-=track[x][y];
            y--;
        }
        for (int i=1; i<=m; i++) sb.append(portion[i]).append(" ");

        System.out.println(sb.toString());
    }
}
