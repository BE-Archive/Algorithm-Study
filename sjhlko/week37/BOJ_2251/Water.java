package sjhlko.week37.BOJ_2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Water {
    //https://www.acmicpc.net/problem/2251
    //물통
    static boolean[][][] info;
    static int[] size;
    static int[][] move = {{0, 1}, {1, 0}, {1, 2}, {2, 1}, {0, 2}, {2, 0}};
    static List<Integer> ans = new ArrayList<>();

    static void solution(int[] now) {
        for (int i = 0; i < 6; i++) {
            int water = Math.min(now[move[i][0]], size[move[i][1]] - now[move[i][1]]);
            int[] tmp = now.clone();
            tmp[move[i][0]] -= water;
            tmp[move[i][1]] += water;
            if (info[tmp[0]][tmp[1]][tmp[2]]) continue;
            info[tmp[0]][tmp[1]][tmp[2]] = true;
            if (tmp[0] == 0) ans.add(tmp[2]);
            solution(tmp);
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(ans);
        for (Integer i : ans) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        size = new int[]{a, b, c};
        info = new boolean[a + 1][b + 1][c + 1];
        solution(new int[]{0, 0, c});
        print();
    }
}


