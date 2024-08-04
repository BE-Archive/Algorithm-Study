package Week_28.BOJ_1743;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

    public class BOJ_1743 {

        static boolean[][] arr;
        static int N, M;

        static boolean isValid(int i, int j) {
            return 0 <= i && i < N && 0 <= j && j < M && arr[i][j];
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new boolean[N][M];

            int y, x, ny, nx;
            int[] dy = new int[]{1, -1, 0, 0};
            int[] dx = new int[]{0, 0, 1, -1};

            int[][] food = new int[K][2];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                y = Integer.parseInt(st.nextToken()) - 1;
                x = Integer.parseInt(st.nextToken()) - 1;
                arr[y][x] = true;
                food[i][0] = y;
                food[i][1] = x;
            }

            int fy, fx, cnt;
            int[] curr;
            ArrayDeque<int[]> q = new ArrayDeque<>();
            int answer = 0;
            for (int i = 0; i < K; i++) {
                fy = food[i][0];
                fx = food[i][1];
                if (!arr[fy][fx]) continue;
                q.offer(new int[]{fy, fx});
                arr[fy][fx] = false;
                cnt = 0;
                while (!q.isEmpty()) {
                    curr = q.pollFirst();
                    cnt++;
                    for (int j = 0; j < 4; j++) {
                        ny = curr[0] + dy[j];
                        nx = curr[1] + dx[j];
                        if (!isValid(ny, nx)) continue;
                        q.offerLast(new int[]{ny, nx});
                        arr[ny][nx] = false;
                    }
                }
                answer = Math.max(answer, cnt);
            }
            System.out.println(answer);
        }
    }
