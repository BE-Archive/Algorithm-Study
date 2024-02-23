package seoyoung059.Week_02.BOJ_30024;

import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.util.PriorityQueue;
        import java.util.StringTokenizer;

public class BOJ_30024 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, k;
    static int[][] arr;
    static PriorityQueue<Cell> pq;

    static class Cell implements Comparable<Cell>{
        int x,y;
        int corns;
        public Cell(int y, int x,int corns){
            this.x = x;
            this.y = y;
            this.corns = corns;
        }

        @Override
        public int compareTo(Cell o) {
            return o.corns - this.corns;
        }
    }
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static boolean isValid(int y, int x) {
        return (0<=y&&y<n&&0<=x&&x<m);
    }
    static void solution() {
        Cell curr;
        int ny, nx;
        for (int i = 0; i < k; i++) {
            curr = pq.poll();
            sb.append(curr.y+1);
            sb.append(" ");
            sb.append(curr.x+1);
            sb.append("\n");
            arr[curr.y][curr.x] = 0;
            for (int j = 0; j < 4; j++) {
                ny = curr.y+dy[j];
                nx = curr.x+dx[j];
                if(isValid(ny, nx)&&arr[ny][nx]!=0){
                    pq.offer(new Cell(ny, nx, arr[ny][nx]));
                    arr[ny][nx]=0;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        arr = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
                if (y==0||y==n-1||x==0||x==m-1){
                    pq.offer(new Cell(y,x,arr[y][x]));
                    arr[y][x]=0;
                }
            }
        }
        k = Integer.parseInt(br.readLine());
        solution();
        System.out.print(sb.toString());
    }
}