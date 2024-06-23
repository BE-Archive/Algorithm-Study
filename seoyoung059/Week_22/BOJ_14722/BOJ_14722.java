package Week_22.BOJ_14722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n+1][n+1];
        int[][][] visited = new int[3][n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(visited[0][i],-1);
            Arrays.fill(visited[1][i],-1);
            Arrays.fill(visited[2][i],-1);
        }

        int curr;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                curr = arr[i][j];
                visited[curr][i][j] = Math.max(visited[curr][i-1][j], visited[curr][i][j-1]);
                if(visited[(curr+2)%3][i-1][j]>0||visited[(curr+2)%3][i][j-1]>0)
                    visited[curr][i][j] = Math.max(Math.max(visited[(curr+2)%3][i-1][j], visited[(curr+2)%3][i][j-1])+1,visited[curr][i][j]);
                if(curr==0) visited[curr][i][j] = Math.max(visited[curr][i][j], 1);
                visited[(curr+1)%3][i][j] = Math.max(visited[(curr+1)%3][i-1][j],visited[(curr+1)%3][i][j-1]);
                visited[(curr+2)%3][i][j] = Math.max(visited[(curr+2)%3][i-1][j],visited[(curr+2)%3][i][j-1]);
            }
        }

        int answer = Math.max(visited[0][n][n], Math.max(visited[1][n][n], visited[2][n][n]));
        System.out.println(Math.max(answer, 0));
    }
}