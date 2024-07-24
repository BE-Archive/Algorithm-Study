package seoyoung059.Week_27.BOJ_5904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5904 {

    static int[] length(int n) {
        int l = 0, idx = 0;
        while (l * 2 + idx + 3 < n) {
            l = l * 2 + idx + 3;
            idx++;
        }
        return new int[] {idx, l};
    }

    static boolean solution(int n){
        int[] tmp = length(n);

        if(n - tmp[1] == 1) return true;
        else if (n - tmp[1] < tmp[0]+3) return false;
        else return solution(n-tmp[1]-tmp[0]-3);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(solution(n)?'m':'o');

    }
}

