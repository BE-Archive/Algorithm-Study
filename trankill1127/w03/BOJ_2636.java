import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class BOJ_2636 {

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    static int[][] cheese;
    static boolean[][] visited;
    static int n;
    static int m;
    static int cheeseCnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int[n][m];
        cheeseCnt=0;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<m; j++) {
                cheese[i][j]=Integer.parseInt(st.nextToken());
                if (cheese[i][j]==1) cheeseCnt++;
            }
        }

        int time=0;
        int cntPerTime=0;

        while (cheeseCnt!=0) { //치즈가 남으면 이 작업들을 반복한다.
            time++; //이번 차례가 몇번째 녹이기인지 저장
            cntPerTime = cheeseCnt; //녹이기 전의 개수 저장
            meltingCheese(); //녹이기
        }
        System.out.println(time);
        System.out.println(cntPerTime);
    }

    public static void meltingCheese(){
        int[] dx={-1,0,1,0};
        int[] dy={0,-1,0,1};
        Queue<Point> queue = new LinkedList<>();
        visited=new boolean[n][m]; //매번 0,0부터 검사를 시작하니까 매번 방문 배열도 새로 만들어줘야 한다...?

        queue.offer(new Point(0,0));
        visited[0][0]=true;

        while (!queue.isEmpty()){
            Point now = queue.poll();

            for (int i=0; i<4; i++){
                int nextX = now.x+dx[i];
                int nextY = now.y+dy[i];

                if (nextX<0 || nextX>=n || nextY<0 || nextY>=m || visited[nextX][nextY]) continue;

                if (cheese[nextX][nextY]==1){ //치즈가 남아있는 경우
                    cheese[nextX][nextY]=0; //치즈를 녹이고
                    cheeseCnt--; //남아있는 치즈의 개수를 줄인다.
                }
                else if (cheese[nextX][nextY]==0){ //치즈가 남아있지 않은 경우
                    queue.offer(new Point(nextX, nextY)); //큐에 추가
                }

                visited[nextX][nextY]=true; //방문 체크
            }
        }
    }
}



