import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16928_뱀과사다리게임 {
    static int[][] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        move = new int[N + M][2];

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            move[i] = new int[]{a, b};
        }
        System.out.println(solve());

    }

    public static int solve() {
        int visited[] = new int[101];
        Queue<Integer> queue = new ArrayDeque<>();

        Arrays.fill(visited, -1);
        visited[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 1; i <= 6; i++) {
                int next = now + i;

                if (next > 100) continue;

                for (int[] m : move) {
                    if (m[0] == next) {
                        next = m[1];
                        break;
                    }
                }

                if (visited[next] == -1) {
                    visited[next] = visited[now] + 1;
                    queue.add(next);
                }


            }
        }
        return visited[100];

    }


}
