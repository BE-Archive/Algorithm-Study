import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14267 {

    static int n;
    static int[] sumComment;
    static int[] boss;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        sumComment = new int[n + 1];
        boss = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i < n + 1; i++) {
            boss[i] = Integer.parseInt(st.nextToken());
        }

        int under;
        int comment;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            under = Integer.parseInt(st.nextToken()); //부하
            comment = Integer.parseInt(st.nextToken());
            sumComment[under] += comment;
            BFS(under, comment);
        }

        for (int i = 1; i < n + 1; i++) {
            sb.append(sumComment[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void BFS(int under, int comment) {
        Queue<Integer> q = new LinkedList<>();
        for (int i=under+1; i<n+1; i++) {
            if (boss[i]==under) {
                q.add(i);
                sumComment[i]+=comment;
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i=now+1; i<n+1; i++) {
                if (boss[i]==now) {
                    q.add(i);
                    sumComment[i]+=comment;
                }
            }
        }
    }
}

/*
너무 칭찬을 많이 하지 않도록 해라...
일을 줄여라...
 */