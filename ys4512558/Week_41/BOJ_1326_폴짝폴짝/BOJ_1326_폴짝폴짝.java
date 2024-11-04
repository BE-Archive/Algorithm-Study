import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1326 {

    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(bfs(start, end));
    }

    private static int bfs(int start, int end) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isv = new boolean[N + 1];

        isv[start] = true;
        queue.offer(start);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();

                if (cur == end) return cnt;

                int right = cur;
                for (int i = 1; (right = cur + i * arr[cur]) <= N; i++) {
                    if(isv[right]) continue;
                    isv[right] = true;
                    queue.offer(right);
                }

                int left = cur;
                for (int i = 1; (left = cur - i * arr[cur]) >= 1; i++) {
                    if(isv[left]) continue;
                    isv[left] = true;
                    queue.offer(left);
                }
            }
            cnt++;
        }

        return -1;
    }

}