import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            queue.offer(i + 1);
        }

        sb.append("<");
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < K - 1; j++) {
                int first = queue.poll();
                queue.offer(first);
            }
            sb.append(queue.poll());
            sb.append(", ");
        }
        sb.append(queue.poll());
        sb.append(">");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}