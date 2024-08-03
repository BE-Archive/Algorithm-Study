package Week_28.BOJ_1254;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1254 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int l = s.length();

//        int mid = l/2;
        int ans = l*2-1;
//        loop:
//        for (int i = mid; i < l; i++) {
//
//
//            for (int j = 0; i+j<l; j++){
//                if(s.charAt(i+j)!=s.charAt(i-j)) continue loop;
//            }
//            ans = i*2+1;
//            break;
//        }
        int left, right;
        loop:
        for (int i = 0; i < l; i++) {
            left = i; right = l-1;
            while(left < right){
                if(s.charAt(left) == s.charAt(right)){
                    left++; right--;
                } else {
                    continue loop;
                }
            }
            ans = l+i;
            break;
        }

        System.out.println(ans);
    }
}

