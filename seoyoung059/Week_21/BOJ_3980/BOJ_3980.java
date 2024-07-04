package Week_21.BOJ_3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980 {

    static int answer;
    static int[][] arr = new int[11][11];

    static void solve(int i, int sum, int check) {
        if (i == 11) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int j = 0; j < 11; j++) {
            if (arr[i][j] == 0 || ((check & (1 << j)) != 0)) continue;
            solve(i + 1, sum + arr[i][j], check | (1 << j));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    arr[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            solve(0, 0, 0);

            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}