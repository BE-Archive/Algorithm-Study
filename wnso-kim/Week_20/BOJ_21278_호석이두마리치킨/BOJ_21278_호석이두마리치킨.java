import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_21278_호석이두마리치킨 {

    static int N,M;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            Arrays.fill(map[i], 100);
            map[i][i] = 0;
        }

        for(int i=0; i<M; i++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            map[from][to] = 1;
            map[to][from] = 1;
        }

        // 플로이드 와샬
        floydWarshall();

        // 값 확인
        int first = 1;
        int second = 1;
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j) continue;

                int sum = 0;
                for(int k=1; k<=N; k++){
                    if(i==k || j==k) continue;

                    sum += 2 * Math.min(map[i][k], map[j][k]);
                }

                if(sum < min){
                    first = i;
                    second = j;
                    min = sum;
                } 
            }
        }

        System.out.println(first + " " + second + " " + min);
    }

    // 플로이드 와샬
    static void floydWarshall(){
        // 경유지
        for(int k=1; k<=N; k++){
            // 출발지
            for(int i=1; i<=N; i++){
                if(k==i) continue;
                
                // 도착지
                for(int j=1; j<=N; j++){
                    if(i==j || k==j) continue;

                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }
    }
}
