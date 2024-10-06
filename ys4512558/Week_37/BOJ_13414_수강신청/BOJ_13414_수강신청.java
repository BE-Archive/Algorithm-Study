import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13414 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<String> queue = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            queue.offer(str);
            int cnt = map.getOrDefault(str, 0);
            map.put(str, cnt + 1);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty() && N != 0) {
            String str = queue.poll();

            int cnt = map.get(str);
            if (cnt == 1) {
                sb.append(str).append("\n");
                N--;
                continue;
            }
            map.put(str, cnt - 1);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
