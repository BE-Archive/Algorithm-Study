package Week_19.BOJ_1461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int tmp;
        for (int i = 0; i < n; i++) {
            tmp = Integer.parseInt(st.nextToken());
            if (tmp > 0) positive.add(tmp);
            else negative.add(tmp);
        }

        positive.sort(Comparator.reverseOrder());
        negative.sort(Comparator.naturalOrder());

        int answer = 0;
        int max = -1;
        int cnt = 0;
        for (Integer num : negative) {
            if (cnt % m == 0) {
                answer -= num;
                max = Math.max(max, -num);
            }
            cnt++;
        }

        cnt = 0;
        for (Integer num : positive) {
            if (cnt % m == 0) {
                answer += num;
                max = Math.max(max, num);
            }
            cnt++;
        }
        System.out.println(answer * 2 - max);
    }
}
