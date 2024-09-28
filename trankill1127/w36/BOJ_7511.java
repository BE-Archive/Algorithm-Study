package trankill1127.w36;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_7511{
    public static int[] parent = null;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int testCnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("");
        for (int t = 1; t <= testCnt; t++) {
            sb.append("Scenario ").append(t).append(":").append("\n");
            int userCnt = Integer.parseInt(br.readLine());

            parent = new int[userCnt];
            for (int i=0; i<userCnt; i++) parent[i]=i;

            int linkCnt = Integer.parseInt(br.readLine());
            int a;
            int b;
            for (int l = 0; l < linkCnt; l++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                union(a,b);
            }
            int innerTestCnt = Integer.parseInt(br.readLine());
            for (int it = 0; it < innerTestCnt; it++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                sb.append(find(a)==find(b) ? 1 : 0).append("\n");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa==pb) return;

        if (pa<pb) parent[pb]=a;
        else parent[pa]=pb;
    }
    public static int find(int a) {
        if (parent[a]!=a) parent[a]=find(parent[a]);
        return parent[a];
    }

}