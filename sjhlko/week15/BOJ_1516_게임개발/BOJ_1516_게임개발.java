package sjhlko.week15.BOJ_1516_게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1516_게임개발 {
    //https://www.acmicpc.net/problem/1516
    //게임 개발
    static int N;
    static int[] before, info;
    static List<List<Integer>> next = new ArrayList<>();
    static Queue<int[]> done = new ArrayDeque<>();
    static int[] ans;

    static void solution() {
        for (int i = 0; i < N; i++) {
            if (before[i] == 0) {
                done.add(new int[]{i, info[i]});
                ans[i] = info[i];
            }
        }
        while (!done.isEmpty()) {
            int[] now = done.poll();
            for (int i : next.get(now[0])) {
                ans[i] = Math.max(ans[i], now[1]);
                if (--before[i] == 0) {
                    done.add(new int[]{i, info[i] + ans[i]});
                    ans[i] += info[i];
                }
            }
        }
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        before = new int[N];
        info = new int[N];
        ans = new int[N];
        for (int i = 0; i < N; i++) {
            next.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            info[i] = time;
            while (true) {
                int nextNode = Integer.parseInt(st.nextToken());
                if (nextNode == -1) break;
                next.get(nextNode - 1).add(i);
                before[i]++;
            }
        }
        solution();
        printAns();
    }
}