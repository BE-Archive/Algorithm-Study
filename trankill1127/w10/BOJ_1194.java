import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194 {

    public static class Point {
        int x,y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new char[n][m];
        Point start = null;
        for (int i=0; i<n; i++){
            maze[i]=br.readLine().toCharArray();
            for (int j=0; j<m; j++){
                if (maze[i][j]=='0') start = new Point(i,j);
            }
        }

        bfs(start);


    }

    public static int n;
    public static int m;
    public static char[][] maze;
    public static int[][] direction = {
            {-1,0}, {1,0}, {0,-1}, {0,1}
    };
    public static int keyStatus=0;
    public static int time=0;

    public static void bfs(Point start){
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);

        outer:
        while (!q.isEmpty()){
            Point now = q.poll();

            for (int i=0; i<4; i++){
                int nextX = now.x+direction[i][0];
                int nextY = now.y+direction[i][1];

                if (nextX<0 || nextX>=n || nextY<0 || nextY>=m) continue;

                if (maze[nextX][nextY]=='#') continue;

                if (maze[nextX][nextY]=='1') {

                }
                if (maze[nextX][nextY]=='1') {

                    break outer;
                }

            }
        }
    }

}
