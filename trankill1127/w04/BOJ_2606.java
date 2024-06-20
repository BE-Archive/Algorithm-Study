import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2606 {

    static int n;
    static boolean[][] board;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new boolean[n+1][n+1];

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[s][e]=true;
            board[e][s]=true;
        }

        visited = new boolean[n+1];
        BFS(1);

        int cnt=0;
        for (boolean result: visited) if (result) cnt++;
        System.out.println(cnt-1);
    }

    public static void BFS(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start]=true;

        while (!q.isEmpty()){
            int now = q.poll();
            for (int next=1; next<=n; next++){
                if (board[now][next] && !visited[next]){
                    q.add(next);
                    visited[next]=true;
                }
            }
        }
    }

}
