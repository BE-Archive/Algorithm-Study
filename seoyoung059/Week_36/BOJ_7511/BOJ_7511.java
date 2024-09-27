package Week_36.BOJ_7511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7511 {

    static class UF {
        int[] parent;

        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if(parent[i] != i){
                parent[i] = find(parent[i]);
                return parent[i];
            } else return i;
        }

        public void union(int i, int j) {
            int root1 = find(i);
            int root2 = find(j);
            if(root1 < root2) parent[root1] = root2;
            else parent[root2] = root1;
        }
    }

    static void solve(BufferedReader br, StringBuilder sb) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        UF uf = new UF(n);

        StringTokenizer st;
        int a, b;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            uf.union(a, b);
        }

        int m = Integer.parseInt(br.readLine());
        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            sb.append((uf.find(u) == uf.find(v)) ? "1\n" : "0\n");
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("Scenario ").append(t).append(":\n");
            solve(br, sb);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
