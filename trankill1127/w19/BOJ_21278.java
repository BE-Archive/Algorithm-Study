import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_21278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n+1][n+1];
        for (int i=1; i<=n; i++){
            Arrays.fill(board[i], 101);
            board[i][i]=0;
        }

        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b]=1;
            board[b][a]=1;
        }

        //플로이드 워셜
        for (int k=1; k<=n; k++) { //중간 정점
            for (int i=1; i<=n; i++){
                if (i==k) continue;

                for (int j=1; j<=n; j++){
                    if (j==k) continue;

                    board[i][j] = Math.min(
                            board[i][k]+board[k][j],
                            board[i][j]
                    );
                }
            }
        }

        int l=0, r=0;
        int minLen = Integer.MAX_VALUE;
        for (int i=1; i<n; i++){ //A
            for (int j=i+1; j<=n; j++) { //B

                int len=0;
                for (int k=1; k<=n; k++)
                    len += Math.min(board[i][k], board[j][k]);

                if (minLen>len) {
                    l=i; r=j; minLen=len;
                }
            }
        }

        System.out.println(l+" "+r+" "+minLen*2);
    }
}
