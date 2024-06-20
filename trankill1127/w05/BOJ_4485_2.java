import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4485_2 {

    public static class Point implements Comparable<Point>{
        int x,y,sumDist;
        Point(int x, int y, int sumDist){
            this.x=x;
            this.y=y;
            this.sumDist=sumDist;
        }
        @Override
        public int compareTo(Point o) {
            return this.sumDist-o.sumDist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb =new StringBuilder();

        int trial = 1;
        while (true){
            n = Integer.parseInt(br.readLine());
            if (n==0) break;

            cave = new int[n][n]; //동굴 안의 루피 배치
            for (int i=0; i<n; i++){
                st=new StringTokenizer(br.readLine(), " ");
                for (int j=0; j<n; j++){
                    cave[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            memo = new int[n][n]; //최단경로 기록용
            for (int i=0; i<n; i++){
                Arrays.fill(memo[i], Integer.MAX_VALUE);
            }
            BFS();
            int result=memo[n-1][n-1];
            sb.append("Problem ").append(trial).append(": ").append(result).append("\n");

            trial++;
        }

        System.out.println(sb.toString());
    }

    public static int n;
    public static int[][] memo;
    public static int[][] cave;
    public static boolean[][] visited;

    public static void BFS(){
        int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        Queue<Point> q = new PriorityQueue<>();
        q.add(new Point(0,0, cave[0][0]));
        memo[0][0]=cave[0][0];

        while (!q.isEmpty()){
            Point now = q.remove();

            for (int i=0; i<4; i++){ //상하좌우 확인
                int nextX = now.x+direction[i][0];
                int nextY = now.y+direction[i][1];

                if (nextX<0 || nextX>=n || nextY<0 || nextY>=n) continue;

                if ( memo[nextX][nextY]>now.sumDist+cave[nextX][nextY] ){
                    q.add(new Point(nextX, nextY, now.sumDist+cave[nextX][nextY]));
                    memo[nextX][nextY]=now.sumDist+cave[nextX][nextY];
                }
            }
        }
    }
}

/*
0. 문제 파악
[0,0] >> [n-1,n-1]
가질 수 있는 최소 도둑 루피?

1. 접근
완점탐색을 통해 최단 경로 찾기 > BFS
지금까지 경로 길이 + next = 다음까지 경로 길이
그러면 q에 위치랑 지금까지 경로 길이도 저장해야겠다.

그리고 x,y 지점에 대해서 그 지점까지 오는 경로의 개수는 여러개다.
ex) 위에서 오는 경우, 왼쪽에서 오는 경우 등등
완전탐색을 해야 하기 때문에 이 모든 경로를 queue에 넣고 확인해줘야 한다.

그리고 최소 경로의 값은 따로 저장해서 관리하여서 출력을 제대로 할 수 있게 해야 한다.
 */