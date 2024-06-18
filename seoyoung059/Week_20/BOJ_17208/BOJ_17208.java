package Week_20.BOJ_17208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17208 {

    static class Order {
        int x;
        int y;

        Order(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Order[] arr = new Order[n + 1];
        arr[0] = new Order(-1, -1);
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Order(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[][] dp = new int[m + 1][k + 1];
        int answer = 0;
        int x, y;
        for (int i = 1; i < n + 1; i++) {
            x = arr[i].x;
            y = arr[i].y;
            for (int j = m; j >= x; j--) {
                for (int l = k; l >= y; l--) {
                    dp[j][l] = Math.max(dp[j - x][l - y] + 1, dp[j][l]);
                    answer = Math.max(answer, dp[j][l]);
                }
            }
        }

        System.out.println(answer);

    }
}
