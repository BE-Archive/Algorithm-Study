import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980_선발_명단 {

    static final int n = 11;

    static int C;
    static int[][] score;
    static boolean[] visited;

    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        C = Integer.parseInt(br.readLine());

        score = new int[n][n];

        while (C-- > 0) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[n];
            max = 0;

            score(0, 0);

            sb.append(max).append('\n');
        }

        System.out.println(sb);
    }

    public static void score(int i, int s) {
        if (i == n) {
            max = Math.max(max, s);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (score[j][i] == 0 || visited[j]) {
                continue;
            }

            visited[j] = true;
            score(i + 1, s + score[j][i]);
            visited[j] = false;
        }
    }
}
