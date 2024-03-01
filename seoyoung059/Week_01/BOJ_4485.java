package seoyoung059.Week_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_4485 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // y, x가 전체 n*n array 내에 있는지 확인하는 함수
    static boolean isValid(int y, int x, int n) {
        return (0<=y&&y<n&&0<=x&&x<n);
    }

    // BFS + Priority Queue 이용 풀이
    static int solution(int[][] arr, int n) {

        // 방문 확인 boolean 2차원 배열, 전체를 false로 초기화
        boolean[][] visited = new boolean[n][n];
        for(boolean[] d: visited) Arrays.fill(d,false);


        // {(start 지점으로부터의 거리), y좌표, x좌표}의 인자를 받는 우선순위 큐 설정
        // 정렬 기준은 start 지점으로부터의 거리가 짧은 것부터 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[0]-o2[0]);

        // 순회 시작할 [0,0] 에 대한 array 생성 및 삽입
        int[] start = {arr[0][0], 0, 0};
        pq.offer(start);
        int[] current; int ny; int nx;  // 인자 선언

        while(!pq.isEmpty()){
            // start로부터 가장 거리가 짧은 current 배열 poll
            current = pq.poll();
            //current로부터 4방향으로,
            for (int i = 0; i < 4; i++) {
                // y좌표, x좌표를 구하고
                ny = current[1]+dy[i];
                nx = current[2]+dx[i];
                // 도착 지점인지 확인
                if (ny==n-1 && nx==n-1) {
                    return (current[0] + arr[n - 1][n - 1]);
                }
                // 전체 배열 내에서 방문하지 않은 칸에 대해 방문 여부 체크 및 우선순위 큐에 삽입
                if (isValid(ny, nx, n) && !visited[ny][nx]){
                    visited[ny][nx]=true;
                    int[] newOffer = {current[0]+arr[ny][nx], ny, nx};
                    pq.offer(newOffer);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        int caseNum = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if (n==0) break;
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ");
            sb.append(caseNum++);
            sb.append(": ");
            sb.append(solution(arr,n));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}