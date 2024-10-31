import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class BOJ20437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());
            if (K == 1) {
                sb.append(1).append(" ").append(1).append("\n");
                continue;
            }
            Map<Character, Info> map = new HashMap<>();

            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                Info info = map.getOrDefault(c, null);

                Info newInfo;
                if (info == null) {
                    newInfo = new Info(1, j, null, null);
                } else {
                    newInfo = new Info(info.cnt + 1, j, info.first == null ? info : info.first, null);
                    info.next = newInfo;
                }

                if (newInfo.cnt == K) {
                    int diff = (newInfo.idx - newInfo.first.idx) + 1;
                    min = Math.min(min, diff);
                    max = Math.max(max, diff);
                } else if (newInfo.cnt > K) {
                    newInfo.first = info.first.next;
                    newInfo.cnt--;
                    int diff = (newInfo.idx - newInfo.first.idx) + 1;
                    min = Math.min(min, diff);
                    max = Math.max(max, diff);
                }
                map.put(c, newInfo);
            }
            if (min == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
                continue;
            }
            sb.append(min).append(" ").append(max).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Info {
    int cnt, idx;
    Info first, next;

    public Info(int cnt, int idx, Info first, Info next) {
        this.cnt = cnt;
        this.idx = idx;
        this.first = first;
        this.next = next;
    }
}