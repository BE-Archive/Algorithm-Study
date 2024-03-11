package Week_08.BOJ_1522;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1522 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int n = str.length();
        int cnt=0;
        for (int i = 0; i < n; i++) {
            if(str.charAt(i)=='b'){
                cnt++;
            }
        }

        int max = 0, tmp = 0;
        for (int i = 0; i < cnt; i++) {
            if(str.charAt(i)=='b') tmp++;
        }
        for (int i = 0; i < n; i++) {
            max=Math.max(tmp, max);
            if(str.charAt(i)=='b') tmp--;
            if(str.charAt((i+cnt)%n)=='b') tmp++;
        }
        System.out.println(cnt-max);
    }
}