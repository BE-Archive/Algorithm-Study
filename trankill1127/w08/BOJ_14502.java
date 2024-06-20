import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {

    /*
    빈칸: 0
    벽: 1
    바이러스: 2

    바이러스는 상하좌우로 퍼져나간다.
    벽 3개를 세워서 안전지대의 넓이 최대값을 구하자.

    걍 무지성 대각선 ㄱㄱ??
    1 있는 데 아무나 찾아서 대각선 ㄱㄱ 하던가
    2칸 대각선.. 3칸 대각선으로 막을 수 있는 지점에서 ㄱㄱ 하던가

    아니 벽세우는 게 dfs라고요???
    이게 어케 dfs임~~~ 걍 아무데나 세우는 거잖슴~~~
    걍 벽 차원에서 dfs라 보는 거냐 설마????
    ㅈ진심 이해 안감.... 어이없어
     */

    public static class Point {
        int x, y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return x+" "+y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][m];

        virus = new ArrayList<>();

        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++){
                lab[i][j]=Integer.parseInt(st.nextToken());
                if (lab[i][j]==2) virus.add(new Point(i,j));
            }
        }


    }

    static int n;
    static int m;
    public static int[][] lab;
    public static List<Point> virus;
    public static int[][] direction = {
            {-1,0}, {1,0}, {0,-1}, {0,1}
    };
    public static int maxCnt=0;

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        for (int i=0; i<virus.size(); i++){
            q.add(virus.get(i));
        }

        //lab 복사해서 해줘야겟다....

        while(!q.isEmpty()){
            Point now = q.poll();

            for (int i=0; i<4; i++){
                int nextX = now.x+direction[i][0];
                int nextY = now.y+direction[i][1];

                if ( nextX<0 || nextX>=n || nextY<0 || nextY>=m ) continue;
                if (lab[nextX][nextY]!=0) continue;

                lab[nextX][nextY]=2;
                q.add(new Point(nextX, nextY));
            }

        }

        int cnt=0;
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (lab[i][j]==0) cnt++;
            }
        }

        if (cnt>maxCnt) maxCnt=cnt;

    }

}
