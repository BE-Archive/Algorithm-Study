import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4193 {

    public static StringTokenizer st;
    public static int n;
    public static int[] start;
    public static int[] end;
    public static int[][] ocean;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++){
            n = Integer.parseInt(br.readLine());
            ocean = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    ocean[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine().trim());
            start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            st = new StringTokenizer(br.readLine().trim());
            end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            visited = new int[n][n];
            sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static Queue<int[]> q = new LinkedList<>();
    public static int[][] direction = {
            {-1,0}, {1,0}, {0,-1}, {0,1}
    };
    public static int[][] visited;
    public static int bfs(){
        q.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]]=1;

        int minTime=Integer.MAX_VALUE;
        while (!q.isEmpty()){
            int[] now = q.poll();

            if (now[0]==end[0]&&now[1]==end[1]){  //*
                minTime = Math.min(minTime, now[2]);
                continue;
            }

            for (int i=0; i<4; i++){
                int nextX= now[0]+direction[i][0];
                int nextY= now[1]+direction[i][1];

                if (nextX<0||nextX>=n||nextY<0||nextY>=n||ocean[nextX][nextY]==1||visited[nextX][nextY]==1) continue;

                if (ocean[nextX][nextY]==2 && now[2]%3!=2) {
                    q.add(new int[]{now[0],now[1],now[2]+1});
                    continue;
                }

                q.add(new int[]{nextX,nextY,now[2]+1});
                visited[nextX][nextY]=1;

            }
        }

        if (minTime==Integer.MAX_VALUE) return -1;
        else return minTime;
    }

}
