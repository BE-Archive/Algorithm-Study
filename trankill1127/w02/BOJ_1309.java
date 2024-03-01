import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class BOJ_1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //100_000
        int[][] cases = new int[n][3];
        cases[0][0] = 1;//왼쪽 칸에 넣음
        cases[0][1] = 1;//오른쪽 칸에 넣음
        cases[0][2] = 1;//어느 칸에도 넣지 않음
        for (int i = 1; i < n; i++) {
            cases[i][0] = (cases[i - 1][1] + cases[i - 1][2]) % 9901;
            cases[i][1] = (cases[i - 1][0] + cases[i - 1][2]) % 9901;
            cases[i][2] = (cases[i - 1][0] + cases[i - 1][1] + cases[i - 1][2]) % 9901;
        }
        int cnt = cases[n - 1][0] + cases[n - 1][1] + cases[n - 1][2];
        System.out.println(cnt % 9901);
    }
}
