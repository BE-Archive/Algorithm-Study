import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1063_킹 {

    static int N;
    static char kingR, kingC;
    static char stoneR, stoneC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        char[] kings = stk.nextToken().toCharArray();
        kingC = kings[0];
        kingR = kings[1];

        char[] stones = stk.nextToken().toCharArray();
        stoneC = stones[0];
        stoneR = stones[1];

        N = Integer.parseInt(stk.nextToken());

        for(int n=0; n<N; n++){
            // 커맨드 입력
            String cmd = br.readLine();

            // 킹, 이동 가능 여부 체크
            char[] kingMove = validateCanMove(cmd, kingC, kingR);
            // 킹 이동 불가능
            if(kingMove[0] == '0') continue;
            
            // 킹 이동 가능한 경우, 돌과 겹치지 않다면 이동
            if(kingMove[1]!=stoneC || kingMove[2]!=stoneR){
                kingC = kingMove[1];
                kingR = kingMove[2];
                continue;
            }

            // 킹 이동 가능, 돌과 겹쳐서 돌 이동 가능한지 체크
            char[] stoneMove = validateCanMove(cmd, stoneC, stoneR);

            // 돌 이동 불가능, 패쓰
            if(stoneMove[0] == '0') continue;

            // 돌 이동 가능, 킹과 돌 이동
            stoneC = stoneMove[1];
            stoneR = stoneMove[2];
            kingC = kingMove[1];
            kingR = kingMove[2];
        }

        // 출력
        System.out.println(""+kingC+kingR);
        System.out.println(""+stoneC+stoneR);
    }

    static char[] validateCanMove(String cmd, char C, char R){
        switch (cmd){
            case "R": C++; break;
            case "L": C--; break;
            case "B": R--; break;
            case "T": R++; break;
            case "RT": C++; R++; break;
            case "LT": C--; R++; break;
            case "RB": C++; R--; break;
            case "LB": C--; R--; break;
        }

        if(C<'A' || C>'H' || R<'1' || R>'8') return new char[]{'0'};

        return new char[]{'1',C,R};
    }
}