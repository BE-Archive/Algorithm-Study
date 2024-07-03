package Week_24.BOJ_1041;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];
        int one = Integer.MAX_VALUE, two = Integer.MAX_VALUE, three = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            one = Math.min(one, dice[i]);
        }


        if (n == 1) {
            int answer = 0, max = 0;
            for (int i = 0; i < 6; i++) {
                answer += dice[i];
                max = Math.max(dice[i], max);
            }
            System.out.println(answer - max);
        } else {
            // 한면만 나오는것 (N-2)*(N-2)*5+(N-2)*4
            // 두 면 나오는 것: (N-2)*8- 4
            // 3면 나오는 것: 4

            for (int i = 0; i < 6; i++) {
                for (int j = i + 1; j < 6; j++) {
                    if (i + j == 5) continue;
                    two = Math.min(two, dice[i] + dice[j]);
                    for (int k = j + 1; k < 6; k++) {
                        if (i + k == 5 || j + k == 5) continue;
                        three = Math.min(three, dice[i] + dice[j] + dice[k]);
                    }
                }
            }

            long answer = (n - 2) * (5 * n - 6) * one + two * ((n - 2) * 8 + 4) + 4 * three;
            System.out.println(answer);
        }


    }
}