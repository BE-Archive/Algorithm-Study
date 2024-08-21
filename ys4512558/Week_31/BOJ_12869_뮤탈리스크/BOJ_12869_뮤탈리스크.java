import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12869 {

    static boolean[][][] isv = new boolean[61][61][61];
    static int[][] combinations = {
        {9, 3, 1}, {9, 1, 3},
        {3, 9, 1}, {3, 1, 9},
        {1, 3, 9}, {1, 9, 3},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scvs = new int[3];
        for (int i = 0; i < N; i++) {
            scvs[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs(scvs));
    }

    private static int bfs(int[] scvs) {
        Queue<Tuple> queue = new ArrayDeque<>();
        queue.offer(new Tuple(scvs[0], scvs[1], scvs[2]));
        isv[scvs[0]][scvs[1]][scvs[2]] = true;

        int cnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Tuple tuple = queue.poll();

                for (int i = 0; i < combinations.length; i++) {
                    int one = Math.max(0, tuple.one - combinations[i][0]);
                    int two = Math.max(0, tuple.two - combinations[i][1]);
                    int three = Math.max(0, tuple.three - combinations[i][2]);

                    if(isv[one][two][three]) continue;
                    Tuple next = new Tuple(one, two, three);
                    if (next.sum() == 0) return cnt;
                    isv[one][two][three] = true;
                    queue.offer(next);
                }
            }
            cnt++;
        }
        return cnt;
    }

}

class Tuple {

    int one, two, three;

    public Tuple(int one, int two, int three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public int sum() {
        return one + two + three;
    }
}