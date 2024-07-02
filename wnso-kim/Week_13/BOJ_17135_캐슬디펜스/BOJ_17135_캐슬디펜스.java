import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {

    static int N,M,D,ANSWER=0;
    static int[][] MAP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 N M D
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        D = Integer.parseInt(stk.nextToken());
        // 입력 map
        MAP = new int[N][M];
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                MAP[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 시간이 흐르고.. 틱택톡
        aFewMomentsLater();

        // 결과 출력
        System.out.println(ANSWER);
    }

    // 조합으로 결과 확인
    static void aFewMomentsLater(){
        // M combi 3
        for(int i=0; i<M-2; i++){
            for(int j=i+1; j<M-1; j++){
                for(int k=j+1; k<M; k++){
                    war_NeverAgain_NeverAgain_War(i,j,k);
                }
            }
        }
    }

    // 전쟁전쟁! 결코 다시! 전쟁!
    static int[][] visit;
    static int[][] map;
    static void war_NeverAgain_NeverAgain_War(int i, int j, int k){
        map = new int[N+1][M];
        for(int index=0; index<N; index++) map[index] = Arrays.copyOf(MAP[index], M);
        
        int answer = 0;

        for(int row=N-1; row>=0; row--){
        	visit = new int[N][M];
        	
            int[] is = bfs(i, row);
            int[] js = bfs(j, row);
            int[] ks = bfs(k, row);
            
            // 적 처리 일괄 처리
            if(map[is[0]][is[1]] == 1) {map[is[0]][is[1]]=0; answer++;}
            if(map[js[0]][js[1]] == 1) {map[js[0]][js[1]]=0; answer++;}
            if(map[ks[0]][ks[1]] == 1) {map[ks[0]][ks[1]]=0; answer++;}
        }

        ANSWER = Math.max(ANSWER, answer); 
    }

    // bfs로 확인
    static int[] bfs(int archer, int row){
    	if(map[row][archer] ==1) return new int[] {row,archer};
    	
    	Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, archer});
        
        visit[row][archer] |= (1<<archer);	// 방문 처리
        
        while(!queue.isEmpty()){
            int[] state = queue.poll();
            
            for(int i=0; i<3; i++){
                int r = state[0]+dr[i];
                int c = state[1]+dc[i];

                if(isOut(r, c)) continue;                       // 방문 불가
                if((visit[r][c] & (1<<archer)) >0) continue;    // 이미 방문 
                if((Math.abs(row-r) + Math.abs(archer-c)) >=D) continue;	// 거리 D 초과 
                
                if(map[r][c]==1) return new int[] {r,c}; // 내가 처치

                visit[r][c] |= (1<<archer);       // 방문 처리
                queue.offer(new int[]{r,c});    // 다음 칸 확인
            }
        }

        return new int[] {N,0}; // 처리할 적 없음
    }

    static int[] dr = {0,-1,0}; // 왼 위 오
    static int[] dc = {-1,0,1}; // 왼 위 오
    static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=N || c>=M;
    }
}
