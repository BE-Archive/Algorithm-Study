import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_22255_호석사우르스 {

    static int N,M;
    static int answer = -1;
    static int[][] MAP;
    static int[][] se = new int[2][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());        

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<2; i++){
            se[i][0] = Integer.parseInt(stk.nextToken());        
            se[i][1] = Integer.parseInt(stk.nextToken());        
        }

        MAP = new int[N+2][M+2];
        for(int i=0; i<=N+1; i++){Arrays.fill(MAP[i], -1);}

        for(int i=1; i<=N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                MAP[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        dijkstra();

        System.out.println(answer);
    }

    static void dijkstra(){
        Queue<int[]> queue = new PriorityQueue<>((q1, q2) -> (q1[2] - q2[2]));
        int[][][] cost = new int[3][N+2][M+2];
        for(int i=0; i<3; i++){
            for(int j=0; j<=N+1; j++){
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        queue.add(new int[]{se[0][0], se[0][1], 0, 1});

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            int step = now[3]%3;
            int len = dr[step].length;
            
            for(int i=0; i<len; i++){
                int r = now[0] + dr[step][i];
                int c = now[1] + dc[step][i];
                int weight = now[2]+MAP[r][c];

                if(r == se[1][0] && c == se[1][1]) {
                    answer = weight;
                    return;
                }

                if(MAP[r][c] == -1) continue;

                if(cost[step][r][c] > weight){
                    cost[step][r][c] = weight;
                    queue.add(new int[]{r, c, weight, now[3]+1});
                }

            }
        }
    }

    static int[][] dr = {{0,0,1,-1}, {-1,1}, {0,0}};
    static int[][] dc = {{1,-1,0,0}, {0,0}, {-1,1}};
}