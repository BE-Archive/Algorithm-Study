import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        String s = "";
        for (int i = 0; i < t; i++) {
            s = br.readLine();
            int result = palindromeType(s);
            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static int palindromeType(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left)!=s.charAt(right)){
                if (isPalindrome(s,left+1, right) || isPalindrome(s,left,right-1)){
                    return 1;
                }
                else return 2;
            }
            left++; right--;
        }

        return 0;
    }

    public static boolean isPalindrome(String s, int l, int r){
        while (l<r) {
            if (s.charAt(l)!=s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
