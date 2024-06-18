import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_17780_새로운게임 {

    static int N, K;
    static int[][] MAP;
    
    static int[] DR = {0, 0, 0, -1, 1};
    static int[] DC = {0, 1, -1, 0, 0};

    static int[][] pieces;  // {번호,R,C,방향}
    static ArrayDeque<Integer>[][] states; // 한 칸의 말 정보

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N K
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        // 초기화| MAP pieces states
        MAP = new int[N+2][N+2];
        pieces = new int[K+1][4];
        states = new ArrayDeque[N+2][N+2];
        for(int i=0; i<=N; i++){
            for(int j=0; j<=N; j++){
                states[i][j] = new ArrayDeque<>();
            }
        }

        // 입력| MAP
        for(int i=1; i<=N; i++){
            stk = new StringTokenizer(br.readLine());
            
            for(int j=1; j<=N; j++){
                MAP[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 입력| pieces
        for(int i=1; i<=K; i++){
            stk = new StringTokenizer(br.readLine());
            pieces[i][0] = i;
            int r = pieces[i][1] = Integer.parseInt(stk.nextToken());
            int c = pieces[i][2] = Integer.parseInt(stk.nextToken());
            pieces[i][3] = Integer.parseInt(stk.nextToken());

            states[r][c].add(i);
        }

        // 게임 실행
        int result = iWantToPlayAGame();

        // 게임 결과 출력
        System.out.println(result);
    }

    static int iWantToPlayAGame(){
        int round = 1;

        while(true){
            if(round > 1000) return -1;

            for(int i=1; i<=K; i++){
                move(i);

                if(states[pieces[i][1]][pieces[i][2]].size() >= 4)
                    return round;
            }

            round++;
        }
    }

    static void move(int number){
        int r = pieces[number][1];
        int c = pieces[number][2];
        int d = pieces[number][3];

        // 해당 말이 맨 아래의 말이 아닌 경우
        if(states[r][c].peek() != number) return;

        int newR = r+DR[d];
        int newC = c+DC[d];
        
        if(newR==0 || newC==0 || newR>N || newC>N) blue(number, r, c);
        else if(MAP[newR][newC] == 0) white(r,c,newR,newC);
        else if(MAP[newR][newC] == 1) red(r,c,newR,newC);
        else blue(number, r, c);
    }

    static void white(int r, int c, int newR, int newC){
        while(!states[r][c].isEmpty()){
            int number = states[r][c].poll();

            states[newR][newC].add(number);

            pieces[number][1] = newR;
            pieces[number][2] = newC;
        }
    }

    static void red(int r, int c, int newR, int newC){
        while(!states[r][c].isEmpty()){
            int number = states[r][c].pollLast();

            states[newR][newC].add(number);

            pieces[number][1] = newR;
            pieces[number][2] = newC;
        }
    }

    static void blue(int number, int r, int c){
        // 방향 전환
        int d = pieces[number][3];
        pieces[number][3] = d = d%2==0? d-1: d+1;

        // 다음 방향
        int newR = r+DR[d];
        int newC = c+DC[d];

        // 이동 가능한 경우만 이동
        if(newR==0 || newC==0 || newR>N || newC>N) return;
        else if(MAP[newR][newC] == 0) white(r, c, newR, newC);
        else if(MAP[newR][newC] == 1) red(r, c, newR, newC);
    }
}
