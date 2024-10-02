package sjhlko.week37.BOJ_15989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeeHi {
    //https://www.acmicpc.net/problem/15989
    //1,2,3 더하기 4
    static int N;

    static int solution() {
        int[] dp = new int[N];
        // 이전것 + 1,2,3 해서 N을 만들 수 있는 경우의 수
        return dp[N];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            System.out.println(solution());
        }

    }

}
