package sjhlko.week27.BOJ_5904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MooGame {
    static int N;

    static char solution() {
        /**
         * 3
         * 343
         * 343 5 343
         * 343 5 343  6  343 5 343
         * -> 어느 부분(N)차 수열 에 속하는지를 분할정복으로
         * 해결하면 될 듯 한데 점화식? 로직? 이 잘 안떠오름..
         *
         */
        return 'm';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

    }
}
