package Week_19.BOJ_2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_2448 {

    static int n;
    static boolean[][] arr;

    static void solve(int i, int j, int size) {
        if (size == 3) {
            arr[i][j] = true;
            arr[i + 1][j - 1] = arr[i + 1][j + 1] = true;
            for (int k = 0; k < 5; k++) {
                arr[i + 2][j - 2 + k] = true;
            }
            return;
        }

        solve(i, j, size / 2);
        solve(i + size / 2, j - size / 2, size / 2);
        solve(i + size / 2, j + size / 2, size / 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new boolean[n][2 * n - 1];

        solve(0, n - 1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                if (arr[i][j]) sb.append('*');
                else sb.append(' ');
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}



