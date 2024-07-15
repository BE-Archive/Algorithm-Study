package Week_25.BOJ_1600_말이_되고픈_원숭이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이_되고픈_원숭이 {
    static final int INF = Integer.MAX_VALUE;
    static int K,H,W;
    static int[][] MAP;
    static int answer = INF;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        K = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        W = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());

        MAP = new int[H][W];
        for(int i=0; i<H; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                MAP[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        bfs();

        System.out.println(answer == INF? -1: answer);
    }

    static void bfs(){
        // int[] 구조
        // 0: 총 이동 횟수
        // 1: 말 이동 횟수
        // 2: index(r)
        // 3: index(c)
        Queue<int[]> queue = new ArrayDeque<>();
        int[][][] visit = new int[K+1][H][W];  

        for(int k=0; k<=K; k++){
            for(int h=0; h<H; h++){
                Arrays.fill(visit[k][h], INF);
            }
        }

        queue.add(new int[]{0,0,0,0});
        visit[0][0][0] = 0;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int move = now[0];
            int k = now[1];
            int r = now[2];
            int c = now[3];

            if(r == H-1 && c==W-1) {
                answer = Math.min(answer, move);
                continue;
            }

            // 사방 이동
            for(int i=0; i<4; i++){
                int newR = r+dr1[i];
                int newC = c+dc1[i];

                if(newR<0 || newC<0 || newR>=H || newC>=W) continue;
                if(MAP[newR][newC] == 1) continue;
                if(visit[k][newR][newC] <= move+1) continue;
                
                visit[k][newR][newC] = move+1;
                queue.add(new int[]{move+1, k, newR, newC});
            }

            if(k == K) continue;

            // 말 이동
            for(int i=0; i<8; i++){
                int newR = r+dr2[i];
                int newC = c+dc2[i];

                if(newR<0 || newC<0 || newR>=H || newC>=W) continue;
                if(MAP[newR][newC] == 1) continue;
                if(visit[k+1][newR][newC] <= move+1) continue;

                visit[k+1][newR][newC] = move+1;
                queue.add(new int[]{move+1, k+1, newR, newC});
            }
        }
    }

    static int[] dr1 = {0,0,1,-1};
    static int[] dc1 = {1,-1,0,0};

    static int[] dr2 = {-1,-2,-2,-1,1,2,2,1};
    static int[] dc2 = {-2,-1,1,2,2,1,-1,-2};
}