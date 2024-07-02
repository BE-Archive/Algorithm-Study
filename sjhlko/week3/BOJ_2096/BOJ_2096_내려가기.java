package sjhlko.week3.BOJ_2096;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_2096_내려가기 {
    //https://www.acmicpc.net/problem/2096
    //내려가기
    static int[][] nums, dpMin, dpMax;
    static int N;

    static void solution() {
        for (int i = 0; i < 3; i++) {
            dpMax[0][i] = nums[0][i];
            dpMin[0][i] = nums[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dpMin[i][j] = dpMin[i - 1][j] + nums[i][j];
                dpMax[i][j] = dpMax[i - 1][j] + nums[i][j];
                for (int k = 0; k < 3; k++) {
                    if (Math.abs(j - k) > 1) continue;
                    dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i-1][k] + nums[i][j]);
                    dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i-1][k] + nums[i][j]);
                }
            }
        }
        System.out.println(Math.max(dpMax[N-1][0],Math.max(dpMax[N-1][1],dpMax[N-1][2]))
                + " " +
                Math.min(dpMin[N-1][0],Math.min(dpMin[N-1][1],dpMin[N-1][2]))
        );
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nums = new int[N][3];
        dpMin = new int[N][3];
        dpMax = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
    }

}
