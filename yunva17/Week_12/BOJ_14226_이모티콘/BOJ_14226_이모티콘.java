import java.io.*;
import java.util.*;


public class Emoticon {

    static int S, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        answer = 0;

        solve();

        System.out.println(answer);
    }

    public static void solve() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean visited[][] = new boolean[1001][1001];

        queue.add(new int[]{1, 0, 0}); // screen, clipboard, count
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int ns = now[0];
            int nc = now[1];
            int count = now[2];

            if (ns == S) {
                answer = count;
                break;
            }

            if (!visited[ns][ns]) {
                queue.add(new int[]{ns, ns, count + 1}); // screen -> clipboard
                visited[ns][ns] = true;
            }

            if (nc + ns <= S) {
                if (!visited[ns + nc][nc] && nc > 0) { // 클립보드가 비어있지 않은 상태
                    queue.add(new int[]{ns + nc, nc, count + 1});
                    visited[ns + nc][nc] = true;
                }
            }

            if (ns > 0 && !visited[ns - 1][nc]) {
                queue.add(new int[]{ns - 1, nc, count + 1});
                visited[ns - 1][nc] = true;
            }


        }


    }


}
