package sjhlko.week22.BOJ_2531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2531_회전초밥 {
    //https://www.acmicpc.net/problem/2531
    //회전 초밥
    static int N, d, k, c;
    static Map<Integer, Integer> hm = new HashMap<>();
    static List<Integer> susi = new ArrayList<>();
    static int ans = 0;

    static void solution() {
        for (int i = 0; i < susi.size(); i++) {
            hm.put(susi.get(i), hm.getOrDefault(susi.get(i),0)+1);
            if (i >= k) {
                hm.put(susi.get(i - k), hm.get(susi.get(i - k)) - 1);
                if (hm.get(susi.get(i - k)) == 0)
                    hm.remove(susi.get(i - k));
            }
            if (hm.containsKey(c))
                ans = Math.max(ans, hm.keySet().size());
            else ans = Math.max(ans, hm.keySet().size() + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            susi.add(Integer.parseInt(bf.readLine()));
        }
        susi.addAll((susi));
        solution();
        System.out.println(ans);
    }
}
