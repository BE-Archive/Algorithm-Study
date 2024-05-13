import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[101];

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from] = to;
        }

        System.out.println(bfs(arr));
    }

    private static int bfs(int[] arr) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isv = new boolean[101];
        queue.offer(1);
        isv[1] = true;

        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();
                for (int i = 1; i <= 6; i++) {
                    int next = cur + i;
                    if(next == 100) return result;
                    if(isv[next]) continue;
                    isv[next] = true;
                    if(arr[next] != 0) {
                        isv[arr[next]] = true;
                        queue.offer(arr[next]);
                        continue;
                    }
                    queue.offer(next);
                }
            }
            result++;
        }
        return result;
    }

}