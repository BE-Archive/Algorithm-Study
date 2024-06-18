import java.io.*;
import java.util.*;

public class Main {

    static int N, M, D, answer = 0;
    static int[] pick = new int[3];
    static int[][] arr;
    static List<int[]> enemies = new ArrayList<>();

    static int solve()
    {
        int castle = N;
        int result = 0;

        int killPoint[][] = new int[N+1][M];

        int x, y, offset;
        while(castle > 0)
        {
            int tmp = 0;
            for(int i = 0; i < 3; ++i)
            {
                int archerX = castle;
                int archerY = pick[i];
                int[] target = new int[] {100, 100, 100}; // x, y, offset

                for(final int[] e : enemies)
                {
                    x = e[0];
                    y = e[1];

                    if(x >= castle || killPoint[x][y] > castle) continue;

                    offset = Math.abs(x - archerX) + Math.abs(y - archerY);

                    if(offset > D) continue;

                    if(target[2] > offset)
                    {
                        target = new int[] {x, y, offset};
                    }
                    else if(target[2] == offset && target[1] > y)
                    {
                        target = new int[] {x, y, offset};
                    }
                }

                int cnt = 0;
                if(target[0] != 100)
                {
                    x = target[0];
                    y = target[1];

                    //System.out.printf("[KILL | %d] %d, %d\n", castle, x, y);

                    // 이미 죽었던 친구라면 지나가
                    if(killPoint[x][y] != 0) continue;

                    killPoint[x][y] = castle;
                    ++result;


                    ++cnt;
                }
                tmp += cnt;
            }

            //System.out.printf("------------------------> %d\n", tmp);

            --castle;
        }

        return result;
    }

    static void comb(int depth, int idx) {
        if(depth == 3) {

            //System.out.println("\n=================================");
            //System.out.println("pick :" + Arrays.toString(pick));
            int tmp =  solve();
            //System.out.println("=> " + tmp);
            answer = Math.max(answer,tmp);
            return;
        }

        for(int i=idx; i<M; ++i) {
            pick[depth] = i;
            comb(depth + 1, i + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) {
                    enemies.add(new int[]{i, j});
                }
            }
        }
        
        comb(0, 0);
        System.out.print(answer);
    } // main

}
