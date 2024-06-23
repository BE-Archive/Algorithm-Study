import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14722: 우유 도시
 */
public class BOJ14722 {
    static int[][] map;
    static int[][][] dp;
    static int[] dx = {-1, 0};
    static int[] dy = {0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[3][N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //무조건 딸기부터 먹어야 하므로 해당 위치가 딸기인 경우 1개로 카운팅 해둔다.
                if(map[i][j] == 0) dp[0][i][j] = 1;
            }
        }
        //dp 테이블 정의 : [현재 위치에 어떤 맛을 기록할 것인지][x][y]
        //즉, 현재 위치에 해당 인덱스에 해당하는 맛의 최대 개수 기록
        //[0][i][j]는 i, j위치에 마지막으로 딸기맛을 먹었을 때 최대 개수 기록
        //map[i][j] == 0이면 현재 위치의 딸기맛까지 먹은 개수까지 포함해서 갱신
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    //이전 값
                    int px = i + dx[k];
                    int py = j + dy[k];
                    if (px < 0 || py < 0) continue;
                    for (int l = 0; l < 3; l++) {
                        //현재 위치의 우유를 먹는 경우
                        //현재 위치의 우유 이전에 먹어야 할 맛에 1을 더해줌
                        if (map[i][j] == l) {
                            //이전 값이 0이 아니면 1더해주기 (0이면 딸기맛부터 안먹고왔음)
                            //이전 맛을 확인하기 위해 모듈러 연산 사용 이전 맛이기 때문에 0의 경우 2를 확인해야함
                            //따라서 index + 3 - 1 = index + 2로 인덱스 범위 내에 넣어주기
                            dp[l][i][j] = Math.max(dp[l][i][j], dp[(l + 2) % 3][px][py] + (dp[(l + 2) % 3][px][py] == 0 ? 0 : 1));
                        }
                        //현재 맛을 못먹는 경우 마지막으로 먹은 우유가 l맛인 우유의 최대개수로 갱신
                        dp[l][i][j] = Math.max(dp[l][i][j], dp[l][px][py]);
                    }
                }
            }
        }
        System.out.println(Math.max(Math.max(dp[0][N - 1][N - 1], dp[1][N - 1][N - 1]), dp[2][N - 1][N - 1]));
    }
}