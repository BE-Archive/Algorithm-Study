import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17070 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(inputs[j-1]);
            }
        }

        long[][][] memo = new long[n+1][n+1][3];
        memo[1][2][0]=1;
        //memo[i][j][k]란, (i,j)를 끝점으로 하는 k 상태의 파이프를 만들 수 있는 모든 방법의 수

        for (int i=1; i<n+1; i++){
            for (int j=3; j<n+1; j++){ //어떤 경우에도 j<=2인 좌표로 파이프를 옮길 수 없음을 반영

                //이 자리에 벽이 있으면 검사하지 않는다.
                if (map[i][j]==1) continue;

                //가로
                memo[i][j][0] = memo[i][j-1][0] + memo[i][j-1][1];

                //세로
                //i==1인 경우 memo[0][]을 참조하는데 이 위치에 파이프를 놓는 것은 불가능하기 때문에 검사하지 않는다.
                if (i==1) continue;
                memo[i][j][2] = memo[i-1][j][1] + memo[i-1][j][2];

                //대각선
                //대각선 파이프로 회전하려면 3개의 공간이 필요하다.
                if (map[i-1][j]==1 || map[i][j-1]==1) continue;
                memo[i][j][1] = memo[i-1][j-1][0] + memo[i-1][j-1][1] + memo[i-1][j-1][2];
            }
        }

        System.out.println(memo[n][n][0]+memo[n][n][1]+memo[n][n][2]);

    }
}
