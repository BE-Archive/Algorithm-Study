package seoyoung059.Week_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int m, n;

    static int[][] box;
    static Queue<GoodTomato> q = new LinkedList<GoodTomato>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int max = 0;

    public static class GoodTomato {
        int y;
        int x;

        public GoodTomato(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static boolean isValid(int y, int x) {
        return ((0<=y)&&(y<n)&&(0<=x)&&(x<m));
    }
    static void solution(){
        GoodTomato curr;
        int ny, nx;
        while(!q.isEmpty()) {
            curr = q.poll();
//            System.out.println(curr.y+" "+curr.x);
            for (int i = 0; i < 4; i++) {
                ny = curr.y+dy[i];
                nx = curr.x+dx[i];
                if(isValid(ny, nx)&&box[ny][nx]==0){
                    box[ny][nx]=box[curr.y][curr.x]+1;
                    max = Math.max(max, box[ny][nx]-1);
                    q.offer(new GoodTomato(ny,nx));
                }
            }
        }
    }

    static boolean isGoodBox(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(box[i][j]==0)
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        box = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j]==1) q.offer(new GoodTomato(i,j));
            }
        }

        solution();
        if(isGoodBox())
            System.out.println(max);
        else
            System.out.println(-1);

    }
}
