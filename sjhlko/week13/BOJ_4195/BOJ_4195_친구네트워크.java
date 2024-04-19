package sjhlko.week13.BOJ_4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195_친구네트워크 {
    static int N;
    static int[] height, parent;
    static Map<String, Integer> index;

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        if (height[px] < height[py]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py]) height[px]++;
        height[py] = 0;
        parent[px] += parent[py];
        parent[py] = px;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            int M = N;
            index = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            int count = 0;
            height = new int[2 * N + 1];
            parent = new int[2 * N + 1];
            Arrays.fill(parent, -1);
            while (M-- > 0) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String first = st.nextToken();
                String second = st.nextToken();
                index.putIfAbsent(first, count++);
                index.putIfAbsent(second, count++);
                int fi = index.get(first);
                int si = index.get(second);
                union(index.get(first), index.get(second));
                sb.append(parent[find(fi)] < 0 ? -parent[find(fi)] : -parent[find(si)]).append("\n");
            }
            Arrays.fill(parent, -1);
            System.out.print(sb);
        }
    }
}