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
/*
 * n*m 크기의 밭
 * 각 옥수수의 가치 1~n*m
 * 민석이는 옥수수밭 밖에 있고
 * 바깥에 인접한 옥수수, 혹은 이미 옥수수 따서 빈 공간의 상하좌우까지 고려
 * 접근할 수 있는 것 중에서 가치가 가장 큰 거 K개를 골라서
 * 각각의 위치를 순서대로 출력해야 한다.
 */
//일단 주변부는 계속 검색해야 함...
//단지 여기서 검색 부분이 하나 찍먹 할 때마다 느는게 그렇지...
//좌표랑 옥수수 가치를 같이 저장
//우선순위 큐,,,,? compareTo 기준을 옥수수 가치로 하는거지
//그러면 가치가 제일 높은 애를 뽁 뽑을거잖아??
//걔 좌표 상하좌우 중에서 검색 대상에 없는 애 있으면 추가
//k = min(n*m, 100_000)
