package sjhlko.week23.BOJ_2179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2179_비슷한단어 {
    //https://www.acmicpc.net/problem/2179
    //비슷한 단어
    static int N;

    static void solution() {
        String ans1= null, ans2 = null;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int tmpCount = 0;
                for (int k = 0; k < Math.min(list.get(i).length(),list.get(j).length()); k++) {
                    if (list.get(i).charAt(i) == list.get(j).charAt(j)) {
                        tmpCount++;
                    }
                    else break;
                }
                if (tmpCount > count) {
                    ans1 = list.get(i);
                    ans2 = list.get(j);
                }
            }
        }
        System.out.println(ans1);
        System.out.println(ans2);
    }

    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            list.add(bf.readLine());
        }
        solution();
    }
}
