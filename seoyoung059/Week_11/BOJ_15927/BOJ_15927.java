package Week_11.BOJ_15927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15927 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();

        int left = 0, right = n-1, i;
        boolean isPalindrom = true, allSame = true;
        char firstLetter = str.charAt(0);
        while(left<=right){
            if(str.charAt(left)!=firstLetter || str.charAt(right)!=firstLetter) allSame = false;
            if(str.charAt(left)!=str.charAt(right)) {
                isPalindrom = false;
                break;
            }
            left++; right--;
        }
        System.out.println(allSame?-1:(isPalindrom?n-1:n));
    }
}
