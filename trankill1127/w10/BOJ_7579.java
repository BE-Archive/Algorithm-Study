import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int MemorySum = 0;
        int[] memory = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            MemorySum += memory[i];
        }

        int costSum = 0;
        int[] cost = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            costSum += cost[i];
        }

        int[] dp = new int[costSum + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = costSum; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j - cost[i]] + memory[i], dp[j]);
            }
        }

        for (int i = 0; i <= costSum; i++){
            if (M<=dp[i]) {
                System.out.println(i);
                break;
            }
        }

    }
}
