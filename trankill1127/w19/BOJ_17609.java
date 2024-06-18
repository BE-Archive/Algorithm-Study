import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {
    public static char[] S;
    public static int l, r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++){
            S = br.readLine().toCharArray();
            l=0; r=S.length-1;
            if (isPalindrome(l, r, 0)){
                sb.append("0").append("\n");
            }
            else {
                if (isPalindrome(l+1, r, 1) || isPalindrome(l, r-1, 1)) sb.append("1").append("\n");
                else sb.append("2").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static boolean isPalindrome(int ll, int rr, int mode){
        while (ll<=rr){
            if (S[ll]!=S[rr]) {
                if (mode==0) {
                    l=ll;
                    r=rr;
                }
                return false;
            }
            ll++; rr--;
        }
        return true;
    }
}
