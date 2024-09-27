import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28017_게임을_클리어하자 {

    static int N,M;
    static int[][] games;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        games = new int[N][M];

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                games[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for(int n=1; n<N; n++){
            for(int m=0; m<M;m++){
                int tmp = 5_000_000;

                for(int i=0; i<M; i++){
                    if(m==i) continue;

                    tmp = Math.min(tmp, games[n][m]+games[n-1][i]);
                }

                games[n][m] = tmp;
            }
        }

        int answer = 5_000_000;
        for(int m=0; m<M; m++){
            answer = Math.min(answer, games[N-1][m]);
        }

        System.out.println(answer);
    }
}
