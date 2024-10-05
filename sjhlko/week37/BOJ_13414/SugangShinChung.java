package sjhlko.week37.BOJ_13414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SugangShinChung {
    // https://www.acmicpc.net/problem/13414
    // 수강신청
    static int K, L;
    static Map<String, Integer> hm = new HashMap<>();
    static List<String> list = new ArrayList<>();

    static void solution() {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            if (hm.get(s) == 1){
                sb.append(s)
                  .append("\n");
                if(--K==0) break;
            }
            else hm.put(s, hm.get(s) - 1);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        while (L-- > 0) {
            String s = bf.readLine();
            hm.put(s, hm.getOrDefault(s, 0) + 1);
            list.add(s);
        }
        solution();
    }
}
