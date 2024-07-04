import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22866_탑보기 {

    static int N;
    static int[] buildings;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력| N, buildings
        N = Integer.parseInt(br.readLine()); 
        StringTokenizer stk = new StringTokenizer(br.readLine());

        buildings = new int[N];
        for(int i=0; i<N; i++)  buildings[i] = Integer.parseInt(stk.nextToken());
        
        // 초기화| dp
        dp = new int[N][3]; // [i][0]: i기준 왼쪽 빌딩, [i][1]: i기준 오른쪽 빌딩, [i][2]: 가장 가까운 빌딩

        // 왼쪽 빌딩 확인
        int max = buildings[0];
        for(int i=1; i<N; i++){
            if(max < buildings[i]){
                max = buildings[i];
                continue;
            }

            for(int j=i-1; j>=0; j--){
                if(buildings[j] > buildings[i]){
                    dp[i][0] = dp[j][0]+1;
                    dp[i][2] = j+1;
                    break;
                }
            }
        }

        // 오른쪽 빌딩 확인
        max = buildings[N-1];
        for(int i=N-2; i>=0; i--){
            if(buildings[i] > max){
                max = buildings[i];
                continue;
            }

            for(int j=i+1; j<N; j++){
                if(buildings[i] < buildings[j]){
                    dp[i][1] = dp[j][1]+1;

                    if(dp[i][0] == 0 || (i - dp[i][2]+1) > (j-i)){
                        dp[i][2] = j+1;
                    }

                    break;
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            int sum = dp[i][0] + dp[i][1];

            sb.append(sum).append(" ");
            if(sum != 0) sb.append(dp[i][2]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
