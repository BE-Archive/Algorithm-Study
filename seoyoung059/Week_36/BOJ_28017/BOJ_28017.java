package Week_36.BOJ_28017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28017 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] time = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] arr = new int[n + 1][m];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                int tmp = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {
                    if (arr[i - 1][k] < tmp && k != j)
                        tmp = arr[i - 1][k];
                }
                arr[i][j] = tmp + time[i - 1][j];
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            answer = Math.min(answer, arr[n][i]);
        }
        System.out.println(answer);

    }
}