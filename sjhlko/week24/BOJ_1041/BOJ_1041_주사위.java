package sjhlko.week24.BOJ_1041;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1041_주사위 {
    //https://www.acmicpc.net/problem/1041
    //주사위
    static long N;
    static long[] dice = new long[6];
    static final int[][] two = {{0, 1}, {0, 4}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {1, 5}, {2, 4}, {2, 5}, {3, 5}, {3, 4}, {4, 5}};
    static final int[][] three = {{0, 1, 2}, {0, 1, 3}, {0, 3, 4}, {0, 4, 2}, {1, 2, 5}, {1, 3, 5}, {2, 4, 5}, {3, 4, 5}};

    static long solution() {
        long mOne = Long.MAX_VALUE;
        long mTwo = Long.MAX_VALUE;
        long mThree = Long.MAX_VALUE;
        long sum = 0, max = 0;
        for (int i = 0; i < two.length; i++) {
            mTwo = Math.min(mTwo, dice[two[i][0]] + dice[two[i][1]]);
        }
        for (int i = 0; i < three.length; i++) {
            mThree = Math.min(mThree, dice[three[i][0]] + dice[three[i][1]] + dice[three[i][2]]);
        }
        for (int i = 0; i < 6; i++) {
            mOne = Math.min(mOne, dice[i]);
            sum += dice[i];
            max = Math.max(max, dice[i]);
        }
        if (N == 1) return sum - max;
        return mThree * 4 + mTwo * ((N - 1) * 4 + (N - 2) * 4) + mOne * ((N - 2) * (N - 2) + (N - 1) * (N - 2) * 4);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Long.parseLong(st.nextToken());
        }
        System.out.println(solution());
    }
}
