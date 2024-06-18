import java.io.*;
import java.util.*;

public class BOJ_14226_이모티콘 {

    final static int MAX_SIZE = 2002;

    static int N, answer;
    static boolean visited[][];

    static void solve()
    {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0}); // 초기 상태 (cnt, copy)

        int[] e;
        int depth = 0;
        while(!q.isEmpty()) {

            int qSize = q.size();
            while(qSize-- > 0) {

                e = q.poll();

                int cnt = e[0];
                int copy = e[1];

                if(cnt == N) {
                    answer = Math.min(depth, answer);
                    return;
                }

                if(cnt < MAX_SIZE && cnt > 0 && cnt != copy && !visited[cnt][cnt] ) {
                    visited[cnt][cnt] = true;
                    q.offer(new int[]{cnt, cnt});
                }
                if(cnt+copy < MAX_SIZE && copy != 0 && !visited[cnt+copy][copy]) {
                    visited[cnt+copy][copy] = true;
                    q.offer(new int[]{cnt + copy, copy});
                }
                if(cnt>0 && !visited[cnt-1][copy]) {
                    visited[cnt-1][copy] = true;
                    q.offer(new int[]{cnt - 1, copy});
                }
            }

            if(++depth == N) return;
            
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[MAX_SIZE][MAX_SIZE];
        answer = N;

        solve();

        System.out.print(answer);
    }
}
