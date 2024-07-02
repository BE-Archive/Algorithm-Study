import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] indegree = new int[n+1];
        int[][] graph = new int[n+1][n+1];
        int x, y, k;
        int unit;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            graph[y][x] = k;
            indegree[x]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if(indegree[i]==0) q.offer(i);
        }
        unit = q.size();

        int[] order = new int[n];
        int idx = 0, curr;
        while(!q.isEmpty()) {
            curr = q.pollFirst();
            order[idx++] = curr;
            for (int i = 1; i <= n; i++) {
                if(graph[curr][i]>0) {
                    indegree[i]--;
                    if(indegree[i]==0) q.offerLast(i);
                }
            }
        }

        int[] cnt = new int[n+1];
        cnt[order[n-1]] = 1;
        for (int i = n-1; i >= unit; i--) {
            for (int j = 1; j <= n ; j++) {
                cnt[j]+=graph[j][order[i]] * cnt[order[i]];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < unit; i++) {
            sb.append(order[i]).append(" ").append(cnt[order[i]]).append("\n");
        }
        System.out.println(sb);
    }
}
