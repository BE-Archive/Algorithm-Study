package trankill1127.w37;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2533 {

    static ArrayList<Integer>[] tree;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        int[] result = dfs(1, 0);
        System.out.println(Math.min(result[0], result[1]));
    }

    static int[] dfs(int node, int parent) { //현재 노드, 이 노드의 부모 노드
        int early = 1; //얼리 어답터인 경우
        int notEarly = 0; //얼리 어답터가 아닌 경우

        for (int child : tree[node]) {
            if (child != parent) { //부모로는 다시 탐색하지 않도록 방지
                int[] childResult = dfs(child, node);
                //얼리 어답터인 경우에는 자식이 무엇이던 상관 없음
                early += Math.min(childResult[0], childResult[1]);
                //얼리 어답터가 아닌 경우에는 자식이 얼리 어답터여야 함
                notEarly += childResult[0];
            }
        }

        return new int[]{early, notEarly};
    }

}