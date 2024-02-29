import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,fuel;
    static int[] taxi = {0,0};
    static int[][] originMap;   // 택시맵: 벽(1)과 손님(-index)의 위치만 기입
    static int[][] map;         // 택시맵: 택시가 움직일 때 마다 변할 맵
    static int[][] destination; // 도착지: 도착지의 row col 기입

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력| N M fuel
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        fuel = Integer.parseInt(stk.nextToken());

        // 입력| 맵
        originMap = new int[N+2][N+2];
        Arrays.fill(originMap[0], 1);
        Arrays.fill(originMap[N+1], 1);
        for(int i=1; i<=N; i++){
            Arrays.fill(originMap[i], 1);
            stk = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                originMap[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 입력| 택시 위치
        stk = new StringTokenizer(br.readLine());
        taxi[0] = Integer.parseInt(stk.nextToken());
        taxi[1] = Integer.parseInt(stk.nextToken());

        // 입력| 출발(r,c), 도착(r,c)
        destination = new int[M+1][2];
        for(int i=1; i<=M; i++){
            stk = new StringTokenizer(br.readLine());
            int sR = Integer.parseInt(stk.nextToken());   // 출발r
            int sC = Integer.parseInt(stk.nextToken());   // 출발c
            int eR = Integer.parseInt(stk.nextToken());   // 도착r
            int eC = Integer.parseInt(stk.nextToken());    // 도착c
            originMap[sR][sC] = -i;
            destination[i][0] = eR;
            destination[i][1] = eC;
        }

        // 택시 이동
        taxxxxi();

        //출력
        System.out.println(fuel);
        br.close();
    }

    // 택시 이동
    static void taxxxxi(){
        // 손님의 수 만큼 반복
        for(int i=0; i<M; i++){
            // 맵 복사
            map = new int[N+2][];
            for(int n=0; n<N+2; n++){
                map[n] = Arrays.copyOf(originMap[n], N+2);
            }

            // 손님까지 일단 가보기
            int cNumber = go2customer();

            // 손님에게 갔지만 연료가 바닥난 경우... 뭐 어쩌겠어 오늘 접어!
            if(cNumber == 0){
                fuel = -1;
                return;
            }

            // 손님에게 정상 도착한 경우, 맵 복사, 출발지 삭제
            originMap[taxi[0]][taxi[1]] = 0;
            map = new int[N+2][];
            for(int n=0; n<N+2; n++){
                map[n] = Arrays.copyOf(originMap[n], N+2);
            }

            // 손님에게 정상 도착한 경우, 목적지 설정 및 
            int r = destination[cNumber][0];
            int c = destination[cNumber][1];
            map[r][c] = -cNumber;

            // 목적지까지 이동
            boolean canGo = go2Destination(-cNumber);
            
            // 목적지에 도착하지 못한경우... 뭐 어쩌겠어 오늘 접어!
            if(!canGo){
                fuel = -1;
                return;
            }

            // 손님i의 경로 삭제
            if(originMap[taxi[0]][taxi[1]] > 0){
                originMap[taxi[0]][taxi[1]] = 0;
            }
           
        }
    }

    static int[] dr = {-1,0,0,1};//위왼오아
    static int[] dc = {0,-1,1,0};//위왼오아

    // 반환값 {도착여부: 0(도착실패), 양수(고객번호)
    static int go2customer(){
        if(map[taxi[0]][taxi[1]] < 0) return -map[taxi[0]][taxi[1]];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(taxi);
        map[taxi[0]][taxi[1]] = 1;

        int min1 = N+2;
        int min2 = N+2;
        while(!queue.isEmpty()){
            if(fuel-- < 0) return 0;

            int size = queue.size();
            while(size-- > 0){
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                
                for(int i=0; i<4; i++){
                    int newR = r+dr[i];
                    int newC = c+dc[i];
                    if(map[newR][newC] != 1){
                        // 승객을 찾은 경우
                        if(map[newR][newC] < 0){
                            if((newR<min1)&&(newC<min2)){
                                min1 = newR;
                                min2 = newC;
                            }else if((newR<min1)){
                                min1 = newR;
                                min2 = newC;
                            }else if((newR==min1)&&(newC<min2)){
                                min1 = newR;
                                min2 = newC;
                            }
                        }  
                        // 승객이 아닌경우
                        else{
                            map[newR][newC] = 1;
                            queue.offer(new int[]{newR,newC});
                        }
                    }   
                }
            }
            if(min1!=N+2 && min2!=N+2){
                taxi[0] = min1;
                taxi[1] = min2;
                return -map[min1][min2];
            }
        }

        return 0;
    }


    // 반환값 {도착여부}
    static boolean go2Destination(int target){
        if(map[taxi[0]][taxi[1]] == target) return true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(taxi);

        int usedFuel = 1;
        while(!queue.isEmpty()){
            if(fuel - usedFuel < 0) return false;

            int size = queue.size();
            while(size-- > 0){
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                
                for(int i=0; i<4; i++){
                    int newR = r+dr[i];
                    int newC = c+dc[i];
                    if(map[newR][newC] != 1){
                        // 도착지를 찾은 경우
                        if(map[newR][newC] == target){
                            taxi[0] = newR;
                            taxi[1] = newC;
                            fuel += usedFuel;
                            return true;
                        }  
                        // 도착지가 아닌경우
                        else{
                            map[newR][newC] = 1;
                            queue.offer(new int[]{newR,newC});
                        }
                    }   
                }
            }
            usedFuel++;
        }

        return false;
    }
}
