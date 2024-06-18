import java.io.*;
import java.util.StringTokenizer;
public class BOJ3980 {
    static final int ELEVEN = 11;
    static int[][] positions;
    static boolean[] isv;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            positions = new int[ELEVEN][ELEVEN];
            isv = new boolean[ELEVEN];
            for (int j = 0; j < ELEVEN; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < ELEVEN; k++) {
                    positions[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            backtracking(0, 0);
            sb.append(max).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void backtracking(int depth, int sum) {
        if (depth == ELEVEN) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < ELEVEN; i++) {
            if(positions[depth][i] == 0 || isv[i]) continue;
            isv[i] = true;
            backtracking(depth + 1, sum + positions[depth][i]);
            isv[i] = false;
        }
    }
}