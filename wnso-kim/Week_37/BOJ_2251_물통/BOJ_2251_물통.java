import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2251_물통 {

    static int A,B,C;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        A = Integer.parseInt(stk.nextToken());
        B = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());

        visit = new boolean[A+1][B+1][C+1];

        find(0,0,C);

        boolean[] answer = new boolean[C+1];
        for(int b=0; b<=B; b++){
            for(int c=0; c<=C; c++){
                if(visit[0][b][c])
                    answer[c] = true;
            }
        }

        for(int c=0; c<=C; c++){
            if(answer[c])
                System.out.print(c+" ");
        }
    }

    static void find(int a, int b, int c){
        if(visit[a][b][c]) return;
        visit[a][b][c] = true;

        if(a+b <= B) find(0, a+b, c);
        else find((a+b)-B, B, c);

        if(a+c <= C) find(0, b, a+c);
        else find((a+c)-C, b, C);

        if(b+a <= A) find(b+a, 0, c);
        else find(A, (b+a)-A, c);

        if(b+c <= C) find(a, 0, b+c);
        else find(a, (b+c)-C, C);

        if(c+a <= A) find(c+a, b, 0);
        else find(A, b, (c+a)-A);

        if(c+b <= B) find(a, c+b, 0);
        else find(a, B, (c+b)-B);
    }
}
