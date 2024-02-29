import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_30024 {
    static class Corn implements Comparable<Corn>{
        int x, y, value;
        public Corn(int x,int y,int value){
            this.x=x;
            this.y=y;
            this.value=value;
        }
        @Override
        public int compareTo(Corn o) {
            return o.value-this.value;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m;
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        int[][] farm=new int[n+2][m+2];
        for (int i=1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=1; j<m+1; j++) {
                farm[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Corn> q = new PriorityQueue<>();
        for (int i=1; i<n+1; i++) {
            q.add(new Corn(i,1,farm[i][1]));
            farm[i][1]=0;
            q.add(new Corn(i,m,farm[i][m]));
            farm[i][m]=0;
        }
        for (int i=2; i<=m-1; i++) {
            q.add(new Corn(1,i,farm[1][i]));
            farm[1][i]=0;
            q.add(new Corn(n,i,farm[n][i]));
            farm[n][i]=0;
        }

//        System.out.println("------------------초기 큐------------------");
//		for (Corn c : q) {
//			System.out.println(c.value);
//		}
//		System.out.println();

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        StringBuilder sb = new StringBuilder();
        while (k>0) {
            Corn now = q.poll();
            sb.append(now.x).append(" ").append(now.y).append("\n");
            //sb.append(now.x).append(" ").append(now.y).append(" ").append(now.value).append(" ").append("\n");

            for (int i=0; i<4; i++) {
                int nextX = now.x+dx[i];
                int nextY = now.y+dy[i];

                if (farm[nextX][nextY]==0 || nextX==1 || nextX==n || nextY==1 || nextY==m ) continue;

                q.add(new Corn(nextX, nextY, farm[nextX][nextY]));
                farm[nextX][nextY]=0;

//                System.out.println("------------------수정 큐------------------ "+k);
//                for (Corn c : q) {
//                    System.out.println(c.value);
//                }
//                System.out.println();
            }

            k--;
        }

        System.out.println(sb.toString());
    }
}
