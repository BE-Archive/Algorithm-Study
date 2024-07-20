package sjhlko.week26.BOJ_15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class NAndM {
    //https://www.acmicpc.net/problem/15654
    //N과M5
    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder ans = new StringBuilder();

    static void solution(int count, int[] arr, boolean[] visited) {
        if (count == M) {
            for (int i = 0; i < count; i++) {
                ans.append(arr[i]).append(" ");
            }
            ans.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            arr[count] = list.get(i);
            visited[i] = true;
            solution(count + 1, arr, visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N ; i++) {
            list.add(Integer.valueOf(st.nextToken()));
        }
        Collections.sort(list);
        solution(0, new int[N], new boolean[N]);
        System.out.println(ans);
    }
}
