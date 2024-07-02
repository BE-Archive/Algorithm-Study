import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Map<String, Integer> map = new HashMap<>();
            int idx = 1;
            int F = Integer.parseInt(br.readLine());
            UnionFind uf = new UnionFind(2 * F);

            for (int j = 0; j < F; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                int idx1 = 0, idx2 = 0;

                if (!map.containsKey(name1)) {
                    map.put(name1, idx);
                    idx1 = idx++;
                } else {
                    idx1 = map.get(name1);
                }
                if (!map.containsKey(name2)) {
                    map.put(name2, idx);
                    idx2 = idx++;
                } else {
                    idx2 = map.get(name2);
                }
                int res = uf.union(idx1, idx2);
                sb.append(res).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class UnionFind{
    int[] parents;
    int[] rank;

    public UnionFind(int size) {
        this.parents = new int[size + 1];
        this.rank = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int v){
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    public int union(int v, int u) {
        int rep1 = find(v);
        int rep2 = find(u);

        if(rep1 == rep2) return Math.max(rank[rep1], rank[rep2]);

        //더 작은 쪽에 붙히는 게 이득
        if (rank[rep1] < rank[rep2]) {
            rank[rep1] += rank[rep2];
            rank[rep2] = 0;
            parents[rep2] = rep1;
            return rank[rep1];
        } else {
            rank[rep2] += rank[rep1];
            rank[rep1] = 0;
            parents[rep1] = rep2;
            return rank[rep2];
        }
    }
}