import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16197_두동전 {

    static int N,M,answer=11;
    static char[][] MAP;   
    static List<int[]> coins = new LinkedList<>();      // 코인 2개 저장
    static List<Long> commands;         // 최대 10회의 이동방향 저장: 유망한 커멘드 저장
                                        // 1:좌, 2:우, 3:상, 4:하
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N, M
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());    
        M = Integer.parseInt(stk.nextToken());    
        
        // 입력| MAP 
        // 찾기| coins -> o찾기
        MAP = new char[N][M];
        for(int i=0; i<N; i++){
            MAP[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(MAP[i][j] == 'o') coins.add(new int[]{i,j});
            }
        }

        // BFS| coins로 BFS -> 맵 밖으로 나간 경우, commands에 이동방향 저장
        // check| commands 기준으로 가능한지 파악
        for(int i=0; i<2; i++) {
            bfs(coins.get(i)[0], coins.get(i)[1]);
            check(i^1);
        }

        // 출력
        System.out.println(answer==11? -1: answer);      
    }

    
    static int[] dr = {0, 0,0,-1,1};    // index 0은 버림
    static int[] dc = {0, -1,1,0,0};    // index 0은 버림
    static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=N || c>=M;
    }

    static void bfs(int R, int C){
        commands = new LinkedList<>();              // 유망한 커멘드 저장을 위한 리스트
        Queue<int[]> queue = new ArrayDeque<>();    // 0:r, 1:c, 2:커멘드, 3:클릭횟수
        
        queue.offer(new int[]{R,C,0,0});

        while(!queue.isEmpty()){
            int[] state = queue.poll();
            
            for(int i=1; i<=4; i++){
                int r = state[0]+dr[i];
                int c = state[1]+dc[i];
                int cmd = state[2];
                int cnt = state[3];

                if(isOut(r, c)) {   // 맵 밖으로 나간 경우
                    commands.add(cmd*10l +i);
                    continue;
                }

                if(cnt == 9) continue;          // 방문 불가(클릭 횟수 초과)
                if(MAP[r][c]=='#') queue.add(new int[]{state[0],state[1],cmd*10+i, cnt+1});
                else queue.add(new int[]{r,c,cmd*10+i, cnt+1});
            }
        }
    }
    // commands중 가장 작은 횟수 찾기
    static int[] index;
    static void check(int target){
        for(long command: commands){
            index = new int[]{coins.get(target)[0], coins.get(target)[1]};
            
            // 커멘드 하나씩 실행
            boolean isOk = true;
            for(char cmd: String.valueOf(command).toCharArray()){
                if(!doOrDie(cmd-'0')){
                    isOk = false;
                    break;
                }
            }

            if(isOk) answer = Math.min(answer, String.valueOf(command).length());
        }
    }

    static boolean doOrDie(int cmd){
        int r = index[0]+dr[cmd];
        int c = index[1]+dc[cmd];

        if(isOut(r, c)) return false;
        if(MAP[r][c]=='#') return true;

        index[0] = r;
        index[1] = c;

        return true;
    }
}