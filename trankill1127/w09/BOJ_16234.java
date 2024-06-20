import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {

    public static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static int n, l, r;
    public static int[][] map;
    public static int[][] visited;
    public static int[][] direction = {
            {-1,0},{1,0},{0,-1},{0,1}
    };
    public static int daysCnt=0;
    public static int trialTotCnt=0;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n= Integer.parseInt(st.nextToken());
        l= Integer.parseInt(st.nextToken());
        r= Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<n; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited=new int[n][n];
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    for (int k=0; k<4; k++){
                        int nextX = i+direction[k][0];
                        int nextY = j+direction[k][1];
                        if (nextX<0 || nextX>=n || nextY<0 || nextY>=n || visited[nextX][nextY]==1) continue;

                        int diff = Math.abs(map[i][j]-map[nextX][nextY]);
                        if (l<=diff && diff<=r){
                            bfs(i,j);
                        }
                    }
                }
            }

            int changedCnt=0;
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    if (visited[i][j]==1) changedCnt++;
                }
            }

            if (changedCnt==0) break;
            daysCnt++;
        }

        System.out.println(daysCnt);

    }

    public static int countryCnt;
    public static int countryPpl;

    public static void bfs(int x, int y){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        visited[x][y]=1;
        countryCnt=1;
        countryPpl=map[x][y];

        List<Pair> listForCalc = new LinkedList<>();
        listForCalc.add(new Pair(x,y));

        while (!q.isEmpty()){
            Pair now=q.poll();

            System.out.println(now.toString());

            for (int i=0; i<4; i++){
                int nextX = now.x+direction[i][0];
                int nextY = now.y+direction[i][1];
                if (nextX<0 || nextX>=n || nextY<0 || nextY>=n || visited[nextX][nextY]==1) continue;

                int diff = Math.abs(map[now.x][now.y]-map[nextX][nextY]);
                if (l<=diff && diff<=r){
                    visited[nextX][nextY]=1;
                    q.add(new Pair(nextX, nextY));
                    listForCalc.add(new Pair(nextX,nextY));
                    countryCnt++;
                    countryPpl+=map[nextX][nextY];
                }
            }
        }

        for (Pair p : listForCalc){
            map[p.x][p.y]=countryPpl/countryCnt;
        }

        for (int i2=0; i2<n; i2++){
            for (int j2=0; j2<n; j2++){
                System.out.print(map[i2][j2]+" ");
            }
            System.out.println();
        }


    }

}

/*

11:11-

n*n
각 칸마다 인구수

[국경 오픈 세션]
인접한 두나라 L<=인구 차<=R 면, 국경 오픈
: bfs로 4방을 확인하고 조건을 만족하면 큐에 추가
: visited로 방문한 놈들 체크

[인구 이동 세션]
연결된 애들의 인구수 = 인구수 합 / 칸 개수 (소수점은 버려잉)
: visited를 써서 인구수의 합, 칸 개수를 구한다.
: 구한 값을 이용해서 이 칸의 인구수를 수정한다.

[연합 해제 세션]
: visited한 칸이 0개보다 클 때,
    visited 초기화 + 인구이동 일수++

: visited한 칸이 0개 일 때, 인구이동 일수 출력

 */