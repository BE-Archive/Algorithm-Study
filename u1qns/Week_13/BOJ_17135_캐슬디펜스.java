import java.io.*;
import java.util.*;

public class BOJ_17135_캐슬디펜스 {

    static int N, M, D, answer = 0;
    static int[] pick = new int[3];
    static int[][] arr;

    static int calcDistance(int[] a, int[] b)
    {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    static int solve()
    {

        /**
         * 1. 궁수들의 위치 조합을 구한다. (너비 M)에서 3개를 골라 pick에 저장한다.
         * 2. 궁수들의 초기 위치는 (N, pick[i])에 위치하게 된다.
         * 3. 궁수들의 공격을 하기 위해 BFS를 돌려 level D만큼 진행한다.
         * 4. 적들이 한칸씩 내려온다 -> 궁수가 그냥 올라가면 될 일. 궁수는 N-T에 위치하게 된다.
         *      처음 1초에는 N 에 궁수가 위치하고
         *      2초에서 N-1 가 된다 .-> N-T; (T는 0초부터)
         * 5. 이 짓을 몬스터가 없을 때까지 한다 -> N-T가 = 0 이 되면 안해도 되지 않겠어요 ?
         */

        int result = 0, timer = 0;
        boolean[][] visited = new boolean[N+1][M];

        while(timer < N)
        {
            int[] top = new int[3];
            int x, y, offset, who;

            // 궁수의 위치
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{N-timer, pick[0], 0});
            q.offer(new int[]{N-timer, pick[1], 1});
            q.offer(new int[]{N-timer, pick[2], 2});

            Queue<int[]> best = new ArrayDeque<>(); // 각 궁수들에게 가장 가까운 몬스터

            PriorityQueue<int[]> monster = new PriorityQueue<>((a, b) -> {
                if(a[2] == b[2])
                {
                    if(a[0] == b[0])
                        return Integer.compare(a[1], b[1]);
                    return Integer.compare(b[0], a[0]);
                }
                return Integer.compare(a[2], b[2]);
            });

            int distance = 0;
            while(!q.isEmpty() )
            {
                int qSize = q.size();
                while(qSize-- > 0)
                {
                    top = q.poll();
                    who = top[2];

                    monster.clear();
                    for(int i=0; i<M; ++i)
                    {
                        x = top[0] - 1;
                        y = i;
                        offset = calcDistance (top, new int[] {x, y});
                        if (offset > D || x < 0 || y < 0 || x >= N - timer || y >= M ||
                                visited[x][y] ||  arr[x][y] == 0) continue;

                        monster.add(new int[]{x, y, offset, who});
                    }

                    if(monster.size() > 0)
                        best.add(monster.poll());
                    monster.clear();
                }
                if(++distance == D) break;
                if(N-timer-distance <= 0) break;
                q.offer(new int[]{N-timer-distance, pick[0], 0});
                q.offer(new int[]{N-timer-distance, pick[1], 1});
                q.offer(new int[]{N-timer-distance, pick[2], 2});
            }

            // D 레벨만큼 돈 상태니 가까운 몬스터 3마리 잡는다.
            int cnt = 0, tmp = 0;
            boolean order[] = new boolean[3];
            while(!best.isEmpty())
            {
                top = best.poll();
                x = top[0];
                y = top[1];
                who = top[3];

                if(order[who]) continue;
                order[who] = true;
                if(visited[x][y] == false) {
                    ++cnt;
                }

                visited[x][y] = true;
                //System.out.printf("[MONSTER] (%d, %d) %d  by %d\n", x, y, top[2], who);
                if(++tmp == 3) break;
            }
            best.clear();
            result += cnt;
            //System.out.printf("result += %d => %d\n", cnt, result);

            //몬스터가 내려온다. <- 궁수가 한 칸 올라간다.-> ++timer다.
            ++timer;
        }

        return result;
    }

    static void comb(int depth, int idx) {
        if(depth == 3) {

            // System.out.println("\n=================================");
            //System.out.println(Arrays.toString(pick));
            int tmp =  solve();
            //System.out.println("-> " + tmp);
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

        arr = new int[N+2][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.print(answer);
    } // main
}
