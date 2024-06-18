import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        System.out.println(bfs(S));
    }

    private static int bfs(int S) {
        int result = 0;
        boolean[][] isv = new boolean[S + 1][S + 1];
        Queue<Pair> queue = new ArrayDeque<>();
        isv[1][1] = true;
        queue.offer(new Pair(1, 1, 1));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (p.total == S) {
                result = p.time;
                break;
            }

            int cur = p.total + p.copy;
            if(cur <= S && !isv[cur][p.copy]){
                isv[cur][p.copy] = true;
                queue.offer(new Pair(cur, p.copy, p.time + 1));
            }
            if(p.total <= S && !isv[p.total][p.total]){
                isv[p.total][p.total] = true;
                queue.offer(new Pair(p.total, p.total, p.time + 1));
            }
            if(p.total > 1 && !isv[p.total - 1][p.copy]){
                isv[p.total - 1][p.copy] = true;
                queue.offer(new Pair(p.total - 1, p.copy, p.time + 1));
            }
        }
        return result;
    }
}

class Pair{
    int total, copy, time;

    public Pair(int total, int copy, int time) {
        this.total = total;
        this.copy = copy;
        this.time = time;
    }
}