package Week_19.BOJ_21278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_21278 {

    static final int INF = 999_999_999;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[][] distance = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(i==j) distance[i][j] = 0;
                else distance[i][j] = INF;
            }
        }

        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            distance[a][b] = 1;
            distance[b][a] = 1;
        }

        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                for (int j = i+1; j < n+1; j++) {
                    distance[i][j] = Math.min(distance[i][j],distance[i][k]+distance[k][j]);
                }
            }
        }

        int sum = Integer.MAX_VALUE;
        int tmp;
        a = n+1; b = n+1;
        for (int i = 1; i < n+1; i++) {
            for (int j = i+1; j < n+1; j++) {
                tmp = 0;
                for (int k = 1; k < n+1; k++) {
                    tmp+=Math.min(distance[k][i], distance[k][j]);
                }
                if(sum > tmp){
                    a = i; b = j; sum = tmp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(a).append(" ").append(b).append(" ").append(sum*2);
        System.out.println(sb);
    }
}
