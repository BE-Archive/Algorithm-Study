import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14271_그리드_게임 {

    static int N,M,K;
    static int answer = 0;
    static boolean[][] visits = new boolean[3050][3050];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        for(int n=0; n<N; n++){
            char[] grid = br.readLine().toCharArray();
            for(int m=0; m<M; m++){
                if(grid[m] == 'o'){
                    visits[n+1500][m+1500] = true;
                }
            }
        }

        K = Integer.parseInt(br.readLine());

        bfs();
        System.out.println(answer);
    }

    static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();

        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(visits[n+1500][m+1500]){
                     queue.add(new int[]{n+1500, m+1500});
                }
            }
        }

        while(!queue.isEmpty() && K>=0){
            int size = queue.size();
            answer += size;

            while(size > 0){
                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];

                for(int i=0; i<4; i++) {
                    int newR = r + dr[i];
                    int newC = c + dc[i];

                    if(newR<0 || newC<0 || newR>=3050 || newC>=3050) continue;
                    if (visits[newR][newC]) continue;

                    queue.add(new int[]{newR, newC});
                    visits[newR][newC] = true;
                }

                size--;
            }

            K--;
        }
    }

    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};
}