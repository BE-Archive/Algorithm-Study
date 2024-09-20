import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[101];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int heart = Integer.parseInt(st1.nextToken());
            int happy = Integer.parseInt(st2.nextToken());

            for (int j = 100; j >= 0; j--) {
                int pre = j - heart;
                if(j - heart <= 0) continue;
                dp[j] = Math.max(dp[j], dp[pre] + happy);
            }
        }
        System.out.println(dp[100]);
    }
}