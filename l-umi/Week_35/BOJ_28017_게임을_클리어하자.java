import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][M];
        // 이전 회차의 최소값과 두 번째 최소값
        int[] minPrev = { Integer.MAX_VALUE, Integer.MAX_VALUE };

        // 첫번째 회차
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            dp[0][i] = Integer.parseInt(st.nextToken());
            
            // 최소값과 두 번째 최소값 갱신
            if (dp[0][i] < minPrev[0]) {
                minPrev[1] = minPrev[0];
                minPrev[0] = dp[0][i];
            } else if (dp[0][i] < minPrev[1]) {
                minPrev[1] = dp[0][i];
            }
        }

        // 다음 회차
        for (int stage = 1; stage < N; stage++) {
            st = new StringTokenizer(br.readLine());
            int[] currentMin = { Integer.MAX_VALUE, Integer.MAX_VALUE }; // 현재 회차의 최소값과 두 번째 최소값
            
            for (int i = 0; i < M; i++) {
                int currentWeaponTime = Integer.parseInt(st.nextToken());
                dp[stage][i] = (dp[stage - 1][i] == minPrev[0] ? minPrev[1] : minPrev[0]) + currentWeaponTime;

                // 현재 회차에서 최소값과 두 번째 최소값 갱신
                if (dp[stage][i] < currentMin[0]) {
                    currentMin[1] = currentMin[0];
                    currentMin[0] = dp[stage][i];
                } else if (dp[stage][i] < currentMin[1]) {
                    currentMin[1] = dp[stage][i];
                }
            }
            minPrev = currentMin; // 최소값 배열을 갱신
        }
        // 결과 출력 (최종 최소값)
        System.out.println(minPrev[0]);
    }
}
