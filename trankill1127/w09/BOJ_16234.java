import java.io.*;
import java.util.*;

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

        int daysCnt=0;
        while (true) { //하루 단위
            visited=new int[n][n];
        	boolean flag = false;
            
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    if (visited[i][j]==1) continue;
                    if (bfs(i,j)) {
                    	flag=true;
                    }
                }
            }
            if (flag) daysCnt++;
            else break;
        }

        System.out.println(daysCnt);
    }

    public static boolean bfs(int x, int y){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        visited[x][y]=1;
        int countryPpl=map[x][y];

        List<Pair> listForCalc = new LinkedList<>();
        listForCalc.add(new Pair(x,y));

        while (!q.isEmpty()){
            Pair now=q.poll();

            for (int i=0; i<4; i++){
                int nextX = now.x+direction[i][0];
                int nextY = now.y+direction[i][1];
                if (nextX<0 || nextX>=n || nextY<0 || nextY>=n || visited[nextX][nextY]==1) continue;

                int diff = Math.abs(map[now.x][now.y]-map[nextX][nextY]);
                if (l<=diff && diff<=r){
                    visited[nextX][nextY]=1;
                    countryPpl+=map[nextX][nextY];
                    q.add(new Pair(nextX, nextY));
                    listForCalc.add(new Pair(nextX,nextY));
                }
            }
        }

        for (Pair p : listForCalc){
            map[p.x][p.y]=countryPpl/listForCalc.size();
        }

        if (listForCalc.size()>1) return true;
        else return false;
    }
}

