package trankill1127.w43;

import java.util.*;
import java.io.*;

public class BOJ_1072 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken()); // 총 게임 횟수
        long Y = Long.parseLong(st.nextToken()); // 이긴 게임 횟수

        System.out.println(minGamesToChangeWinRate(X, Y));
    }

    public static long minGamesToChangeWinRate(long X, long Y) {
        long currentRate = (Y * 100) / X;

        // 승률이 이미 99% 이상이면 승률이 변하지 않음
        if (currentRate >= 99) {
            return -1;
        }

        long low = 0, high = 1_000_000_000, result = -1;

        while (low <= high) {
            long mid = (low + high) / 2;
            long newRate = ((Y + mid) * 100) / (X + mid);

            if (newRate > currentRate) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

}

//3 4 5