package sjhlko.week11.BOJ_1423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1423_원숭이키우기 {
    //https://www.acmicpc.net/problem/1423
    //원숭이 키우기
    static int N, D;
    static int[] power, count;
    static List<Info> infos = new ArrayList<>();
    static long[][][] dp;

    static class Info implements Comparable<Info> {
        int start, end;
        double value;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
            value = (double) (power[end] - power[start]) / (double) (end - start);
        }

        @Override
        public int compareTo(Info o) {
            if (o.value == value) {
                return (end - start) - (o.end - o.start);
            }
            return o.value > value ? 1 : -1;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "start=" + start +
                    ", end=" + end +
                    ", value=" + value +
                    '}';
        }
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                infos.add(new Info(i, j));
            }
        }
        Collections.sort(infos);
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                Arrays.fill(dp[i][j], -1);
//            }
//        }
    }

    static void solution() {
        while (D > 0) {
            boolean isValid = false;
            for (int i = 0; i < infos.size(); i++) {
                Info now = infos.get(i);
                if (count[now.start] == 0) continue;
                if (now.end - now.start > D) continue;
                if (now.value <= 0) return;
                D -= (now.end - now.start);
                count[now.start]--;
                count[now.end]++;
                isValid = true;
                break;
            }
            if (!isValid) return;
        }
    }

//    static long solution2(int i, int before, int left) {
//        if (dp[i][before][left] != -1) return dp[i][before][left];
//        dp[i][before][left] =
//    }

    static long calcAns() {
        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += (long) count[i] * (long) power[i];
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st1 = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());
        power = new int[N];
        count = new int[N];
        for (int i = 0; i < N; i++) {
            count[i] = Integer.parseInt(st1.nextToken());
            power[i] = Integer.parseInt(st2.nextToken());
        }
        D = Integer.parseInt(bf.readLine());
        dp = new long[N][D + 1][D + 1];
        init();
        solution();
        System.out.println(calcAns());
    }
}
