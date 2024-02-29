package sjhlko.week5.BOJ_1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {
    //https://www.acmicpc.net/problem/1043
    //거짓말
    static int N, M;
    static int[] height;
    static int[] parent;
    static int canNotLie;
    static List<Integer> party = new ArrayList<>();

    static int find(int node) {
        if (parent[node] < 0) return node;
        return parent[node] = find(parent[node]);
    }

    static boolean union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap == bp) return false;
        if (height[bp] > height[ap]) {
            int tmpAp = ap;
            ap = bp;
            bp = tmpAp;
        }
        if (height[bp] == height[ap]) {
            height[ap]++;
        }
        parent[ap] += parent[bp];
        parent[bp] = ap;
        height[bp] = -1;
        return true;
    }

    static void printAns() {
        int count = 0;
        for (int i = 0; i < party.size(); i++) {
            if (find(canNotLie) != find(party.get(i))) count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(height, 0);
        Arrays.fill(parent, -1);
        st = new StringTokenizer(bf.readLine());
        int count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                canNotLie = Integer.parseInt(st.nextToken());
                continue;
            }
            union(canNotLie, Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            count = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            party.add(first);
            for (int j = 1; j < count; j++) {
                union(first, Integer.parseInt(st.nextToken()));
            }
        }
        printAns();
    }
}