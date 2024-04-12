import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14226 {

    public static class Status {
        int viewLen;
        int clipboardLen;
        int time;

        public Status(int viewLen, int clipboardLen, int time) {
            this.viewLen = viewLen;
            this.clipboardLen = clipboardLen;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());
        bfs();
        System.out.println(result);
    }

    public static int s;
    public static int result;
    public static int[][] visited = new int[1001][1001];

    public static void bfs() {
        Queue<Status> q = new LinkedList<>();
        q.add(new Status(1, 0, 0));
        visited[1][0] = 1;

        while (!q.isEmpty()) {
            Status now = q.poll();
            if (now.viewLen == s) {
                result = now.time;
                break;
            }

            if (now.clipboardLen > 0 &&
                    (now.viewLen + now.clipboardLen) <= 1000 &&
                    visited[now.viewLen + now.clipboardLen][now.clipboardLen] == 0) {
                q.add(new Status(now.viewLen + now.clipboardLen, now.clipboardLen, now.time + 1));
                visited[now.viewLen + now.clipboardLen][now.clipboardLen] = 1;
            }
            if (visited[now.viewLen][now.viewLen] == 0) {
                q.add(new Status(now.viewLen, now.viewLen, now.time + 1));
                visited[now.viewLen][now.viewLen] = 1;
            }
            if (now.viewLen - 1 >= 0 && visited[now.viewLen - 1][now.clipboardLen] == 0) {
                q.add(new Status(now.viewLen - 1, now.clipboardLen, now.time + 1));
                visited[now.viewLen - 1][now.clipboardLen] = 1;
            }

        }
    }
}
