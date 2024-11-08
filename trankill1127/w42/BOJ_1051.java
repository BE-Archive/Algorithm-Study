package trankill1127.w42;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1051 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int i=0; i<n; i++){
            String input = br.readLine();
            for (int j=0; j<m; j++){
                board[i][j]=input.charAt(j)-'0';
            }
        }

        for (int recSize = Math.min(n,m); recSize>0; recSize--){
            for (int i=0; i+recSize-1<n; i++){
                for (int j=0; j+recSize-1<m; j++){
                    if (board[i][j]==board[i+recSize-1][j] &&
                            board[i][j]==board[i+recSize-1][j+recSize-1] &&
                            board[i][j]==board[i][j+recSize-1]
                    ) {
                        System.out.print(recSize*recSize);
                        return;
                    }
                }
            }
        }
    }
}
