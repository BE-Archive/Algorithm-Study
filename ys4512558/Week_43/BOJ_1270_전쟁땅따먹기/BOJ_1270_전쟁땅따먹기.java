import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final String SYJKGW = "SYJKGW";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long T = Long.parseLong(st.nextToken());
            Map<Long, Long> map = new HashMap<>();
            for (int j = 0; j < T; j++) {
                long num = Long.parseLong(st.nextToken());
                long cnt = map.getOrDefault(num, 0L);
                map.put(num, cnt + 1);
            }
            long half = T / 2;
            long res = -1;
            for (long num : map.keySet()) {
                if (map.get(num) >= half + 1) {
                    res = num;
                    break;
                }
            }
            String result = res == -1 ? SYJKGW : String.valueOf(res);
            sb.append(result).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}