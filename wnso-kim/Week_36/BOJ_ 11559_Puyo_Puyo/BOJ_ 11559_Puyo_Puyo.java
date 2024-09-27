import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_ 11559_Puyo_Puyo {

    static int N = 12, M = 6;
    static int[][] filed = new int[N][M];
    static int D = 0, R = 1, G = 2, B = 3, P = 4, Y = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int n=0; n<N; n++){
            char[] input = br.readLine().toCharArray();

            for(int m=0; m<M; m++){
                switch (input[m]){
                    case '.': filed[n][m] = D; break;
                    case 'R': filed[n][m] = R; break;
                    case 'G': filed[n][m] = G; break;
                    case 'B': filed[n][m] = B; break;
                    case 'P': filed[n][m] = P; break;
                    case 'Y': filed[n][m] = Y; break;
                }
            }
        }

        System.out.println(puyoPuyo());
    }

    static int puyoPuyo(){
        int chain = 0;

        while(bomb()){
            setDown();
            chain++;
        }

        return chain;
    }

    static boolean[][][] visit;
    static boolean bomb(){
        boolean isBomb = false;
        visit = new boolean[6][N][M];

        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                int puyo = filed[n][m];

                if(puyo==D || visit[puyo][n][m])
                    continue;

                if(findGroup(puyo, n, m))
                    isBomb = true;

            }
        }

        return isBomb;
    }

    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static boolean findGroup(final int PUYO, final int R, final int C){
        boolean isBomb = false;

        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> bombGroup = new ArrayDeque<>();

        queue.add(new int[]{R,C});
        bombGroup.add(new int[]{R,C});

        visit[PUYO][R][C] = true;

        while(!queue.isEmpty()){
            int[] rc = queue.poll();
            int r = rc[0];
            int c = rc[1];

            for(int i=0; i<4; i++){
                int newR = r + dr[i];
                int newC = c + dc[i];

                if(newR<0 || newC<0 || newR>=N || newC>=M) continue;
                if(visit[PUYO][newR][newC]) continue;
                if(filed[newR][newC] != PUYO) continue;

                visit[PUYO][newR][newC] = true;
                queue.add(new int[]{newR, newC});
                bombGroup.add(new int[]{newR, newC});
            }
        }

        if(bombGroup.size() >= 4){
            isBomb = true;

            while(!bombGroup.isEmpty()){
                int[] rc = bombGroup.poll();
                int r = rc[0];
                int c = rc[1];

                filed[r][c] = D;
            }
        }

        return isBomb;
    }

    static void setDown(){
        int[] ground = {11,11,11,11,11,11};

        for(int n=11; n>=0; n--){
            for(int m=0; m<M; m++){
                if(filed[n][m] == D) continue;

                int tmp = filed[n][m];
                filed[n][m] = D;
                filed[ground[m]][m] = tmp;

                ground[m]--;
            }
        }
    }
}
