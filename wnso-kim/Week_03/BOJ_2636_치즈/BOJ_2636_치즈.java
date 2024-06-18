import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C,result=0,remain=0;
    static class Chiz{
        int r,c;
        boolean isCheese;
        Chiz(int r, int c, boolean isCheese){
            this.r = r;
            this.c = c;
            this.isCheese = isCheese;
        }
    }
    static Chiz[][] chizs;
    static boolean[][] visit;
    static Queue<Chiz> queue = new LinkedList<>();
    static boolean isValid(int r, int c){
        return (0<=r)&&(r<R)&&(0<=c)&&(c<C);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());

        chizs = new Chiz[R][C];
        visit = new boolean[R][C];
        for(int i=0; i<R; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                chizs[i][j] = new Chiz(i, j, Integer.parseInt(stk.nextToken())==1? true: false);
                if(chizs[i][j].isCheese){
                    remain++;
                }
            }
        }

        addAir();
        twoThousandYearsLater();
        System.out.println(result + " " + remain);
    }

    // 맵의 가장자리를 공기 영역으로 추가
    static void addAir(){
        for(int i=0; i<R; i++){
            queue.add(chizs[i][0]);
            queue.add(chizs[i][C-1]);
            visit[i][0] = visit[i][C-1] = true;
        }
        for(int i=1; i<C-1; i++){
            queue.add(chizs[0][i]);
            queue.add(chizs[R-1][i]);
            visit[0][i] = visit[R-1][i] = true;
        }
    }

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static void twoThousandYearsLater(){
        int remove = 0;

        // 치즈가 없을 때 까지 반복
        while(!queue.isEmpty()){
            // 치즈를 일괄 삭제하기 위한 큐
            Queue<Chiz> newQueue = new LinkedList<>();
            
            // 공기를 기준으로 인접한 치즈를 새로운 큐에 집어넣음
            while(!queue.isEmpty()){
                Chiz chiz = queue.poll();
                int r = chiz.r;
                int c = chiz.c;

                for(int i=0; i<4; i++){
                    int newR = r+dr[i];
                    int newC = c+dc[i];
                    if(!isValid(newR, newC)) continue;

                    if(!chizs[newR][newC].isCheese && !visit[newR][newC]){
                        queue.add(chizs[newR][newC]);
                        visit[newR][newC] = true;
                    }
                    else if(chizs[newR][newC].isCheese && !visit[newR][newC]){
                        newQueue.add(chizs[newR][newC]);
                        visit[newR][newC] = true;
                    }
                }
            }

            // 삭제할 치즈가 없으면 종료
            if(newQueue.isEmpty())
                return;

            // 삭제할 치즈가 있으므로 후보정
            result++;
            remain -= remove;
            remove = newQueue.size();

            // 치즈 삭제 및 추가 공기 주입
            while(!newQueue.isEmpty()){
                Chiz chiz = newQueue.poll();
                chiz.isCheese = false; // 치즈 삭제
                queue.add(chiz);
                int r = chiz.r;
                int c = chiz.c;

                for(int i=0; i<4; i++){
                    int newR = r+dr[i];
                    int newC = c+dc[i];
                    if(!isValid(newR, newC)) continue;
                    
                    if(!chizs[newR][newC].isCheese && !visit[newR][newC]){
                        queue.add(chizs[newR][newC]);
                        visit[newR][newC] = true;
                    }
                }
            }
        }
    }
}
