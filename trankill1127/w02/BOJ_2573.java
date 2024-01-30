import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

 class BOJ_2573 {

     static class Point {
         int x, y;
         public Point(int x, int y){
             this.x=x;
             this.y=y;
         }
     }

     static int[] dx = {-1,0,1,0};
     static int[] dy = {0,1,0,-1};
     static int[][] ice;
     static int[][] visited;
     static int n;
     static int m;

     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         ice = new int[n][m];
         for (int i=0; i<n; i++) {
             st = new StringTokenizer(br.readLine(), " ");
             for (int j=0; j<m; j++) {
                 ice[i][j]=Integer.parseInt(st.nextToken());
             }
         }

         int meltCnt=0;
         int[][] meltAmount = new int[n][m];

         breakPoint:
         while (true) {
             //녹이기
             for (int i=1; i<n-1; i++) {
                 for (int j=1; j<m-1; j++) {
                     if (ice[i][j]>0) {
                         meltAmount[i][j]=cntOcean(i,j);
                     }
                 }
             }
             for (int i=1; i<n-1; i++) {
                 for (int j=1; j<m-1; j++) {
                     if (ice[i][j]>0) {
                         ice[i][j] = Math.max(ice[i][j]-meltAmount[i][j],0);
                     }
                 }
             }
             meltCnt++;

//             //빙산이 모두 녹았는지 체크
//             int leftCnt=0;
//             for (int[] i: ice) {
//                 for (int j: i) {
//                     if (j>0) leftCnt++;
//                 }
//             }
//             if (leftCnt==0) {
//                 System.out.print("0");
//                 break;
//             }

             //빙산 분리 체크
             visited = new int[n][m];
             for (int i=0; i<n; i++) {
                 for (int j=0; j<m; j++) {
                     visited[i][j]=(ice[i][j]>0)?0:-1;
                 }
             }
             int partCnt=0;
             for (int i=0; i<n; i++) {
                 for (int j=0; j<m; j++) {
                     if (ice[i][j]>0 && visited[i][j]==0) {
                         //System.out.println("분리 검사 지점 "+i+" "+j);
                         partCnt++;
                         checkSeparated(i, j);
                     }
                 }
             }
             if (partCnt>1) {
                 System.out.println(meltCnt);
                 break;
             }
         }
     }

     static void checkSeparated(int x, int y) {
         Queue<Point> q = new LinkedList<>();
         q.add(new Point(x,y));
         visited[x][y]=1;

         while(!q.isEmpty()) {
             Point now = q.poll();

             for (int i=0; i<4; i++) {
                 int nextX = now.x+dx[i];
                 int nextY = now.y+dy[i];

                 if (nextX<0 || nextX>=n || nextY<0 || nextY>=m ) continue;
                 if (visited[nextX][nextY]!=0) continue;

                 q.add(new Point(nextX, nextY));
                 visited[nextX][nextY]=1;
             }
         }
     }

     static int cntOcean(int x, int y) {
         int result=0;
         for (int i=0; i<4; i++) {
             if (ice[x+dx[i]][y+dy[i]]==0) {
                 result++;
             }
         }

         return result;
     }

}
