import java.io.*;
import java.util.*;

public class BOJ_1600 {
    static int k;
    static int w;
    static int h;
    static int[][] land;
    static int[][] dir = {
            {-1,0}, {0,1}, {1,0}, {0,-1}
    };
    static int[][] horseDir = {
            {-2,1}, {-1,2}, {1,2}, {2,1},
            {2,-1}, {1,-2}, {-1,-2}, {-2,-1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        land = new int[h][w];
        for (int i=0; i<h; i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0; j<w; j++){
                land[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        if (h==1 && w==1) System.out.print(0); //*
        else System.out.print(solution());
    }

    public static int solution(){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[h][w][k+1];
        q.offer(new int[]{0,0,k}); //세로, 가로, 잔여 특별 이동 횟수
        visited[0][0][k]=true;

        int answer=0;
        while (!q.isEmpty()){

            int levelCandidate=q.size();
            answer++;

            while (levelCandidate>0) {
                int[] now = q.poll();

                if (now[2]>0){
                    for (int i=0; i<8; i++){
                        int nx=now[0]+horseDir[i][0];
                        int ny=now[1]+horseDir[i][1];
                        int nk=now[2]-1;

                        if ( !isValid(nx,ny) || land[nx][ny]==1 || visited[nx][ny][nk] ) continue;
                        if ( nx==h-1 && ny==w-1 ) return answer;

                        q.offer(new int[]{nx,ny,nk});
                        visited[nx][ny][nk]=true;
                    }
                }

                for (int i=0; i<4; i++){
                    int nx=now[0]+dir[i][0]; //*
                    int ny=now[1]+dir[i][1]; //*
                    int nk=now[2]; //*

                    if ( !isValid(nx,ny) || land[nx][ny]==1 || visited[nx][ny][nk] ) continue;
                    if ( nx==h-1 && ny==w-1 ) return answer;

                    q.offer(new int[]{nx,ny,nk});
                    visited[nx][ny][nk]=true;
                }

                levelCandidate--;
            }
        }

        return -1;
    }

    public static boolean isValid(int x, int y){
        if (x<0||x>=h||y<0||y>=w) return false;
        return true;
    }

}