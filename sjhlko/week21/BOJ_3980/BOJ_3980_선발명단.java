package sjhlko.week21.BOJ_3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980_선발명단 {
    //https://www.acmicpc.net/problem/3980
    //선발 명단
    static int ans;
    static int[][] info;

    static void solution(int count, int sum, boolean[] visited) {
        if (count == 11) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (visited[i]) continue;
            if (info[i][count] == 0) continue;
            visited[i] = true;
            solution(count + 1, sum + info[i][count], visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            ans = 0;
            info = new int[11][11];
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 11; j++) {
                    info[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solution(0,0,new boolean[11]);
            System.out.println(ans);
        }
    }
}
