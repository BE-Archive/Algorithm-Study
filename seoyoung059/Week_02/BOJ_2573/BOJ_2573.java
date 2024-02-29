package seoyoung059.Week_02.BOJ_2573;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] arr;
    static int n, m;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static boolean isValid(int y, int x){
        return (0<=x)&&(x<m)&&(0<=y)&&(y<n);
    }

    static void printArr(){
        for(int[] a: arr){
            for(int q: a){
                System.out.printf("%d ",q);
            }
            System.out.println();
        }
    }
    static void dfs(int y, int x, boolean[][] isVisited) {
        int ny, nx;
        for (int i = 0; i < 4; i++) {
            ny = y+dy[i];
            nx = x+dx[i];
            if(isValid(ny, nx) && !isVisited[ny][nx] && arr[ny][nx]>0){
                isVisited[ny][nx] = true;
                dfs(ny, nx, isVisited);
            }
        }
    }
    static int check() {
        // 빙산이 분리되었는지 확인
        boolean isIce = false;
        boolean[][] isVisited = new boolean[n][m];
        int cnt=0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (arr[y][x]==0)
                    arr[y][x]=-1;
                else if(arr[y][x]>0 && !isVisited[y][x]) {
                    isIce = true;
//                    System.out.println(y +" "+x);
                    dfs(y, x, isVisited);
                    cnt++;
                }
            }
        }
//        System.out.println(cnt);
        if(!isIce) return -1;
        return cnt;
    }

    static void melt() {
        // 1년동안 얼음이 녹는지 확인
        int ny, nx;
        int cnt;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if(arr[y][x]>0) {
                    cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        ny = y + dy[i];
                        nx = x + dx[i];
                        if (isValid(ny, nx) && arr[ny][nx] == -1) {
                            cnt++;
                        }
                    }
                    arr[y][x] = Math.max(0, arr[y][x]-cnt);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = (arr[i][j]==0)? -1:arr[i][j];
            }
        }

        int year = 0;
        int fin = 1;

        while(fin==1){
            year++;
            melt();
            fin = check();
        }

        System.out.println((fin==-1)?0:year);
    }
}