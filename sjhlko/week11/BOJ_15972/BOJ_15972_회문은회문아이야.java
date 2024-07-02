package sjhlko.week11.BOJ_15972;

import java.util.Scanner;

public class BOJ_15972_회문은회문아이야 {
    //https://www.acmicpc.net/problem/15927
    //회문은 회문아니야!!
    static String s;
    static int solution() {
        boolean isSame = true;
        char first = s.charAt(0);
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if(s.charAt(left)!=s.charAt(right)) return s.length();
            left++;
            right--;
            if(s.charAt(left)!=first) isSame = false;
        }
        if(isSame) return -1;
        return s.length() - 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        System.out.println(solution());
    }
}
