package sjhlko.week8;

import java.util.Scanner;

public class BOJ_1522_문자열교환 {
    //https://www.acmicpc.net/problem/1522
    //문자열 교환
    static String s;
    static int totalACount;

    static void init() {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') totalACount++;
        }
    }

    static int solution() {
        init();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int aCount = 0, ansCount = 0;
            for (int j = i; ; j++) {
                int index = j % s.length();
                if (aCount == totalACount) {
                    ans = Math.min(ansCount, ans);
                    break;
                }
                if (s.charAt(index) != 'a') ansCount++;
                aCount++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        System.out.println(solution());
    }
}
