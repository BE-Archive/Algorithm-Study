package Week_40.BOJ_1719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][][] arr = new int[2][n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[0][i], Integer.MAX_VALUE);
            arr[0][i][i] = 0;
            arr[1][i][i] = -1;
        }

        int a, b, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            if(arr[0][a][b] > t) {
                arr[0][a][b] = t;
                arr[0][b][a] = t;
                arr[1][a][b] = b;
                arr[1][b][a] = a;
            }
        }


        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < n+1; j++) {
                    if(arr[0][i][k] == Integer.MAX_VALUE || arr[0][k][j] == Integer.MAX_VALUE) continue;
                    if(arr[0][i][k] + arr[0][k][j] < arr[0][i][j]) {
                        arr[0][i][j] = arr[0][i][k] + arr[0][k][j];
                        arr[1][i][j] = arr[1][i][k];
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(i==j) sb.append('-');
                else sb.append(arr[1][i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);


    }
}
