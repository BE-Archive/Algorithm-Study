package seoyoung059.Week_05.BOJ_19238;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_19238 {
    static int n, m, fuel, ty, tx, currP;
    static int[][] map, destination;

    static boolean isValid(int y, int x){
        return (0<=y)&&(y<n)&&(0<=x)&&(x<n);
    }


    // 승객 찾아 태우는 함수
    // 승객을 못 찾을 시 false return
    static boolean findP() {
        // 택시의 현재 위치에 승객이 있을 때 승객을 태움
        if(map[ty][tx]<0) {
            currP = -map[ty][tx];
            map[ty][tx] = 0;
            return true;
        }

        // 승객을 찾으러 가야할 때, bfs 탐색
        boolean[][] visited = new boolean[n][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        // 현재 택시 위치에서 bfs 탐색 시작
        q.offer(new int[] {ty, tx});
        visited[ty][tx] = true;

        int[] curr;
        int ny, nx, qsize; boolean found = false;
        int tmpy = n, tmpx = n;

        // BFS 탐색
        while(!q.isEmpty()&&!found){
            // fuel 없을 시 탐색 실패, false return
            if(fuel<=0) return false;

            // qsize대로 while문 돌려서 bfs depth별로
            qsize = q.size();
            while(qsize-->0) {
                curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    ny = curr[0] + dy[i];
                    nx = curr[1] + dx[i];
                    if (isValid(ny, nx) && !visited[ny][nx]) {
                        // 해당 위치(ny, nx) 승객이 있을 시
                        if (map[ny][nx] < 0) {
                            found = true;
                            // 승객 우선순위 행/열 체크
                            if((ny<tmpy)||((ny==tmpy)&&(nx<tmpx))){
                                currP = -map[ny][nx];
                                map[ty][tx] = 0;
                                tmpy = ny; tmpx = nx;
                            }
                        } // 빈칸일 때
                        else if (map[ny][nx] == 0) {
                            visited[ny][nx] = true;
                            q.offer(new int[]{ny, nx});
                        }
                    }
                }
            }
            // 한 칸 이동시 fuel 1 감소
            fuel--;
        }

        // 승객 탐색 성공 시 승객 태우고 승객 위치로 택시 이동
        if(found) {
            map[tmpy][tmpx] = 0;
            ty = tmpy; tx = tmpx;
        }
        // 승객 탐색 성공 여부 return
        return found;
    }

    // 승객을 목적지까지 데려다주는 함수
    // 데려다 줄 수 있는지 여부를 boolean으로 return
    static boolean toDst(){
        // 해당 승객의 출발지와 도착지가 같을 때, true return
        if(destination[currP][0]==ty && destination[currP][1] == tx){
            return true;
        }
        boolean[][] visited = new boolean[n][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        q.offer(new int[] {ty, tx});
        visited[ty][tx] = true;

        int[] curr;
        int ny, nx, qsize; int cnt=0;
        while(!q.isEmpty()){
            // 연료 부족할 시 목적지까지 데려다줄 수 없으므로 false return
            if(fuel<=0) return false;

            // qsize만큼 확인하여 bfs 깊이별 수행 -> 이동거리 계산
            qsize = q.size();
            while(qsize-->0) {
                curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    ny = curr[0] + dy[i];
                    nx = curr[1] + dx[i];
                    // ny, nx 위치가 목적지일 때 연료 계산 후 택시 이동 후 종료
                    if(destination[currP][0]==ny && destination[currP][1] == nx){
                        fuel+=(1+cnt)*2-1;
                        ty = ny; tx = nx;
                        return true;
                    }
                    // ny, nx 위치가 빈칸일 때 방문 표시 후 queue에 넣음
                    if (isValid(ny, nx) && !visited[ny][nx]&&map[ny][nx] != 1) {
                        visited[ny][nx] = true;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
            // 이동 거리 및 연료 체크
            cnt++;
            fuel--;
        }

        return false;
    }

    // m명의 승객 모두를 태우고 목적지까지 이동시키는 함수
    // m명 모두 이동시켰는지 여부를 boolean으로 return
    static boolean sol(){
        for (int i = 0; i < m; i++) {
            if(!findP()||!toDst()) {return false;}
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        /*
         * 전체 N*N 영역, M명의 승객
         * 승객 고를때
         *  - 현재 위치에서 최단경로가 가장 짧은 승객
         *  - 그런 승객이 여러명이면 그중 행 번호가 가장 작은 승객
         *  - 그런 승객이 여러명이면 그중 열 번호가 가장 작은 승객
         *  - 택시와 승객이 같은 위치이면 승객까지의 최단거리는 0
         * 연료
         *  - 한 칸 이동시 1 소모
         *  - 승객 도착 시 승객을 태워 이동하면서 소모한 연료 양의 2배 충전
         *  - 연료 모두 소모 시 이동 실패
         * 구하는것
         *  - 모든 승객을 성공적으로 데려다줄 수 있는지
         *  - 데려다 줄 수 있는 경우 최종적으로 남는 연료의 양 출력
         */


        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        ty = Integer.parseInt(st.nextToken())-1;
        tx = Integer.parseInt(st.nextToken())-1;


        destination = new int[m+1][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = -(i+1);
            destination[i+1][0] = Integer.parseInt(st.nextToken())-1;
            destination[i+1][1] = Integer.parseInt(st.nextToken())-1;
        }

        System.out.println(sol()?fuel:-1);
    }
}