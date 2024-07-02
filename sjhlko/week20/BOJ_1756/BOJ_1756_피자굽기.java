package sjhlko.week20.BOJ_1756;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1756_피자굽기 {
    //https://www.acmicpc.net/problem/1756
    //피자 굽기
    static int ans;
    static long D, N;
    static List<Long> oven = new ArrayList<>(), pizza = new ArrayList<>(), narrowest = new ArrayList<>();

    static void init() {
        long narrow = oven.get(0);
        for (int i = 0; i < oven.size(); i++) {
            if (narrow > oven.get(i))
                narrow = oven.get(i);
            narrowest.add(narrow);
        }
    }

    static int solution() {
        int pIndex = 0;
        ans = -1;
        for (int i = oven.size() - 1; i >= 0; i--) {
            if (pIndex == N) break;
            if (narrowest.get(i) >= pizza.get(pIndex)) {
                pIndex++;
                ans = i + 1;
            }
        }
        return pIndex == N ? ans : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        D = Long.parseLong(st.nextToken());
        N = Long.parseLong(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < D; i++) {
            oven.add(Long.parseLong(st.nextToken()));
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            pizza.add(Long.parseLong(st.nextToken()));
        }
        init();
        System.out.println(solution());
    }
}
