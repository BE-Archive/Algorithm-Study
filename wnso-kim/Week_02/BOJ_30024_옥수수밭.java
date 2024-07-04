import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Corn[][] cornfield;
    static boolean[][] visited;
    static int N,M,K;
    static StringBuilder result = new StringBuilder();
    static class Corn implements Comparable<Corn>{
        int y;
        int x;
        int data;
        Corn(int y, int x, int data){
            this.y = y;
            this.x = x;
            this.data = data;
        }

        @Override
        public int compareTo(Corn c){
            return c.data-this.data;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        // 입력
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        cornfield = new Corn[N+2][M+2];
        visited = new boolean[N+2][M+2];
        for(int i=1; i<=N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                cornfield[i][j] = new Corn(i,j,Integer.parseInt(stk.nextToken()));
            }
        }

        // 수확을 위한 준비| 옥수수 밭 밖에서 진입점 확인하기
        PriorityQueue<Corn> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            if(!visited[i][1]){
                pq.add(cornfield[i][1]); visited[i][1] = true;
            }
            if(!visited[i][M]){
                pq.add(cornfield[i][M]); visited[i][M] = true;
            }
            
        }

        for(int i=1; i<=M; i++){
            if(!visited[1][i]){
                pq.add(cornfield[1][i]); visited[1][i] = true;
            }
            if(!visited[N][i]){
                pq.add(cornfield[N][i]); visited[N][i] = true;
            }
        }

        // 수확시작
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            harvest(pq);
        }

        // 결과 출력
        System.out.println(result);
    }

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static void harvest(PriorityQueue<Corn> pq){
        if(pq.isEmpty())
            return;

        Corn root = pq.poll();
        int y = root.y;
        int x = root.x;
        
        result.append(y + " " + x + "\n");
        for(int i=0; i<4; i++){
            int curY = y+dy[i];
            int curX = x+dx[i];

            if(cornfield[curY][curX]!=null && !visited[curY][curX]){
                visited[curY][curX] = true;
                pq.add(cornfield[curY][curX]);
            }
        }
    }
}
