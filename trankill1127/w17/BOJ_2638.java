import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2638 {

    public static int N, M;
    public static int[][] board;
    public static int[][] direction = {
            {0,1}, {1,0}, {0,-1}, {-1,0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<int[]> cheeseAlive = new LinkedList<>();

        board = new int[N][M];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0; j<M; j++){
                board[i][j]=Integer.parseInt(st.nextToken());
                if (board[i][j]==1){
                    cheeseAlive.add( new int[]{i,j} );
                }
            }
        }

        airFlow(new int[]{0,0});

        Queue<int[]> cheeseFreshDead = new LinkedList<>();
        int date=0;
        int todayCheeseCnt=0;

        while (!cheeseAlive.isEmpty()){
            date++;
            todayCheeseCnt=cheeseAlive.size();

            while (todayCheeseCnt>0){
                int[] target = cheeseAlive.poll();
                int airCnt=0;

                for (int i=0; i<4; i++) {
                    int x=target[0]+direction[i][0];
                    int y=target[1]+direction[i][1];
                    if (x<0||x>=N||y<0||y>=M) continue;
                    if (board[x][y]==-1) airCnt++;
                }

                if (airCnt>=2) cheeseFreshDead.add(target);
                else  cheeseAlive.add(target);

                todayCheeseCnt--;
            }

            while (!cheeseFreshDead.isEmpty()){
                int [] target = cheeseFreshDead.poll();
                board[target[0]][target[1]]=0;
                airFlow(target);
            }
        }

        System.out.println(date);
    }

    public static void airFlow(int[] target){
        Queue<int[]> q = new LinkedList<>();
        board[target[0]][target[1]]=-1;
        q.add(target);

        while (!q.isEmpty()){
            int[] now = q.poll();

            for (int i=0; i<4; i++){
                int nextX = now[0]+direction[i][0];
                int nextY = now[1]+direction[i][1];

                if (nextX<0||nextX==N||nextY<0||nextY==M) continue;
                if (board[nextX][nextY]==1) continue;

                if (board[nextX][nextY]==0) {
                    board[nextX][nextY]=-1;
                    q.add(new int[]{nextX, nextY});
                }
            }
        }
    }
}
