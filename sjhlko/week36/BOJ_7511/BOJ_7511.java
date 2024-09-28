package sjhlko.week36.BOJ_7511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7511 {
    static final String ANS_PREFIX = "Scenario ";
    static int N;
    static int[] parent, height;

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        if (height[px] < height[py]) {
            int tmp = py;
            py = px;
            px = tmp;
        }
        parent[py] = px;
        parent[px]--;
        if (height[px] == py) height[px]++;
        height[py] = 0;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int cnt = 1;
        StringBuilder ans = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            parent = new int[N];
            Arrays.fill(parent, -1);
            height = new int[N];
            int k = Integer.parseInt(bf.readLine());
            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            int m = Integer.parseInt(bf.readLine());
            ans.append(ANS_PREFIX).append(cnt).append(":").append("\n");
            while (m-- > 0) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(find(a) == find(b))
                    ans.append(1).append("\n");
                else ans.append(0).append("\n");
            }
            ans.append("\n");
            cnt++;
        }
        System.out.println(ans);
    }
}
