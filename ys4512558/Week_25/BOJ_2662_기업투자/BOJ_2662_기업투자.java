import java.io.*;
import java.util.*;

public class BOJ2662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //M개의 기업의 금액에 대한 N만원의 투자 이익
        int[][] arr = new int[M + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 1; j <= M; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        //M번째 기업까지 고려하여 N만원을 사용하여 얻은 최대 금액
        int[][] dp = new int[M + 1][N + 1];

        List<int[]>[][] list = new ArrayList[M + 1][N + 1]; //해당 투자 금액에 가장 이득을 보는 기업
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                list[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= M; i++) { //i번째 기업의 투자
            for (int j = 1; j <= N; j++) { //현재 만들려는 금액
                for (int k = N; k >= 1; k--) { //dp[i][j] = 해당 기업의 투자 k만큼 사용하여 에서 총 j만원을 사용해서 만드는 최대 금액
                    if(j < k) continue;
                    if (dp[i][j] < dp[i - 1][j]) {
                        dp[i][j] = dp[i - 1][j];
                        list[i][j] = list[i - 1][j];
                    }
                    // 이전까지의 j - k금액을 사용한 최대 금액 + 현재 기업의 k만원 사용의 최적
                    if (dp[i][j] < dp[i - 1][j - k] + arr[i][k]) {
                        dp[i][j] = dp[i - 1][j - k] + arr[i][k];
                        list[i][j] = new ArrayList<>(list[i - 1][j - k]);
                        list[i][j].add(new int[]{i, k});
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        sb.append(dp[M][N]).append("\n");
        List<int[]> results = list[M][N];
        int[] res = new int[M + 1];
        for (int i = 0; i < results.size(); i++) {
            int idx = results.get(i)[0];
            res[idx] = results.get(i)[1];
        }
        for (int i = 1; i <= M; i++) {
            sb.append(res[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}